package com.myussuf.myussufprojectspring.security.userDetailsServices;

import com.myussuf.myussufprojectspring.Entities.Admin;
import com.myussuf.myussufprojectspring.Repository.AdminRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class AdminDetailService  implements UserDetailsService {

    AdminRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Admin admin = adminRepo.findByEmail(s);

        if(null==admin){
            throw new UsernameNotFoundException("User not found with username" + s);
        }
        return admin;

    }
}
