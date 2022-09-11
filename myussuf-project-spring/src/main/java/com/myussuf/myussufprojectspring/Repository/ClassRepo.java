package com.myussuf.myussufprojectspring.Repository;

import com.myussuf.myussufprojectspring.Entities.Class;
import com.myussuf.myussufprojectspring.Entities.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface ClassRepo extends CrudRepository<Class, Integer> {
     public static final String x = "SELECT * FROM Class, Subject where aClass.id=Subject.id";

     Class getClassById(Integer id);

     Class findClassBySubject(Subject subject);

     Class findClassBySubjectId(int subjectid);

     List<Class> findClassesBySubjectId(int subjectid);

     @Query(value = "SELECT C.id, C.dow FROM Class C WHERE C.subject.id=:subid")
     Class findBySubjectId(@Param("subid") Integer id);
}
