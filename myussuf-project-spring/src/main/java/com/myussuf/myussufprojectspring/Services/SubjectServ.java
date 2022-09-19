package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.Subject;
import com.myussuf.myussufprojectspring.Entities.Teacher;

import java.util.ArrayList;
import java.util.List;

public interface SubjectServ {

    public void saveSubject(Subject subject, int id);

    public Subject getSubject(int subjectid);

    public List<Subject> getAllSubjects();

}
