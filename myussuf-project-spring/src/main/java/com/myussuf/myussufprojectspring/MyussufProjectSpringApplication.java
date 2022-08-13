package com.myussuf.myussufprojectspring;

import com.myussuf.myussufprojectspring.Entities.Authority;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MyussufProjectSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyussufProjectSpringApplication.class, args);
	}

	@PostConstruct
	protected void init(){
		List<Authority> authorities = new ArrayList<>();
		authorities.add(new Authority(null,"ROLE_TEACHER"));
		authorities.add(new Authority(null,"ROLE_ADMIN"));
		authorities.add(new Authority(null,"ROLE_PARENT"));

	}

}
