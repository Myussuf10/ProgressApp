package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.Attendance;
import com.myussuf.myussufprojectspring.Entities.Authority;
import com.myussuf.myussufprojectspring.Entities.Class;
import com.myussuf.myussufprojectspring.Entities.Parent;
import com.myussuf.myussufprojectspring.Services.ParentServImpl;
import com.myussuf.myussufprojectspring.Services.TeacherServImpl;
import com.myussuf.myussufprojectspring.security.userDetailsServices.AuthorityService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/parent")
@AllArgsConstructor
public class ParentController {

    private ParentServImpl parentServImpl;
    private final PasswordEncoder passwordEncoder;
    private TeacherServImpl teacherServ;
    private AuthorityService authorityService;

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:8080")
    public Parent getParent(@PathVariable Integer id){return parentServImpl.getParent(id);}

    @GetMapping("/parent/{email}")
    public Parent getParentByEmail(@PathVariable String email){
        return parentServImpl.getParentByEmail(email);
    }

    @GetMapping("/attendance/{studentid}/{subjectid}")
    public List<Attendance> getAttendanceByStudent(@PathVariable int studentid, @PathVariable int subjectid){
        return teacherServ.getSingleAttendance(studentid,subjectid);
    }

}
