package sustech.cs309.sustechcampus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sustech.cs309.sustechcampus.repository.AccountRepository;

@Service
public class AccountService {

  private final AccountRepository userRepository;

  @Autowired
  public AccountService(AccountRepository userRepository) {
    this.userRepository = userRepository;
  }

  /*public UUID addUser(User user) {
    List<User> userByName = this.userRepository.findAllByUserName(user.getUserName());
    if (userByName.size() > 0) {
      return null;
    }
    this.userRepository.save(user);
    return user.getUid();
  }

  public boolean verifyUser(String username, String password) {
    User user = new User(username, password);
    List<User> userByName = this.userRepository.findAllByUserName(username);
    if (userByName.size() == 0) {
      return false;
    }
    return userByName.get(0).verifyPassword(password);
  }

  public UUID getUidByUsername(String username) {
    List<User> userByName = this.userRepository.findAllByUserName(username);
    if (userByName.size() == 0) {
      return null;
    }
    return userByName.get(0).getUid();
  }*/
}
