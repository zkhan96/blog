package co.uk.zohaibkhan.controllers;

import co.uk.zohaibkhan.config.CustomUserDetails;
import co.uk.zohaibkhan.entities.Post;
import co.uk.zohaibkhan.service.PostService;
import co.uk.zohaibkhan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class BlogController {

  private final PostService postService;
  private UserService userService;

  @Autowired
  public BlogController(PostService postService, UserService userService) {
    this.postService = postService;
    this.userService = userService;
  }

  @GetMapping(value = "/posts")
  public List<Post> posts() {
    return postService.getAllPosts();
  }

  @PostMapping(value = "/post")
  public String publishPost(@RequestBody Post post) {
    CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (post.getDateCreated() == null) {
      post.setDateCreated(new Date());
    }
    post.setCreator(userService.getUser(userDetails.getUsername()));
    postService.insert(post);
    return "Post was published";
  }

  @GetMapping(value = "/posts/{username}")
  public List<Post> postsByUser(@PathVariable String username) {
    return postService.findByUser(userService.getUser(username));
  }
}
