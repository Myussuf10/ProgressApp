package com.myussuf.myussufprojectspring.Repository;

import com.myussuf.myussufprojectspring.Entities.Student;
import com.myussuf.myussufprojectspring.Entities.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends CrudRepository<Student, Integer> {
    Student getById(Integer id);
    List<Student> getStudentsBySubjects(Subject subject);
}
