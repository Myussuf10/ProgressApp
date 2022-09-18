package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.Class;

import com.myussuf.myussufprojectspring.Entities.Student;
import com.myussuf.myussufprojectspring.Services.ClassServImpl;
import com.myussuf.myussufprojectspring.Services.StudentServImpl;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/adminteacher")
@AllArgsConstructor
public class AdminTeacherController {
    private ClassServImpl classServ;
    private StudentServImpl studentServ;

    @GetMapping("{id}")
    public Class getClass(@PathVariable int id){
        return classServ.getClassDetails(id);
    }

    @GetMapping("subject/{id}")
    public List<Class> getSubject(@PathVariable int id){
        return classServ.getClassBySubject(id);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return  studentServ.getAllStudents();
    }

}
