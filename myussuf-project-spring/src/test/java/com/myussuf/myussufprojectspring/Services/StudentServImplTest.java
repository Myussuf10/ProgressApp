package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.*;
import com.myussuf.myussufprojectspring.Repository.ParentRepo;
import com.myussuf.myussufprojectspring.Repository.StudentRepo;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

//import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentServImplTest {

    private StudentServImpl underTest;
    @Mock private ParentServImpl parentServ;
    private AutoCloseable autoCloseable;
    @Mock private StudentRepo studentRepo;
    @Mock private ParentRepo parentRepo;


    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Before(value = "a")
    public void before(){

    }
    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new StudentServImpl(studentRepo);
//        parentServ = new ParentServImpl(parentRepo);
        Parent x = new Parent("Johnson","Philip","jphillip@gmail.com","123");
        Student student1 = new Student(
                1,
                "Mohamed",
                new HashSet<Attendance>(),
                "Yussuf",
                "10-10-1999",
                "Hounslow",
                new ArrayList<Subject>(),
                new HashSet<Comments>(),
                x
        );
    }

    @Test
    void getStudent() {
    }

    @Test
    void getAllStudents() {
        //when

        underTest.getAllStudents();
        verify(studentRepo).findAll();
        //method of repo was invoked using that repo
        //dont want to test real repository when were testing the service test
        //unit tests is faster, no need to create tables and bring databse add all students etc.
    }

    @Test
    void saveStudent() {
        Parent x = new Parent("Johnson","Philip","jphillip@gmail.com","123");
        Student student1 = new Student(
                1,
                "Mohamed",
                new HashSet<Attendance>(),
                "Yussuf",
                "10-10-1999",
                "Hounslow",
                new ArrayList<Subject>(),
                new HashSet<Comments>(),
                x
        );
        underTest.saveStudent(student1, x.getId());
        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);
        verify(studentRepo).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student1);
    }

    @Test
    @Disabled
    void assignStudentToSub() {
    }

    @Test
    void deleteStudent(Student student) {
        underTest.deleteStudent(student.getId());
        verify(studentRepo).delete(student);
    }

    @Test
    @Disabled
    void getStudentsPerSubject() {
    }
}