package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.*;
import com.myussuf.myussufprojectspring.Entities.Class;
import com.myussuf.myussufprojectspring.Services.ParentServImpl;
import com.myussuf.myussufprojectspring.Services.StudentServImpl;
import com.myussuf.myussufprojectspring.Services.SubjectServImpl;
import com.myussuf.myussufprojectspring.Services.TeacherServImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ParentTeacherController {
    private SubjectServImpl subjectServImpl;
    private StudentServImpl studentServImpl;
    private ParentServImpl parentServ;
    private TeacherServImpl teacherServ;

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable int id){
        return subjectServImpl.getSubject(id);
    }

    @GetMapping("/subject/{subjectid}")
    public List<Class> getClassBuSubjectId(@PathVariable int subjectid){
        return parentServ.getClassBySubjectId(subjectid);
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable int id){
        return studentServImpl.getStudent(id);
    }

    @DeleteMapping("/comments/{commentid}/{studentid}")
    public void deleteComment(@PathVariable int commentid, @PathVariable int studentid){
        teacherServ.deleteComment(commentid, studentid);
    }

    @PostMapping("/{studentid}/comments/{teacherid}")
    public Student setComment(@PathVariable int studentid, @PathVariable int teacherid, @RequestBody CommentsHelper comment){
        return teacherServ.setComments(studentid, teacherid, comment );
    }

}
