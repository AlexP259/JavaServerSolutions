package org.spring_boot.gamestore.repository;

import org.spring_boot.gamestore.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {
}
