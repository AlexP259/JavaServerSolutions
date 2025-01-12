package org.spring_boot.paymentsystem.repository;

import org.spring_boot.paymentsystem.entity.Mir;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;


public interface MirRepository extends JpaRepository<Mir, Integer> {

    Optional<Mir> findByCardNumberAndValidityPeriodAndCvvAndFirstNameAndLastNameAndCity(String cardNumber, LocalDate validityPeriod, int cvv, String firstName, String lastName, String city);

}
