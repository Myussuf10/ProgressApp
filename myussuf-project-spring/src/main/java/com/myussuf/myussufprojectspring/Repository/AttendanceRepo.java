package com.myussuf.myussufprojectspring.Repository;

import com.myussuf.myussufprojectspring.Entities.Attendance;
import com.myussuf.myussufprojectspring.Entities.Class;
import com.myussuf.myussufprojectspring.Entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepo extends CrudRepository<Attendance, Integer> {

    boolean existsAttendanceByRegister(Class register);
    Attendance findAttendanceByRegister(Class register);
    Attendance findAttendanceByStudentAndRegister(Student student, Class lesson);
}
