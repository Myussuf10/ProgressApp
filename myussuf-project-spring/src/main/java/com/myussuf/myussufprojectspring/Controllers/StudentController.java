package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.Student;
import com.myussuf.myussufprojectspring.Entities.Subject;
import com.myussuf.myussufprojectspring.Services.StudentServImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentServImpl studentServImpl;

    @Autowired
    public StudentController(StudentServImpl studentServImpl){
        this.studentServImpl = studentServImpl;
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable int id){
        return studentServImpl.getStudent(id);
    }
//
//    public List<Student> getStudents(){}

    @DeleteMapping("/remove/{studentid}")
    public void deleteStudent(@PathVariable int studentid){
        studentServImpl.deleteStudent(studentid);
    }

    @GetMapping("/student/students")
    public List<Student> getAllStudents(){
      return  studentServImpl.getAllStudents();
    }

    @PostMapping("/student/{parentid}")
    public Integer addStudent(@PathVariable int parentid , @Valid @RequestBody Student student){
        studentServImpl.saveStudent(student, parentid);
        return studentServImpl.getStudent(student.getId()).getId();
    }

    @PutMapping("/student/{subjectid}/{studentid}")
    public void enrollStudent(@PathVariable int subjectid, @PathVariable int studentid){
        studentServImpl.assignStudentToSub(subjectid,studentid);

    }
}
