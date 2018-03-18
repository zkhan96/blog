package co.uk.zohaibkhan.controllers;

import co.uk.zohaibkhan.entities.Role;
import co.uk.zohaibkhan.entities.User;
import co.uk.zohaibkhan.pojos.UserRegistration;
import co.uk.zohaibkhan.service.UserService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(value = "/register")
  public String register(@RequestBody UserRegistration userRegistration) {

    if (!userRegistration.getPassword().equals(userRegistration.getPasswordConfirmation())) {
      return "Passwords do not match";
    } else if (userService.getUser(userRegistration.getUsername()) != null) {
      return "User already exists";
    }
    userService.save(new User(userRegistration.getUsername(), userRegistration.getPassword(),
        Collections.singletonList(new Role("USER"))));
    return "User created";
  }

  @GetMapping(value = "/users")
  public List<User> users() {
    return userService.getAllUsers();
  }
}
