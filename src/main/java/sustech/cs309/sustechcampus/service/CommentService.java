package sustech.cs309.sustechcampus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sustech.cs309.sustechcampus.model.Comment;
import sustech.cs309.sustechcampus.repository.CommentRepository;

@Service
public class CommentService {

  private final CommentRepository commentRepository;

  @Autowired
  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public void addComment(Comment comment) {
    this.commentRepository.save(comment);
  }
}
