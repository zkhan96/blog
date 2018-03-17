package co.uk.zohaibkhan.service;

import co.uk.zohaibkhan.entities.Post;
import co.uk.zohaibkhan.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void insert(Post post) {
        postRepository.save(post);
    }
}
