package com.myussuf.myussufprojectspring.Repository;

import com.myussuf.myussufprojectspring.Entities.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TeacherRepoTest {

    @Autowired
    private TeacherRepo underTest;

    @Test
    void findByEmail() {
        Teacher ahmed = new Teacher("Ahmed","Jama", "ahmed@gmail.com", "hello123");
        underTest.save(ahmed);
        String email = underTest.findByEmail(ahmed.getEmail()).getEmail();
    assertThat(email).isEqualTo("ahmed@gmail.com");
    }
}