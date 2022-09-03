package com.example.demo.domain;

import static javax.persistence.CascadeType.PERSIST;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Entity
@Table(name = "post_embedded_uuid")
@Getter
@Setter
public class PostWithEmbeddedUUID {
  @EmbeddedId
  public PostID id;
  private String title;

  @OneToMany(mappedBy = "post", cascade = PERSIST)
  private List<Comment> comments = new ArrayList<>();

  public void addComment(Comment comment) {
    comment.setPost(this);
    comments.add(comment);
  }

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
