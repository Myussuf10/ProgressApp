package com.myussuf.myussufprojectspring.Repository;

import com.myussuf.myussufprojectspring.Entities.Parent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepo extends CrudRepository<Parent, Integer> {

}
