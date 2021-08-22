package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.Admin;
import com.myussuf.myussufprojectspring.Entities.Student;
import com.myussuf.myussufprojectspring.Repository.AdminRepo;
import com.myussuf.myussufprojectspring.Repository.StudentRepo;
import com.myussuf.myussufprojectspring.exceptions.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class AdminServ {
    private AdminRepo adminRepo;
    private StudentRepo studentRepo;

    @Autowired
    public AdminServ(AdminRepo adminRepo, StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
        this.adminRepo = adminRepo;
    }

    public String registerStudent(Student student) throws AuthException{
        String name = student.getFirstname();
        System.out.println(name);
        studentRepo.save(student);
        return "Completed";
//        System.out.println(newAdmin);
//        String email = newAdmin.getEmail();
//        if(email != null) { email = email.toLowerCase();}
//        String regex = "^(.+)@(.+)$";
//        Pattern pattern = Pattern.compile(regex);
//        if (!pattern.matcher(email).matches()){
//            throw new AuthException("invalid email format");
//        }
//        adminRepo.save(newAdmin);
    }

    public List<Admin> getAdmins(){
        List<Admin> x = new ArrayList<>();
        adminRepo.findAll().forEach(x::add);
        return x;
    }
}
