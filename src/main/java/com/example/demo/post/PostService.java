package com.example.demo.post;

import com.example.demo.domain.Post;
import com.example.demo.repo.PostRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
  private final PostRepository postRepository;
  @PersistenceContext
  private EntityManager em;

  @Transactional
  public void changeTitle(Long postId, String title) {
    final var post = postRepository.findById(postId).orElseThrow();
    post.setTitle(title);
    postRepository.save(post);
  }

  @Transactional
  public void changeTitle(Post post, String title) {
    post = em.merge(post);
    post.setTitle(title);
  }
}
