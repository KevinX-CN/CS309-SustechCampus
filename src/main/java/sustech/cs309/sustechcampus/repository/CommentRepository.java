package sustech.cs309.sustechcampus.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sustech.cs309.sustechcampus.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

}