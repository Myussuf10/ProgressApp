package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.Teacher;
import com.myussuf.myussufprojectspring.Repository.TeacherRepo;
import org.springframework.stereotype.Service;

@Service
public class TeacherServ {
    private TeacherRepo teacherRepo;

    public TeacherServ(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    public Teacher getTeacher(int teacherid){
       return teacherRepo.findById(teacherid).get();
    }
}
