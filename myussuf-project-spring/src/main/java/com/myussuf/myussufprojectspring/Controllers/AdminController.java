package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.*;
import com.myussuf.myussufprojectspring.Entities.Class;
import com.myussuf.myussufprojectspring.Services.*;
import com.myussuf.myussufprojectspring.security.userDetailsServices.AuthRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/management")
@AllArgsConstructor
public class AdminController {
    private final AdminServImpl adminServImpl;
    private final PasswordEncoder passwordEncoder;
    private final ClassServImpl classServ;
    private final ParentServImpl parentServ;
    private final SubjectServImpl subjectServ;
    private final StudentServImpl studentServ;
    private final TeacherServImpl teacherServ;
    private AuthenticationManager authenticationManager;


    @GetMapping("/admin")
    public List<Admin> getAdmin(){

        return adminServImpl.getAdmins();
    }

    @PostMapping("/admin")
    public Admin addAdmin(@RequestBody Admin newAdmin){
        return adminServImpl.saveAdmin(newAdmin);
    }

    @GetMapping("/admin/{email}")
    public Admin getAdminByEmail(@PathVariable String email){
        return adminServImpl.getAdminByEmail(email);
    }

    @PatchMapping("/student/{studentid}")
    public Student updateStudent(@PathVariable int studentid, @RequestBody Map<Object, Object> updatedmap ){
        return adminServImpl.updateStudent(studentid
                , updatedmap);
    }

    @PatchMapping("/student/{subjectid}/{studentid}")
    public Student setSubject(@PathVariable int subjectid, @PathVariable int studentid){
        return adminServImpl.setSubject(subjectid, studentid);
    }
    @PostMapping("/class/{subjectId}")
    public void setupClass(
            @RequestBody Class clas,
            @PathVariable int subjectId) throws ParseException {
        classServ.setUpClass(clas, subjectId);
    }

    @GetMapping("/teacher/all")
    public List<Teacher> getTeachers(){return teacherServ.getAllTeachers();}

    @PostMapping("/teacher")
    public void signUpTeacher(@RequestBody Teacher teacher){
        teacherServ.saveTeacher(teacher);
    }

    @GetMapping("/parents")
    public List<Parent> allParents(){

        return parentServ.getParents();
    }

    @PostMapping("/parent")
    public void signUpParent(@RequestBody Parent parent){
        parentServ.saveParent(parent);
    }

    @GetMapping("/subjects")
    public List<Subject> getAllSubjects(){return subjectServ.getAllSubjects();}

    @PostMapping("/student/{parentid}")
    public Integer addStudent(@PathVariable int parentid , @Valid @RequestBody Student student){
        studentServ.saveStudent(student, parentid);
        return studentServ.getStudent(student.getId()).getId();
    }

    @PostMapping("/subject/{id}")
    public void addSubject(@RequestBody Subject subject, @PathVariable int id){

        subjectServ.saveSubject(subject, id);
    }
}
