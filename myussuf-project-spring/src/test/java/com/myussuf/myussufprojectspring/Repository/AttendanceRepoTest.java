package com.myussuf.myussufprojectspring.Repository;

import com.myussuf.myussufprojectspring.Entities.Attendance;
import com.myussuf.myussufprojectspring.Entities.Class;
import com.myussuf.myussufprojectspring.Entities.Student;
import com.myussuf.myussufprojectspring.Services.*;
import com.myussuf.myussufprojectspring.security.userDetailsServices.AuthorityService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = AttendanceRepo.class)
class AttendanceRepoTest {

    @Mock
    private TeacherRepo teacherRepo;
    @Mock private EmailSenderServ emailSenderServ;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private AuthorityService authorityService;
    @Mock private StudentServImpl studentServ;
    @Mock private SubjectServImpl subjectServ;
    @Mock private ClassServImpl classServ;
    @Mock private AttendanceRepo attendanceRepo;
    @Mock private CommentsRepo commentsRepo;
    @Mock private AutoCloseable autoCloseable;
    @Mock
    private TeacherServImpl underTest;

    @BeforeEach
    void setup(){
        underTest = new TeacherServImpl(teacherRepo,emailSenderServ,passwordEncoder,authorityService
                ,studentServ,classServ,commentsRepo,attendanceRepo,subjectServ);
    }

    @Test
    void existsAttendanceByRegister() {
        Class x = new Class();
        Attendance attendance = new Attendance(1,new HashSet<>(), x,"good");

    }

    @Test
    void findAttendanceByRegister() {
    }

    @Test
    void findAttendanceByStudentAndRegister() {
        Student s = new Student();
        Class lesson = new Class();
        underTest.updateUnderstanding(s.getId(),lesson.getId(),new HashMap<>());
        verify(attendanceRepo).findAttendanceByStudentAndRegister(s,lesson);
    }
}