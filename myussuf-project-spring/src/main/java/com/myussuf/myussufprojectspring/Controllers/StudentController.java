package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.Student;
import com.myussuf.myussufprojectspring.Services.StudentServ;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentServ studentServ;

    @Autowired
    public StudentController(StudentServ studentServ){
        this.studentServ = studentServ;
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable int id){
        return studentServ.getStudent(id);
    }
//
//    public List<Student> getStudents(){}

    @DeleteMapping("/remove/{studentid}")
    public void deleteStudent(@PathVariable int studentid){
        studentServ.deleteStudent(studentid);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
      return  studentServ.getAllStudents();
    }

    @PostMapping("/{parentid}")
    public void addStudent(@PathVariable int parentid , @Valid @RequestBody Student student){
        studentServ.saveStudent(student, parentid);
    }
}
