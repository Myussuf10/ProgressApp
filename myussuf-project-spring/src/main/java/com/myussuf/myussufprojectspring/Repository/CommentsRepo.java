package com.myussuf.myussufprojectspring.Repository;

import com.myussuf.myussufprojectspring.Entities.Comments;
import com.myussuf.myussufprojectspring.Entities.Student;
import com.myussuf.myussufprojectspring.Entities.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface CommentsRepo extends CrudRepository<Comments,Integer> {

//    List<Comments> findCommentsByTeacherAndStudentIn(Teacher teacher, Set<Student> student);
//
//    List<Comments> findCommentsByTeacher(Teacher teacher);
    void deleteAllById(int commentid);

}
