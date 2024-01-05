package com.example.backendproject.api;

import com.example.backendproject.domain.Comment;
import com.example.backendproject.domain.ShopList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment,Long> {
    List<Comment> findAll();
    List<Comment> findByIsvist(String name);

    Comment findByUsernameAndComment(String name ,String comment);
}
