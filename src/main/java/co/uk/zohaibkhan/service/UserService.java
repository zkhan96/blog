package co.uk.zohaibkhan.service;

import co.uk.zohaibkhan.entities.User;
import co.uk.zohaibkhan.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository repo;

  @Autowired
  public UserService(UserRepository repo) {
    this.repo = repo;
  }

  @Bean
  public PasswordEncoder getPasswordEncoder(){
    return new BCryptPasswordEncoder();
  }

  public void save(User user){
    user.setPassword(getPasswordEncoder().encode(user.getPassword()));
    repo.save(user);
  }

  public User getUser(String username) {
    return repo.findByUsername(username);
  }

  public List<User> getAllUsers() {
    return repo.findAll();
  }
}
