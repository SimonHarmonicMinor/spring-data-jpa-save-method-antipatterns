package com.example.demo.domain;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post_embedded_uuid")
@Getter
@Setter
public class PostWithEmbeddedUUID {
  @EmbeddedId
  public PostID id;
  private String title;

  public static PostWithEmbeddedUUID newPost() {
    final var post = new PostWithEmbeddedUUID();
    post.setId(new PostID(UUID.randomUUID()));
    return post;
  }

  @Embeddable
  @Data @AllArgsConstructor @NoArgsConstructor
  public static class PostID implements Serializable {

    @Column(name = "id")
    private UUID value;
  }
}
