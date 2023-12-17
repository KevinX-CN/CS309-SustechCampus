package sustech.cs309.sustechcampus.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sustech.cs309.sustechcampus.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}