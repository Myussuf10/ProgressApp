package com.myussuf.myussufprojectspring.Repository;

import com.myussuf.myussufprojectspring.Entities.Teacher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TeacherRepoTest {

    @Autowired
    private TeacherRepo underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByEmail() {

        //given
        Teacher ahmed = new Teacher("Ahmed","Jama", "ahmed@gmail.com", "hello123");
        underTest.save(ahmed);
        //when
        String email = underTest.findByEmail(ahmed.getEmail()).getEmail();

        //expected
    assertThat(email).isEqualTo("ahmed@gmail.com");
    }

    @Test
    void checkTeacherDoesNotExists(){
        //given
        Teacher ahmed = new Teacher("Ahmed","Jama", "ahmed@gmail.com", "hello123");

        //when
        boolean email = underTest.existsByEmail(ahmed.getEmail());
        //expected
        assertThat(email).isFalse();
    }
}