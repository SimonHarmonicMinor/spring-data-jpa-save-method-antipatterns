package com.example.demo.post;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.domain.PostWithEmbeddedUUID;
import com.example.demo.domain.event.PostNameChanged;
import com.example.demo.repo.PostRepository;
import com.example.demo.repo.PostWithEmbeddedUUIDRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

  private final PostRepository postRepository;
  private final PostWithEmbeddedUUIDRepository postWithEmbeddedUUIDRepository;
  @PersistenceContext
  private EntityManager em;

  @Transactional
  public void changeTitle(Long postId, String title) {
    final var post = postRepository.findById(postId).orElseThrow();
    post.changeTitle(title);
    postRepository.flush();
    // postRepository.save(post);
  }

  @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
  public void archiveChanges(PostNameChanged postNameChanged) {
    System.out.println("Archive changes " + postNameChanged.id());
  }

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void sendMessageToKafka(PostNameChanged postNameChanged) {
    System.out.println("Sent message to Kafka " + postNameChanged.id());
  }

  @Transactional
  public void createPostWithUUID(String title) {
    final var post = PostWithEmbeddedUUID.newPost();
    post.setTitle(title + "i");
    post.addComment(new Comment());
    postWithEmbeddedUUIDRepository.save(post);
  }

  @Transactional
  public void changeTitle(Post post, String title) {
    post = em.merge(post);
    post.setTitle(title);
  }
}
