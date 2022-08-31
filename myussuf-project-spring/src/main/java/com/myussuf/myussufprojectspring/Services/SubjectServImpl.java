package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.Student;
import com.myussuf.myussufprojectspring.Entities.Subject;
import com.myussuf.myussufprojectspring.Entities.Teacher;
import com.myussuf.myussufprojectspring.Repository.SubjectRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class SubjectServImpl {
    private SubjectRepo subjectRepo;
    private StudentServImpl studentServ;
    private @Lazy TeacherServImpl teacherServ;

    public void saveSubject(Subject subject, int id){
        Teacher x = teacherServ.getTeacher(id);
        subject.setTeacher(x);
        subjectRepo.save(subject);
    }

    public Subject getSubject(int subjectid){
        return subjectRepo.findById(subjectid).get();
    }

    public List<Subject> getAllSubjects(){
        List<Subject> subjects = new ArrayList<>();
        subjectRepo.findAll().forEach(subject -> subjects.add(subject));
        return subjects;
    }


}
