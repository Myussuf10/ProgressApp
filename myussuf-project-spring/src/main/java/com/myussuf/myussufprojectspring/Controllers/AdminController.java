package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.Admin;
import com.myussuf.myussufprojectspring.Services.AdminServImpl;
import com.myussuf.myussufprojectspring.Services.StudentServImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management")
public class AdminController {
    private final AdminServImpl adminServImpl;
    public final StudentServImpl studentServImpl;

    @Autowired
    public AdminController(AdminServImpl adminServImpl, StudentServImpl studentServImpl){
        this.adminServImpl = adminServImpl;
        this.studentServImpl = studentServImpl;
    }

    @GetMapping("/admin")
    public List<Admin> getAdmin(){

        return adminServImpl.getAdmins();
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
