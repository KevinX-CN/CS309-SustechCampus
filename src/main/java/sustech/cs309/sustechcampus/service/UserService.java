package sustech.cs309.sustechcampus.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sustech.cs309.sustechcampus.model.User;
import sustech.cs309.sustechcampus.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UUID register(String username, String password) {
    User user = new User(username, password);
    this.userRepository.save(user);
    return user.getUid();
  }
}
