package org.spring_boot.paymentsystem.repository;

import org.spring_boot.paymentsystem.entity.Visa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;


public interface VisaRepository extends JpaRepository<Visa, Integer> {

    Optional<Visa> findByCardNumberAndValidityPeriodAndCvvAndFirstNameAndLastNameAndCity(String cardNumber, LocalDate validityPeriod, int cvv, String firstName, String lastName, String city);

}
