package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.Teacher;
import com.myussuf.myussufprojectspring.Services.TeacherServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private TeacherServ teacherServ;

    @Autowired
    public TeacherController(TeacherServ teacherServ) {
        this.teacherServ = teacherServ;
    }

    @GetMapping("/{id}")
    public Teacher getTeacher(@PathVariable int id){
        return teacherServ.getTeacher(id);
    }
}
