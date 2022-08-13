package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.Subject;
import com.myussuf.myussufprojectspring.Services.StudentServImpl;
import com.myussuf.myussufprojectspring.Services.SubjectServImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    private SubjectServImpl subjectServImpl;
    private StudentServImpl studentServImpl;

    @Autowired
    public SubjectController(SubjectServImpl subjectServImpl, StudentServImpl studentServImpl) {
        this.subjectServImpl = subjectServImpl;
        this.studentServImpl = studentServImpl;
    }

    @GetMapping("/subjects")
    public List<Subject> getAllSubjects(){return subjectServImpl.getAllSubjects();};

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable int id){
        return subjectServImpl.getSubject(id);
    }

    @PutMapping("/{studentid}/{subjectid}")
    public void setSubjectStudent(@PathVariable int studentid, @PathVariable int subjectid){
     subjectServImpl.setSubjectToStudent(studentid,subjectid);
    }
}
