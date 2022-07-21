package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.Subject;
import com.myussuf.myussufprojectspring.Services.StudentServ;
import com.myussuf.myussufprojectspring.Services.SubjectServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    private SubjectServ subjectServ;
    private StudentServ studentServ;

    @Autowired
    public SubjectController(SubjectServ subjectServ, StudentServ studentServ) {
        this.subjectServ = subjectServ;
        this.studentServ = studentServ;
    }

    @GetMapping("/subjects")
    public List<Subject> getAllSubjects(){return subjectServ.getAllSubjects();};

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable int id){
        return subjectServ.getSubject(id);
    }

//    @PostMapping("/{studentid}")
//    public String setSubjectStudent(@PathVariable int id, int subjectid){
//     studentServ.getStudent(id);
//    }
}
