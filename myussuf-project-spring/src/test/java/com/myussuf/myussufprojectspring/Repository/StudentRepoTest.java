package com.myussuf.myussufprojectspring.Repository;

import com.myussuf.myussufprojectspring.Entities.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepoTest {

    @Autowired
    private StudentRepo underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void getStudentsBySubjects() {
        Parent parent = new Parent("Ahmed", "Jama","123@gmail.com","123");
        Subject english = new Subject();
        Student student = new Student(1,"Mohamed", new HashSet<Attendance>(),"Ahmed", "10-10-1999", "Hounslow",new ArrayList<Subject>(),
                new HashSet<Comments>(), parent);
        student.getSubjects().add(english);
        underTest.save(student);
        String lastname = student.getLastname();
        assertThat(student.getLastname()).isEqualTo("Ahmed");
        assertThat(english).isIn(student.getSubjects());
    }
}