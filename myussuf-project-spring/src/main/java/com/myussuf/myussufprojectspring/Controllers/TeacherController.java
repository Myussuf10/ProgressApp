package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.Teacher;
import com.myussuf.myussufprojectspring.Services.TeacherServImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private TeacherServImpl teacherServImpl;

    @Autowired
    public TeacherController(TeacherServImpl teacherServImpl) {
        this.teacherServImpl = teacherServImpl;
    }

    @GetMapping("/{id}")
    public Teacher getTeacher(@PathVariable int id){
        return teacherServImpl.getTeacher(id);
    }

    @PostMapping("/teacher")
    public void signUpTeacher(@RequestBody Teacher teacher){

        System.out.println(teacher);
        teacherServImpl.saveTeacher(teacher);
    }
}
