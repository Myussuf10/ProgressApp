package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.Student;
import com.myussuf.myussufprojectspring.Repository.ParentRepo;
import com.myussuf.myussufprojectspring.Repository.StudentRepo;
import com.myussuf.myussufprojectspring.exceptions.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentServ {
    private StudentRepo studentRepo;
    private ParentRepo parentRepo;

    @Autowired
    public StudentServ(StudentRepo studentRepo, ParentRepo parentRepo) {
        this.studentRepo = studentRepo;
        this.parentRepo = parentRepo;
    }

    public Student getStudent(Integer id){
     return studentRepo.findById(id).get();
    }

        public List<Student> getAllStudents(){
            List<Student> students = new ArrayList<>();
            studentRepo.findAll().forEach(student -> students.add(student));
            return students;
    }

    public void saveStudent(Student student, int parentid) {
        System.out.println(student);
        //if(!student.getSubjects() == null)
        parentRepo.findById(parentid)
                .map(stud -> {student.setParent(stud);
                    return studentRepo.save(student);
                })
                .orElseThrow(()-> new AuthException("Parent Not found"));
    }

    public void deleteStudent(int studentid){
        Student student = studentRepo.getById(studentid);

        if(student == null){
            throw new AuthException("Student not found");
        }
        studentRepo.delete(studentRepo.getById(studentid));

    }

}
