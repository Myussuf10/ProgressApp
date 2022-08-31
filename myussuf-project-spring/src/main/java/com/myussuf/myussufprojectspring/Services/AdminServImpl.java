package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.Admin;
import com.myussuf.myussufprojectspring.Entities.Student;
import com.myussuf.myussufprojectspring.Entities.Subject;
import com.myussuf.myussufprojectspring.Repository.AdminRepo;
import com.myussuf.myussufprojectspring.Repository.StudentRepo;
import com.myussuf.myussufprojectspring.exceptions.AuthException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@Transactional
@Configurable
@AllArgsConstructor
public class AdminServImpl implements UserDetailsService {
    private AdminRepo adminRepo;
    private StudentServImpl studentServ;
    private SubjectServImpl subjectServ;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
     Admin admin = adminRepo.findByEmail(s);
     if(admin == null){
         throw new UsernameNotFoundException("Username not found");
     }
        return new User(admin.getEmail(),admin.getPassword(),admin.getAuthorities());
    }
    public void saveAdmin(Admin admin){
        adminRepo.save(admin);
    }

    public List<Admin> getAdmins(){
        List<Admin> x = new ArrayList<>();
        adminRepo.findAll().forEach(x::add);
        return x;
    }

    public Student updateStudent(int id, Map<Object, Object> updatedmap){
        Student student = studentServ.getStudent(id);
        updatedmap.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Student.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, student, value);
        });
        return student;
    }

    public Student setSubject(int subjectid, int studentid){
        Student student = studentServ.getStudent(studentid);
        Subject subject = subjectServ.getSubject(subjectid);
        if (!student.getSubjects().contains(subject)) {
            student.getSubjects().add(subject);
            studentServ.assignStudentToSub(student);
        }
        return student;
    }

}
