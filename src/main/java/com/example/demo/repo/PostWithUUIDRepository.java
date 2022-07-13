package com.example.demo.repo;

import com.example.demo.domain.PostWithUUID;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostWithUUIDRepository extends JpaRepository<PostWithUUID, UUID> {

}
