package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.Admin;
import com.myussuf.myussufprojectspring.Repository.AdminRepo;
import com.myussuf.myussufprojectspring.Services.AdminServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class AdminController {
    private final AdminServ adminServ;

    @Autowired
    public AdminController(AdminServ adminServ){
        this.adminServ = adminServ;
    }

    @GetMapping("/admin")
    public List<Admin> getAdmin(){

        return adminServ.getAdmins();
    }

    @PostMapping("/admin")
    public String addAdmin(@RequestBody Admin newAdmin){
        adminServ.registerStudent(newAdmin);
        return "Completed";
    }

}
