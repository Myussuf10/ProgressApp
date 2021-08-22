package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.Subject;
import com.myussuf.myussufprojectspring.Repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServ {
    private SubjectRepo subjectRepo;

    @Autowired
    public SubjectServ(SubjectRepo subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

    public void saveSubject(Subject subject){
        subjectRepo.save(subject);
    }

    public Subject getSubject(int subjectid){
        return subjectRepo.getById(subjectid);
    }
}
