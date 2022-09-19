package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.Parent;
import com.myussuf.myussufprojectspring.Entities.Student;
import com.myussuf.myussufprojectspring.Entities.Subject;
import com.myussuf.myussufprojectspring.Repository.StudentRepo;
import com.myussuf.myussufprojectspring.exceptions.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

public interface StudentServ {


    Student getStudent(Integer id);
    List<Student> getAllStudents();

    void saveStudent(Student student, int parentid) ;

    void assignStudentToSub(Student student);

    void deleteStudent(int studentid);

    List<Student> getStudentsPerSubject(int subjectid);
}
