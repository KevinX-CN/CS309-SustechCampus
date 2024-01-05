package com.example.backendproject.service;

import com.example.backendproject.api.CommentRepository;
import com.example.backendproject.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAll(){return commentRepository.findAll();}

    public List<Comment> findBytrue(){
        return commentRepository.findByIsvist("yes");
    }
    public List<Comment> findByfalse(){
        return commentRepository.findByIsvist("no");
    }
    public Comment findByUsernameaAndComment(String name ,String comment){
        return commentRepository.findByUsernameAndComment(name,comment);
    }
    public void save(Comment tem) {
        commentRepository.save(tem);
    }
}
