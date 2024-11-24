package org.spring.securityregisterlogin.repository;

import org.spring.securityregisterlogin.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {
}
