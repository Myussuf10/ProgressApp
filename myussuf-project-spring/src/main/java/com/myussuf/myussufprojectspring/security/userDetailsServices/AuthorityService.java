package com.myussuf.myussufprojectspring.security.userDetailsServices;

import com.myussuf.myussufprojectspring.Entities.Authority;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AuthorityService {

    public Authority createAuthority(String role){
        Authority authority = new Authority();
        authority.setRoleName(role);
        return authority;
    }
}
