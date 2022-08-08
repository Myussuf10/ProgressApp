package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.Teacher;
import com.myussuf.myussufprojectspring.Repository.TeacherRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherServImpl {
    private TeacherRepo teacherRepo;
    private EmailSenderServ emailSenderServ;

    public Teacher getTeacher(int teacherid){
       return teacherRepo.findById(teacherid).get();
    }

    public void saveTeacher(Teacher teacher){
        String em= "Auto generated email";
        String pass = "Your Generated password is " + teacher.getPassword();
        emailSenderServ.sendEmail(teacher.getEmail(),em, pass);
        teacherRepo.save(teacher);
    }
}
