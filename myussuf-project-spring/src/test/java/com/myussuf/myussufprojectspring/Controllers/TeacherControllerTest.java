package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.*;
import com.myussuf.myussufprojectspring.Entities.Class;
import com.myussuf.myussufprojectspring.Services.StudentServImpl;
import com.myussuf.myussufprojectspring.Services.SubjectServImpl;
import com.myussuf.myussufprojectspring.Services.TeacherServImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class TeacherControllerTest {

    @MockBean
    TeacherServImpl teacherServ;
    @MockBean
    SubjectServImpl subjectServ;
    @MockBean
    StudentServImpl studentServ;

    @BeforeEach
    public void setup(){
        Teacher mark = new Teacher(
                "Mark","John",
                "adbb@gmail.com","123");
        Student student = new Student(3,"John",new HashSet<Attendance>(),"Mark","10-10-1999",
                "Hounslow",new ArrayList<Subject>(),new HashSet<Comments>(),new Parent());
        Student student2 = new Student();
        student.setSubjects(new ArrayList<Subject>());
        Class lesson = new Class();
        Subject x = new Subject(2,"Maths",mark,
                new ArrayList<Student>(),
                new ArrayList<Class>());
        student.setId(3);
        student.getSubjects().add(x);
        student2.getSubjects().add(x);
        Mockito.when(teacherServ.getTeacherByEmail("adbb@gmail.com")).thenReturn(mark);
    }

    @Test
    void getTeacherByEmail() {
        String name = "adbb@gmail.com";
        Teacher found = teacherServ.getTeacherByEmail(name);
        assertThat(found.getEmail()).isEqualTo(name);
    }

    @Test
    void setAttendance() {
    }

    @Test
    void getAttendance() {
    }

    @Test
    void getStudentPerSubject() {
        Student y = studentServ.getStudent(3);
       List<Student> found = teacherServ.getStudentsBySubject(2);
       assertThat(found.contains(y)).isTrue();

    }

    @Test
    void updateUnderstanding() {

    }

    @Test
    void getStudentById() {
    }
}