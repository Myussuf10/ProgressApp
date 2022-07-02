package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.Admin;
import com.myussuf.myussufprojectspring.Entities.Parent;
import com.myussuf.myussufprojectspring.Repository.ParentRepo;
import com.myussuf.myussufprojectspring.Repository.StudentRepo;
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

    @Autowired
    public ParentServ(ParentRepo parentRepo, StudentRepo studentRepo){
        this.studentRepo = studentRepo;
        this.parentRepo = parentRepo;
    }

    public Parent getParent(Integer id){
        return parentRepo.findById(id).get();
    }

    public List<Parent> getParents(){
        List<Parent> x = new ArrayList<>();
        parentRepo.findAll().forEach(x::add);
        return x;
    }
}
