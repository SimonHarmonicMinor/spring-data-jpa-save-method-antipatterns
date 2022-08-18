package com.example.demo.domain;

import static javax.persistence.GenerationType.IDENTITY;

import com.example.demo.domain.event.DomainEventPublisher;
import com.example.demo.domain.event.PostNameChanged;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationEventPublisher;

@Entity
@Table(name = "post")
@Setter
@Getter
public class Post {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  public Long id;

  private String title;

  public void changeTitle(String title) {
    this.title = title;
    DomainEventPublisher.publish(new PostNameChanged(id));
  }
}
