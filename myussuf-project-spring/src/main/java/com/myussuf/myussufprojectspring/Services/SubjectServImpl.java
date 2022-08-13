package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.Student;
import com.myussuf.myussufprojectspring.Entities.Subject;
import com.myussuf.myussufprojectspring.Repository.SubjectRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServImpl {
    private SubjectRepo subjectRepo;
    private StudentServImpl studentServ;

    @Autowired
    public SubjectServImpl(SubjectRepo subjectRepo,@Lazy StudentServImpl studentServ) {
        this.subjectRepo = subjectRepo;
        this.studentServ = studentServ;
    }

    public void saveSubject(Subject subject){
        subjectRepo.save(subject);
    }

    public Subject getSubject(int subjectid){
        return subjectRepo.findById(subjectid).get();
    }

    public List<Subject> getAllSubjects(){
        List<Subject> subjects = new ArrayList<>();
        subjectRepo.findAll().forEach(subject -> subjects.add(subject));
        return subjects;
    }

    public void setSubjectToStudent(int studentid, int subjectid){
       

       studentServ.assignStudentToSub(subjectid, studentid);
    }


}
