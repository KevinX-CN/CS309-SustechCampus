package sustech.cs309.backend.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sustech.cs309.backend.service.UserService;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/login")
  @CrossOrigin
  public String getLoginView() {
    return "login";
  }

  @PostMapping(value = "/login")
  @CrossOrigin
  public boolean postLoginView(@RequestParam(value = "userName") String userName,@RequestParam(value = "password") String password) {
    System.out.println("Viewing");
    return true;
  }

  @GetMapping(value = "/user")
  @CrossOrigin
  public String getUserView() {
    return "user";
  }

  @PostMapping(value = "/user/")
  @CrossOrigin
  public boolean postUserView(@RequestParam(value = "userName") String userName,@RequestParam(value = "password") String password) {
    System.out.println("Viewing");
    return true;
  }
}
