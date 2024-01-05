package sustech.cs309.sustechcampus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sustech.cs309.sustechcampus.model.Account;
import sustech.cs309.sustechcampus.model.Account.authorityType;
import sustech.cs309.sustechcampus.repository.AccountRepository;

@Service
public class AccountService {

  private final AccountRepository accountRepository;

  @Autowired
  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
    initAccount();
  }

  public boolean addAccount(Account account) {
    List<Account> userByName = this.accountRepository.findAllByUsername(account.getUserName());
    if (userByName.size() == 0) {
      this.accountRepository.save(account);
      return true;
    } else {
      return false;
    }
  }

  public UUID verifyAccount(String username, String password) {
    List<Account> accountByName = this.accountRepository.findAllByUsername(username);
    if (accountByName.size() != 1) {
      return null;
    }
    Account account = accountByName.get(0);
    if (account.verifyPassword(password)) {
      return account.getUid();
    } else {
      return null;
    }
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
    accountList.add(new Account("admin", "admin", authorityType.Admin));
    accountList.add(new Account("user1", "user1", authorityType.User));
    accountList.add(new Account("user2", "user2", authorityType.User));
    this.accountRepository.saveAll(accountList);
  }
}
