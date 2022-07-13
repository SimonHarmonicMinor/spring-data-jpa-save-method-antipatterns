package com.example.demo.domain;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "post_uuid")
@Getter
@Setter
public class PostWithUUID {
  @Id
  public UUID id;

  private String title;

  public static PostWithUUID newPost() {
    final var post = new PostWithUUID();
    post.setId(UUID.randomUUID());
    return post;
  }
}
