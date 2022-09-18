package com.myussuf.myussufprojectspring.Services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TeacherServImplTest {

    @Autowired
    private TeacherServImpl undertest;

    @Test
    void getAllTeachers() {

    }

    @Test
    void checkIfTeacherDoesNotExists(){}

    @Test
    void getTeacher() {
    }

    @Test
    void setComments() {
    }
}