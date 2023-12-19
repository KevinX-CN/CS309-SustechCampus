package sustech.cs309.sustechcampus.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sustech.cs309.sustechcampus.model.Account;

public interface AccountRepository extends JpaRepository<Account, UUID> {
  List<Account> findAllByUsername(String username);
}