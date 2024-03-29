package com.example.demo.repo;

import com.example.demo.domain.PostWithEmbeddedUUID;
import com.example.demo.domain.PostWithEmbeddedUUID.PostID;
import com.example.demo.repo.custom.HibernateRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostWithEmbeddedUUIDRepository extends JpaRepository<PostWithEmbeddedUUID, PostID>,
    HibernateRepository<PostWithEmbeddedUUID> {

}
