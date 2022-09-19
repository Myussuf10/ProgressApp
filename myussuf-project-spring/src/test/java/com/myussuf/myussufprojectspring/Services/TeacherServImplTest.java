package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.CommentsHelper;
import com.myussuf.myussufprojectspring.Entities.Student;
import com.myussuf.myussufprojectspring.Entities.Teacher;
import com.myussuf.myussufprojectspring.Repository.AttendanceRepo;
import com.myussuf.myussufprojectspring.Repository.CommentsRepo;
import com.myussuf.myussufprojectspring.Repository.TeacherRepo;
import com.myussuf.myussufprojectspring.security.userDetailsServices.AuthorityService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class TeacherServImplTest {

    private TeacherServImpl underTest;

    @Mock
    private TeacherRepo teacherRepo;
    @Mock
    private EmailSenderServ emailSenderServ;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthorityService authorityService;
    @Mock
    private StudentServImpl studentServ;
    @Mock
    private SubjectServImpl subjectServ;
    @Mock
    private ClassServImpl classServ;
    @Mock
    private AttendanceRepo attendanceRepo;
    @Mock
    private CommentsRepo commentsRepo;

    @BeforeEach
    void setup(){
        underTest = new TeacherServImpl(teacherRepo,emailSenderServ,passwordEncoder,authorityService
        ,studentServ,classServ,commentsRepo,attendanceRepo,subjectServ);
    }

    @Test
    void getAllTeachers() {
        underTest.getAllTeachers();
        verify(teacherRepo).findAll();
    }

    @Test
    void checkIfTeacherDoesNotExists(){
    }

    @Test
    void getTeacher() {
    }

    @Test
    void setComments() {
        Student x = new Student();
        Teacher y = new Teacher();
        y.setId(6);
        CommentsHelper comment = new CommentsHelper();
        underTest.setComments(x.getId(),y.getId(),comment);
        verify(teacherRepo).findById(6);
    }

}