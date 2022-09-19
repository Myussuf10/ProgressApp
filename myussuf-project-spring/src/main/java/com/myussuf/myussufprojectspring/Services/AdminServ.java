package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.Admin;
import com.myussuf.myussufprojectspring.Entities.Student;

import java.util.List;
import java.util.Map;

public interface AdminServ {

    Admin saveAdmin(Admin admin);

    List<Admin> getAdmins();

    Admin getAdminByEmail(String email);

    Student setSubject(int subid, int studentid);

    Student updateStudent(int id, Map<Object,Object> map);



}
