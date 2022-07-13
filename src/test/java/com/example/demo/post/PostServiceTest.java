package com.example.demo.post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;

import com.example.demo.domain.Post;
import com.example.demo.repo.PostRepository;
import com.example.demo.repo.PostWithEmbeddedUUIDRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@AutoConfigureTestDatabase
@Transactional(propagation = NOT_SUPPORTED)
class PostServiceTest {

  @Autowired
  private PostRepository postRepository;
  @Autowired
  private PostWithEmbeddedUUIDRepository postWithEmbeddedUUIDRepository;
  @Autowired
  private PostService postService;

  @TestConfiguration
  static class Config {

    @Bean
    public PostService postService(
        PostRepository postRepository,
        PostWithEmbeddedUUIDRepository postWithEmbeddedUUIDRepository
    ) {
      return new PostService(postRepository, postWithEmbeddedUUIDRepository);
    }
  }

  @BeforeEach
  void beforeEach() {
    postRepository.deleteAll();
    postWithEmbeddedUUIDRepository.deleteAll();
  }

  @Test
  void shouldChangeTitle() {
    final var post = new Post();
    post.setTitle("oldTitle");
    postRepository.save(post);

    postService.changeTitle(post.getId(), "newTitle");

    final var changedPost = postRepository.findById(post.getId()).orElseThrow();
    assertEquals("newTitle", changedPost.getTitle(), "Unexpected post title");
  }

  @Test
  void shouldCreatePostWithUUID() {
    postService.createPostWithUUID("some_title");
  }

  @Test
  void test() {
    final var post = new Post();
    post.setTitle("oldTitle");
    postRepository.save(post);

    postService.changeTitle(post, "newTitle");
  }
}