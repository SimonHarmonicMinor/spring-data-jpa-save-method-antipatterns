package com.example.demo.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "post")
@Setter
@Getter
public class Post {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  public Long id;

  private String title;

  private String description;
}
