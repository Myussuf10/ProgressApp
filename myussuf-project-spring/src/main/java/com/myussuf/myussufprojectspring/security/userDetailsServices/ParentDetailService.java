//package com.myussuf.myussufprojectspring.security.userDetailsServices;
//
//import com.myussuf.myussufprojectspring.Entities.Admin;
//import com.myussuf.myussufprojectspring.Entities.Authority;
//import com.myussuf.myussufprojectspring.Entities.Parent;
//import com.myussuf.myussufprojectspring.Repository.ParentRepo;
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//@AllArgsConstructor
//@Service
//public class ParentDetailService implements UserDetailsService {
//
//    ParentRepo parentRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        Parent parent = parentRepo.findByEmail(s);
//
//        if(null==parent){
//            throw new UsernameNotFoundException("User not found with username" + s);
//        }
//        return parent;
//    }
//
//
//}
