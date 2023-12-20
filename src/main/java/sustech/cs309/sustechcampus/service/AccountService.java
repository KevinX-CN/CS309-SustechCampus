package sustech.cs309.sustechcampus.service;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sustech.cs309.sustechcampus.model.Account;
import sustech.cs309.sustechcampus.repository.AccountRepository;

@Service
public class AccountService {

  private final AccountRepository userRepository;

  @Autowired
  public AccountService(AccountRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void addAccount(Account account) {
    List<Account> userByName = this.userRepository.findAllByUsername(account.getUserName());
    if (userByName.size() > 0) {
      return;
    }
    this.userRepository.save(account);
  }

  public boolean verifyAccount(String username, String password) {
    List<Account> userByName = this.userRepository.findAllByUsername(username);
    if (userByName.size() == 0) {
      return false;
    }
    return userByName.get(0).verifyPassword(password);
  }

  public UUID getUidByUsername(String username) {
    List<Account> userByName = this.userRepository.findAllByUsername(username);
    if (userByName.size() == 0) {
      return null;
    }
    return userByName.get(0).getUid();
  }
}
