package co.uk.zohaibkhan.controllers;

import co.uk.zohaibkhan.entities.Post;
import co.uk.zohaibkhan.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class BlogController {

  private final PostService postService;

  @Autowired
  public BlogController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping(value = "/")
  public String index() {
    return "index";
  }

  @GetMapping(value = "/posts")
  public List<Post> posts() {
    return postService.getAllPosts();
  }

  @PostMapping(value = "/post")
  public void publishPost(@RequestBody Post post) {
    if (post.getDateCreated() == null) {
      post.setDateCreated(new Date());
    }
    postService.insert(post);
  }
}
