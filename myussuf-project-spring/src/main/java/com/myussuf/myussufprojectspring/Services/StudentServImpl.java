package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.Parent;
import com.myussuf.myussufprojectspring.Entities.Student;
import com.myussuf.myussufprojectspring.Entities.Subject;
import com.myussuf.myussufprojectspring.Repository.ParentRepo;
import com.myussuf.myussufprojectspring.Repository.StudentRepo;
import com.myussuf.myussufprojectspring.Repository.SubjectRepo;
import com.myussuf.myussufprojectspring.exceptions.AuthException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@NoArgsConstructor
public class StudentServImpl implements StudentServ {
    private StudentRepo studentRepo;
    private SubjectServImpl subjectServ;
    private ParentServImpl parentServ;

    public StudentServImpl(StudentRepo studentRepo){
        this.studentRepo = studentRepo;
    }

    @Autowired
    public StudentServImpl(StudentRepo studentRepo, @Lazy
            SubjectServImpl subjectServ, @Lazy ParentServImpl parentServ) {
        this.studentRepo = studentRepo;
        this.subjectServ = subjectServ;
        this.parentServ = parentServ;
    }
    @Override
    public Student getStudent(Integer id){
     return studentRepo.findById(id).get();
    }

        public List<Student> getAllStudents(){
            List<Student> students = new ArrayList<>();
            studentRepo.findAll().forEach(student -> students.add(student));
            return students;
    }
    @Override
    public void saveStudent(Student student, int parentid) {
        Parent x = parentServ.getParent(parentid);
        student.setParent(x);
        studentRepo.save(student);
    }
    @Override
    public void assignStudentToSub(Student student){
        studentRepo.save(student);
    }
    @Override
    public void deleteStudent(int studentid){
        Student student = studentRepo.getById(studentid);

        if(student == null){
            throw new AuthException("Student not found");
        }
        studentRepo.delete(studentRepo.getById(studentid));

    }
    @Override
    public List<Student> getStudentsPerSubject(int subjectid){
        Subject subject = subjectServ.getSubject(subjectid);
        return studentRepo.getStudentsBySubjects(subject);
    }

}
