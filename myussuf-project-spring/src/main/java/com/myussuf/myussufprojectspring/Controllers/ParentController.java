package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.Authority;
import com.myussuf.myussufprojectspring.Entities.Parent;
import com.myussuf.myussufprojectspring.Services.ParentServImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ParentController {

    private ParentServImpl parentServImpl;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:8080")
    public Parent getParent(@PathVariable Integer id){return parentServImpl.getParent(id);}

    @GetMapping("/parents")
    public List<Parent> allParents(){

        return parentServImpl.getParents();
    }
    @PostMapping("/parent")
    public void signUpParent(@RequestBody Parent parent){
        String encryptedPassword = passwordEncoder.encode(parent.getPassword());
        List<Authority> authoritiesList = new ArrayList<>();
        authoritiesList.add(createAuthority("ROLE_TEACHER"));
        parent.setPassword(encryptedPassword);
        parent.setAuthorities(authoritiesList);
        parentServImpl.saveParent(parent);
    }
    private Authority createAuthority(String role){
        Authority authority = new Authority();
        authority.setRoleName("ROLE_PARENT");
        return authority;
    }
}
