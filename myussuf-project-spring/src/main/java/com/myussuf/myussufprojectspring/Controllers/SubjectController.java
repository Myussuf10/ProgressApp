package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.Subject;
import com.myussuf.myussufprojectspring.Services.StudentServImpl;
import com.myussuf.myussufprojectspring.Services.SubjectServImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
@AllArgsConstructor
public class SubjectController {
    private SubjectServImpl subjectServImpl;
    private StudentServImpl studentServImpl;

    @GetMapping("/subjects")
    public List<Subject> getAllSubjects(){return subjectServImpl.getAllSubjects();};

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable int id){
        return subjectServImpl.getSubject(id);
    }

    @PostMapping("/subject/{id}")
    public void addSubject(@RequestBody Subject subject, @PathVariable int id){

        subjectServImpl.saveSubject(subject, id);
    }

//    @PatchMapping("/{id}")
//    public void assignSubjectToStudent(@PathVariable int id,){
//
//    }
}
