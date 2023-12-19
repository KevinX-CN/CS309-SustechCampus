package sustech.cs309.sustechcampus.controller;

import java.util.UUID;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sustech.cs309.sustechcampus.model.Account;
import sustech.cs309.sustechcampus.service.AccountService;

@RestController
@CrossOrigin
public class UserController {

  private final AccountService userService;

  public UserController(AccountService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/login")
  public String getLoginView() {
    return "Login.vue";
  }

  @PostMapping(value = {"/api/user/login", "/login"})
  public boolean postLoginView(@RequestParam(value = "username") String username,
    @RequestParam(value = "password") String password) {
    //return this.userService.verifyUser(username, password);
    return true;
  }

  @GetMapping(value = "/register")
  public String getRegister() {
    return "Register";
  }


  @PostMapping(value = {"/api/user/register", "/register"})
  public UUID apiUserRegister(@RequestParam(value = "username") String username,
    @RequestParam(value = "password") String password) {
    Account account = new Account(username, password);
    //return this.userService.addUser(user);
    return null;
  }
}
