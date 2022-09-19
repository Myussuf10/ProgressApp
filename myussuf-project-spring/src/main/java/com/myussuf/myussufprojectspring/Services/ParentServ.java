package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.Class;
import com.myussuf.myussufprojectspring.Entities.Parent;

import java.util.List;

public interface ParentServ {

    Parent getParent(int id);

    List<Parent> getParents();

    void saveParent(Parent parent);

    Parent getParentByEmail(String email);

    List<Class> getClassBySubjectId(int id);

}
