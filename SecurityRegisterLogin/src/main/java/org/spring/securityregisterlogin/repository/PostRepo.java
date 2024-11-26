package org.spring.securityregisterlogin.repository;

import org.spring.securityregisterlogin.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByCategory(String category);

    List<Post> findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(String ch, String ch2);

    Page<Post> findByCategory(Pageable pageable, String category);

    Page<Post> findAllBy(Pageable pageable);

}
