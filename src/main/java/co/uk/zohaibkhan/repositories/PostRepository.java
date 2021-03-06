package co.uk.zohaibkhan.repositories;

import co.uk.zohaibkhan.entities.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  List<Post> findByCreatorId(Long id);
}
