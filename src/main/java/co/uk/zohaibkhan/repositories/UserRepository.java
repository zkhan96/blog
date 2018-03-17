package co.uk.zohaibkhan.repositories;

import co.uk.zohaibkhan.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsername(String username);
}
