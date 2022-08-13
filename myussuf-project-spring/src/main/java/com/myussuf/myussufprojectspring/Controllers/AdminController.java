package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.Admin;
import com.myussuf.myussufprojectspring.Services.AdminServImpl;
import com.myussuf.myussufprojectspring.Services.StudentServImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management")
@AllArgsConstructor
public class AdminController {
    private final AdminServImpl adminServImpl;
    public final StudentServImpl studentServImpl;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/admin")
    public List<Admin> getAdmin(){

        return adminServImpl.getAdmins();
    }

//    public Admin getOne(Integer id){
//
//    }

    @PostMapping("/admin")
    public String addAdmin(@RequestBody Admin newAdmin){
        String encryptedPass = passwordEncoder.encode(newAdmin.getPassword());
        newAdmin.setPassword(encryptedPass);
        adminServImpl.saveAdmin(newAdmin);
        
        return "Completed";
    }

}
