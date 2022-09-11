package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.*;
import com.myussuf.myussufprojectspring.Services.AttendanceWrapper;
import com.myussuf.myussufprojectspring.Services.TeacherServImpl;
import com.myussuf.myussufprojectspring.security.userDetailsServices.AuthorityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teacher")
@AllArgsConstructor
public class TeacherController {
    private TeacherServImpl teacherServImpl;
    private PasswordEncoder passwordEncoder;
    private AuthorityService authorityService;

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

    @GetMapping("/all")
    public List<Teacher> getTeachers(){return teacherServImpl.getAllTeachers();}

    @PostMapping("/teacher")
    public void signUpTeacher(@RequestBody Teacher teacher){
        teacherServImpl.saveTeacher(teacher);
    }

    @PostMapping("/{studentid}/comment/{teacherid}")
    public Student setComment(@PathVariable int studentid, @PathVariable int teacherid, @RequestBody String comments){
    return teacherServImpl.setComments(studentid,teacherid, comments );

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
}
