package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.*;
import com.myussuf.myussufprojectspring.Entities.Class;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface TeacherServ {


    public List<Teacher> getAllTeachers();

    public Teacher getTeacherByEmail(String email);

    public Teacher getTeacher(Integer id);

    public void saveTeacher(Teacher teacher);

    public Student setComments(int studentid, int teacherid, CommentsHelper comments);

    public Attendance recordAttendance(HelperAttendance student, int classid);

    public List<Attendance> getattendance();

    public List<Student> getStudentsBySubject(int subjectid);

    public Attendance updateUnderstanding(int studentid, int classid, Map<Object, Object> updatedmap);

    public List<Attendance> getSingleAttendance(int student, int subjectid);

    public void deleteComment(int commentid, int studentid);
}
