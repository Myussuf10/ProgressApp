package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.Class;

import java.sql.Time;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public interface ClassServ {

    Class getClassDetails(int id);

    List<Class> getClassBySubject(int SubjectId);

    Class setUpClass(Class clas, int subjectId) throws ParseException;

}
