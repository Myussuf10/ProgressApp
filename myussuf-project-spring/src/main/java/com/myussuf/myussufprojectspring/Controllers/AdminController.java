package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.Admin;
import com.myussuf.myussufprojectspring.Entities.Student;
import com.myussuf.myussufprojectspring.Services.AdminServ;
import com.myussuf.myussufprojectspring.Services.StudentServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management")
public class AdminController {
    private final AdminServ adminServ;
    public final StudentServ studentServ;

    @Autowired
    public AdminController(AdminServ adminServ, StudentServ studentServ){
        this.adminServ = adminServ;
        this.studentServ = studentServ;
    }

    @GetMapping("/admin")
    public List<Admin> getAdmin(){

        return adminServ.getAdmins();
    }

//    public Admin getOne(Integer id){
//
//    }

    @PostMapping("/admin")
    public String addAdmin(@RequestBody Admin newAdmin){
        //adminServ.registerStudent(newAdmin);
        return "Completed";
    }

}
