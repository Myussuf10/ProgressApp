package com.myussuf.myussufprojectspring.security.userDetailsServices;

import com.myussuf.myussufprojectspring.Entities.Admin;
import com.myussuf.myussufprojectspring.Entities.Parent;
import com.myussuf.myussufprojectspring.Entities.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
public class AppUser implements UserDetails {

    Teacher teacher;
    Parent parent;
    Admin admin;

    public AppUser(Teacher teacher){
        this.teacher = teacher;
    }
    public AppUser(Parent parent){
        this.parent = parent;
    }
    public AppUser(Admin admin){
        this.admin = admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return teacher.getAuthorities();
    }

    @Override
    public String getPassword() {
        return this.teacher.getPassword();
    }

    @Override
    public String getUsername() {
        return teacher.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
