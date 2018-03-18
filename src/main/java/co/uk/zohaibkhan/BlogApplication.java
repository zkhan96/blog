package co.uk.zohaibkhan;

import co.uk.zohaibkhan.config.CustomUserDetails;
import co.uk.zohaibkhan.entities.Role;
import co.uk.zohaibkhan.entities.User;
import co.uk.zohaibkhan.repositories.UserRepository;
import co.uk.zohaibkhan.service.UserService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogApplication {

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public BlogApplication(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

  @Autowired
  public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserService service) throws Exception {
    //Setup a default user if db is empty
    if (repository.count()==0)
      service.save(new User("user", "user", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
    builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
  }

  private UserDetailsService userDetailsService(final UserRepository repository) {
    return username -> new CustomUserDetails(repository.findByUsername(username));
  }
}
