package org.springsecurity.securityappproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springsecurity.securityappproject.entity.Person;

public interface EmpRepo extends JpaRepository<Person, Integer> {
    public Person findByEmail(String email);
}
