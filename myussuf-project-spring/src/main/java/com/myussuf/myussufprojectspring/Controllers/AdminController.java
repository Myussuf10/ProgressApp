package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.Admin;
import com.myussuf.myussufprojectspring.Entities.Student;
import com.myussuf.myussufprojectspring.Entities.Teacher;
import com.myussuf.myussufprojectspring.Services.AdminServImpl;
import com.myussuf.myussufprojectspring.Services.StudentServImpl;
import com.myussuf.myussufprojectspring.security.userDetailsServices.AuthRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/management")
@AllArgsConstructor
public class AdminController {
    private final AdminServImpl adminServImpl;
    private final PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;


    @GetMapping("/admin")
    public List<Admin> getAdmin(){

        return adminServImpl.getAdmins();
    }

    @PostMapping("/admin")
    public String addAdmin(@RequestBody Admin newAdmin){
        String encryptedPass = passwordEncoder.encode(newAdmin.getPassword());
        newAdmin.setPassword(encryptedPass);
        adminServImpl.saveAdmin(newAdmin);
        
        return "Completed";
    }
    @GetMapping("/{email}")
    public Admin getAdminByEmail(@PathVariable String email){
        return adminServImpl.getAdminByEmail(email);
    }

    @PatchMapping("/student/{studentid}")
    public Student updateStudent(@PathVariable int studentid, @RequestBody Map<Object, Object> updatedmap ){
        return adminServImpl.updateStudent(studentid
                , updatedmap);
    }

    @PatchMapping("/student/{subjectid}/{studentid}")
    public Student setSubject(@PathVariable int subjectid, @PathVariable int studentid){
        return adminServImpl.setSubject(subjectid, studentid);
    }

    @GetMapping("/user")
    public Object secured(Authentication authentication){
        return authentication.getPrincipal();
    }


}
