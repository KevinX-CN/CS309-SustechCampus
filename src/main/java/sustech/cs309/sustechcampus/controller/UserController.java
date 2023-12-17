package sustech.cs309.sustechcampus.controller;

import java.util.UUID;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sustech.cs309.sustechcampus.service.UserService;

@RestController
@CrossOrigin
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/login")
  public String getLoginView() {
    return "login";
  }

  @PostMapping(value = "/login")
  public boolean postLoginView(@RequestParam(value = "userName") String userName,
    @RequestParam(value = "password") String password) {
    System.out.println("Viewing");
    return true;
  }

  @GetMapping(value = "/register")
  public String getRegister() {
    return "Register";
  }

  @PostMapping(value = "/register")
  public UUID apiUserRegister(@RequestParam(value = "username") String username,
    @RequestParam(value = "password") String password) {
    return this.userService.register(username, password);
  }

  @GetMapping(value = "/user")
  public String getUserView() {
    return "user";
  }

  @PostMapping(value = "/user")
  public boolean postUserView(@RequestParam(value = "userName") String userName,
    @RequestParam(value = "password") String password) {
    System.out.println("Viewing");
    return true;
  }
}
