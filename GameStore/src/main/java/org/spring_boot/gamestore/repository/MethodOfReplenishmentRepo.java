package org.spring_boot.gamestore.repository;

import org.spring_boot.gamestore.entity.MethodOfReplenishment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MethodOfReplenishmentRepo extends JpaRepository<MethodOfReplenishment, Integer> {
}
