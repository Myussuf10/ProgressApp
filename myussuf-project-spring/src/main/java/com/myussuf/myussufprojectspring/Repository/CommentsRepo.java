package com.myussuf.myussufprojectspring.Repository;

import com.myussuf.myussufprojectspring.Entities.Comments;
import com.myussuf.myussufprojectspring.Entities.CommentsKey;
import org.springframework.data.repository.CrudRepository;

public interface CommentsRepo extends CrudRepository<Comments,CommentsKey> {
}
