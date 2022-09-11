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

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TeacherServImpl implements UserDetailsService {
    private TeacherRepo teacherRepo;
    private EmailSenderServ emailSenderServ;
    private PasswordEncoder passwordEncoder;
    private AuthorityService authorityService;
    private StudentServImpl studentServ;
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
                           AttendanceRepo attendanceRepo) {
        this.teacherRepo = teacherRepo;
        this.emailSenderServ = emailSenderServ;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
        this.studentServ = studentServ;
        this.classServ = classServ;
        this.commentsRepo = commentsRepo;
        this.attendanceRepo = attendanceRepo;
    }

    public List<Teacher> getAllTeachers(){
        List<Teacher> teachers = new ArrayList<>();
        teacherRepo.findAll().forEach(x->teachers.add(x));
        return teachers;
    }

    public Teacher getTeacherByEmail(String email){
        return teacherRepo.findByEmail(email);
    }

    public Teacher getTeacher(Integer id){
        return teacherRepo.findById(id).get();
    }

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

    public Student setComments(int studentid,int teacherid, String comments){
        Student student = studentServ.getStudent(studentid);
        Teacher teacher = teacherRepo.findById(teacherid).get();
        Comments comments1 = new Comments();
        comments1.setComment(comments);
        comments1.setTeacher(teacher);
        comments1.setStudent(student);
        CommentsKey commentsKey = new CommentsKey();
        commentsKey.setStudentId(studentid);
        commentsKey.setTeacherId(teacherid);
        comments1.setId(commentsKey);
        commentsRepo.save(comments1);
        return student;
    }

    public Attendance recordAttendance(HelperAttendance student, int classid){
        Class lesson = classServ.getClassDetails(classid);
        Student s = studentServ.getStudent(student.getStudentid());
        if (!attendanceRepo.existsAttendanceByRegister(lesson)){
            Attendance attendance = new Attendance();
            attendance.setRegister(lesson);
            attendance.getStudents().add(s);
            attendanceRepo.save(attendance);
            return attendance;
        } else{
            Attendance attendance = attendanceRepo.findAttendanceByRegister(lesson);
            attendance.getStudents().add(s);
            attendanceRepo.save(attendance);
            return attendance;}

        //        List<Student> students = new ArrayList<>();

//        for(int x: student){
//            students.add(studentServ.getStudent(x));
//        }

//        for (Student y: students){
//            attendance. getStudents().add(y);
//        }

    }

    public List<Attendance> getattendance(){
        List<Attendance> attendances = new ArrayList<>();
        attendanceRepo.findAll().forEach(attendance1 -> attendances.add(attendance1));

        return attendances;
    }

}
