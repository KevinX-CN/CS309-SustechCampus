package sustech.cs309.sustechcampus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class PageController {

  @GetMapping("/login")
  public String getLoginView() {
    return "login";
  }

  @GetMapping(value = "/register")
  public String getRegister() {
    return "register";
  }

  @GetMapping(value = "/map")
  public String getMap() {
    return "map";
  }

}
