package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.Admin;
import com.myussuf.myussufprojectspring.Entities.Parent;
import com.myussuf.myussufprojectspring.Entities.Student;
import com.myussuf.myussufprojectspring.Repository.ParentRepo;
import com.myussuf.myussufprojectspring.Repository.StudentRepo;
import com.myussuf.myussufprojectspring.exceptions.AuthException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ParentServ {
    private ParentRepo parentRepo;
    private StudentRepo studentRepo;
    private EmailSenderServ emailSenderServ;

    @Autowired
    public ParentServ(ParentRepo parentRepo, StudentRepo studentRepo, EmailSenderServ emailSenderServ){
        this.studentRepo = studentRepo;
        this.parentRepo = parentRepo;
        this.emailSenderServ = emailSenderServ;
    }

    public Parent getParent(Integer id){
        return parentRepo.findById(id).get();
    }

    public List<Parent> getParents(){
        List<Parent> x = new ArrayList<>();
        parentRepo.findAll().forEach(x::add);
        return x;
    }

    public void saveParent(Parent parent) {
        //if(!student.getSubjects() == null)
        String em= "Auto generated email";
        String pass = "Your Generated password is " + parent.getPassword();
        emailSenderServ.sendEmail(parent.getEmail(),em, pass);
        parentRepo.save(parent);
    }
}
