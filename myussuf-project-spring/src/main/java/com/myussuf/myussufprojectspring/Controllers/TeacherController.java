package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.*;
import com.myussuf.myussufprojectspring.Services.AttendanceWrapper;
import com.myussuf.myussufprojectspring.Services.StudentServImpl;
import com.myussuf.myussufprojectspring.Services.TeacherServImpl;
import com.myussuf.myussufprojectspring.security.userDetailsServices.AuthorityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
@AllArgsConstructor
public class TeacherController {
    private TeacherServImpl teacherServImpl;
    private PasswordEncoder passwordEncoder;
    private AuthorityService authorityService;
    private StudentServImpl studentServ;

    @Autowired
    public TeacherController(TeacherServImpl teacherServImpl) {
        this.teacherServImpl = teacherServImpl;
    }

    @GetMapping("/{id}")
    public Teacher getTeacher(@PathVariable int id){
        return teacherServImpl.getTeacher(id);
    }

    @GetMapping("/email/{email}")
    public Teacher getTeacherByEmail(@PathVariable String email){
       return teacherServImpl.getTeacherByEmail(email);
    }


        @PostMapping("/attendance/{classid}")
    public Attendance setAttendance(
            @RequestBody HelperAttendance student,
            @PathVariable int classid
            )
    {
       return teacherServImpl.recordAttendance(student, classid);

    }

    @GetMapping("/attendance")
    public List<Attendance> getAttendance(){
        return teacherServImpl.getattendance();
    }



    @GetMapping("/students/{subjectid}")
    public List<Student> getStudentPerSubject(@PathVariable int subjectid){
        return teacherServImpl.getStudentsBySubject(subjectid);

    }

    @PutMapping("progress/{studentid}/{classid}")
    public Attendance updateUnderstanding(@PathVariable int studentid, @PathVariable int classid, @RequestBody Map<Object, Object> updatedmap){
        return teacherServImpl.updateUnderstanding(studentid, classid,updatedmap);
    }

    @GetMapping("student/{studentid}")
    public Student getStudentById(@PathVariable int studentid){
        return studentServ.getStudent(studentid);
    }
}
