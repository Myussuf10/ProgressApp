package com.myussuf.myussufprojectspring.Repository;

import com.myussuf.myussufprojectspring.Entities.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepo extends CrudRepository<Subject, Integer> {

    public Subject getById(int subjectid);
}
