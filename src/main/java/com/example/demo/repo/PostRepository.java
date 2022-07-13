package com.example.demo.repo;

import com.example.demo.domain.Post;
import com.example.demo.repo.custom.HibernateRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository
    extends JpaRepository<Post, Long>, HibernateRepository<Post> {

}
