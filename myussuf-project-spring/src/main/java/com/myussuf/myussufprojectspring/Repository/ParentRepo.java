package com.myussuf.myussufprojectspring.Repository;

import com.myussuf.myussufprojectspring.Entities.Parent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepo extends CrudRepository<Parent, Integer> {
}
