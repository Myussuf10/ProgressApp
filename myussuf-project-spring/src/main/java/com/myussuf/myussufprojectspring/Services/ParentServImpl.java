package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.*;
import com.myussuf.myussufprojectspring.Entities.Class;
import com.myussuf.myussufprojectspring.Repository.ParentRepo;
import com.myussuf.myussufprojectspring.Repository.StudentRepo;
import com.myussuf.myussufprojectspring.exceptions.AuthException;
import com.myussuf.myussufprojectspring.security.userDetailsServices.AuthorityService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class ParentServImpl implements UserDetailsService, ParentServ {
    private ParentRepo parentRepo;
    private StudentRepo studentRepo;
    private EmailSenderServ emailSenderServ;
    private PasswordEncoder passwordEncoder;
    private AuthorityService authorityService;
    private ClassServImpl classServ;
    private SubjectServImpl subjectServ;

    public ParentServImpl(ParentRepo parentRepo) {
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Parent parent = parentRepo.findByEmail(s);
        if(parent==null){
            throw new UsernameNotFoundException("Username not found");
        }
        return new User(parent.getEmail(),parent.getPassword(), parent.getAuthorities());
    }

    @Override
    public Parent getParent(int id){
        return parentRepo.findById(id).get();
    }

    @Override
    public List<Parent> getParents(){
        List<Parent> x = new ArrayList<>();
        parentRepo.findAll().forEach(x::add);
        return x;
    }

    @Override
    public void saveParent(Parent parent) {
        //if(!student.getSubjects() == null)
        String em= "Auto generated email";
        String pass = "Your Generated password is " + parent.getPassword();
        emailSenderServ.sendEmail(parent.getEmail(),em, pass);
        String encryptedPassword = passwordEncoder.encode(parent.getPassword());
        List<Authority> authoritiesList = new ArrayList<>();
        authoritiesList.add(authorityService.createAuthority("ROLE_PARENT"));
        parent.setPassword(encryptedPassword);
        parent.setAuthorities(authoritiesList);
        parentRepo.save(parent);
    }

    @Override
    public Parent getParentByEmail(String email){
        return parentRepo.findByEmail(email);
    }

    @Override
    public List<Class> getClassBySubjectId(int subjectid){
        return classServ.findClassesBySubjectId(subjectid);
    }

}
