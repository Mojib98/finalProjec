package com.finalProject.Project.repository.interfaces;

import com.finalProject.Project.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository  extends CrudRepository<Comment,Integer> {
}
