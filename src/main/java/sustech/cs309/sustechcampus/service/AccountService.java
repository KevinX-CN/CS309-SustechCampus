package sustech.cs309.sustechcampus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sustech.cs309.sustechcampus.model.Account;
import sustech.cs309.sustechcampus.model.Building;
import sustech.cs309.sustechcampus.repository.AccountRepository;

@Service
public class AccountService {

  private final AccountRepository accountRepository;

  @Autowired
  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
    initAccount();
  }

  public void addAccount(Account account) {
    List<Account> userByName = this.accountRepository.findAllByUsername(account.getUserName());
    if (userByName.size() > 0) {
      return;
    }
    this.accountRepository.save(account);
  }

  public boolean verifyAccount(String username, String password) {
    List<Account> userByName = this.accountRepository.findAllByUsername(username);
    if (userByName.size() == 0) {
      return false;
    }
    return userByName.get(0).verifyPassword(password);
  }

  public UUID getUidByUsername(String username) {
    List<Account> userByName = this.accountRepository.findAllByUsername(username);
    if (userByName.size() == 0) {
      return null;
    }
    return userByName.get(0).getUid();
  }

  public void initAccount() {
    List<Account> accountList = new ArrayList<>();
    accountList.add(new Account("admin", "admin"));
    accountList.add(new Account("abc", "123"));
    accountList.add(new Account("321", "cba"));
    this.accountRepository.saveAll(accountList);
  }
}
