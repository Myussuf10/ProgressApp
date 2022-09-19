package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.*;
import com.myussuf.myussufprojectspring.Entities.Class;
import com.myussuf.myussufprojectspring.Repository.AttendanceRepo;
import com.myussuf.myussufprojectspring.Repository.CommentsRepo;
import com.myussuf.myussufprojectspring.Repository.TeacherRepo;
import com.myussuf.myussufprojectspring.security.userDetailsServices.AppUser;
import com.myussuf.myussufprojectspring.security.userDetailsServices.AuthorityService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class TeacherServImpl implements UserDetailsService, TeacherServ {
    private TeacherRepo teacherRepo;
    private EmailSenderServ emailSenderServ;
    private PasswordEncoder passwordEncoder;
    private AuthorityService authorityService;
    private StudentServImpl studentServ;
    private SubjectServImpl subjectServ;
    private ClassServImpl classServ;
    private AttendanceRepo attendanceRepo;
    private CommentsRepo commentsRepo;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       Teacher teacher = teacherRepo.findByEmail(s);
       if(teacher==null){
           throw new UsernameNotFoundException("user not found");
       }
        return new User(teacher.getEmail(),teacher.getPassword(),teacher.getAuthorities());
    }

    public TeacherServImpl(TeacherRepo teacherRepo, EmailSenderServ emailSenderServ,
                           PasswordEncoder passwordEncoder, AuthorityService authorityService,
                           StudentServImpl studentServ, @Lazy ClassServImpl classServ,CommentsRepo commentsRepo,
                           AttendanceRepo attendanceRepo, @Lazy SubjectServImpl subjectServ) {
        this.teacherRepo = teacherRepo;
        this.emailSenderServ = emailSenderServ;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
        this.studentServ = studentServ;
        this.classServ = classServ;
        this.commentsRepo = commentsRepo;
        this.attendanceRepo = attendanceRepo;
        this.subjectServ = subjectServ;
    }
    @Override
    public List<Teacher> getAllTeachers(){
        List<Teacher> teachers = new ArrayList<>();
        teacherRepo.findAll().forEach(x->teachers.add(x));
        return teachers;
    }
    @Override
    public Teacher getTeacherByEmail(String email){
        return teacherRepo.findByEmail(email);
    }
    @Override
    public Teacher getTeacher(Integer id){
        return teacherRepo.findById(id).get();
    }
    @Override
    public void saveTeacher(Teacher teacher){
        String em= "Auto generated email";
        String pass = "Your Generated password is " + teacher.getPassword();
        emailSenderServ.sendEmail(teacher.getEmail(),em, pass);
        String encryptedPassword = passwordEncoder.encode(teacher.getPassword());

        List<Authority> authoritiesList = new ArrayList<>();
        authoritiesList.add(authorityService.createAuthority("ROLE_TEACHER"));
        teacher.setPassword(encryptedPassword);
        teacher.setAuthorities(authoritiesList);
        teacherRepo.save(teacher);
    }
    @Override
    public Student setComments(int studentid, int teacherid, CommentsHelper comments){
        Student student = studentServ.getStudent(studentid);
        Comments comments1 = new Comments();
        Teacher teacher = teacherRepo.findById(teacherid).get();
        comments1.setComment(comments.getComment());
        comments1.setDate(comments.getDate());
        comments1.setTeacher(teacher);
        comments1.setSentBy(comments.getSentBy());
        comments1.setRole(comments.getRole());
        comments1.getStudent().add(student);
        student.getComments().add(comments1);
        commentsRepo.save(comments1);
        return student;
    }
    @Override
    public Attendance recordAttendance(HelperAttendance student, int classid){
        Class lesson = classServ.getClassDetails(classid);
        Student s = studentServ.getStudent(student.getStudentid());
        Attendance attendance = new Attendance();
        attendance.setRegister(lesson);
        attendance.getStudent().add(s);
        lesson.getAttendance().add(attendance);
        attendance.setUnderstanding(student.getUnderstanding());
        s.getAttendance().add(attendance);
        return attendanceRepo.save(attendance);

    }
    @Override
    public List<Attendance> getattendance(){
        List<Attendance> attendances = new ArrayList<>();
        attendanceRepo.findAll().forEach(attendance1 -> attendances.add(attendance1));

        return attendances;
    }
    @Override
    public List<Student> getStudentsBySubject(int subjectid){
        return studentServ.getStudentsPerSubject(subjectid);
    }
    @Override
    public Attendance updateUnderstanding(int studentid, int classid, Map<Object, Object> updatedmap){
        Student s = studentServ.getStudent(studentid);
        Class clas = classServ.getClassDetails(classid);
        Attendance x = attendanceRepo.findAttendanceByStudentAndRegister(s, clas);
        updatedmap.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Attendance.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, x, value);
        });
        return x;
    }
    @Override
    public List<Attendance> getSingleAttendance(int student, int subjectid){
        Student s = studentServ.getStudent(student);
        Subject x = subjectServ.getSubject(subjectid);
        List<Class> lessons  = classServ.getClassBySubject(subjectid);
        List<Attendance> attendance = s.getAttendance().stream().filter(d->d.getRegister().getSubject().equals(x)).collect(Collectors.toList());
        attendance.forEach(System.out::println);
        return attendance;
    }
    @Override
    public void deleteComment(int commentid, int studentid){
        Comments x = commentsRepo.findById(commentid).get();
        Student student = studentServ.getStudent(studentid);
        student.getComments().remove(x);
        x.getStudent().remove(student);
        studentServ.assignStudentToSub(student);
        commentsRepo.delete(x);
    }

}
