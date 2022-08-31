//package com.myussuf.myussufprojectspring.security.userDetailsServices;
//
//import com.myussuf.myussufprojectspring.Entities.Admin;
//import com.myussuf.myussufprojectspring.Entities.Teacher;
//import com.myussuf.myussufprojectspring.Repository.TeacherRepo;
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@AllArgsConstructor
//@Service
//public class TeacherDetailService implements UserDetailsService {
//
//    TeacherRepo teacherRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        Teacher teacher = teacherRepo.findByEmail(s);
//
//        if(null==teacher){
//            throw new UsernameNotFoundException("User not found with username" + s);
//        }
//        return teacher;
//
//    }
//}
