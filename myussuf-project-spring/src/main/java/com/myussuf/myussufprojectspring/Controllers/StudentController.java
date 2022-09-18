package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.Student;
import com.myussuf.myussufprojectspring.Entities.Subject;
import com.myussuf.myussufprojectspring.MyussufProjectSpringApplication;
import com.myussuf.myussufprojectspring.Services.StudentServImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentServImpl studentServImpl;

    @Autowired
    public StudentController(StudentServImpl studentServImpl) {
        this.studentServImpl = studentServImpl;
    }

    //
//    public List<Student> getStudents(){}

    @DeleteMapping("/remove/{studentid}")
    public void deleteStudent(@PathVariable int studentid){
        studentServImpl.deleteStudent(studentid);
    }





}
