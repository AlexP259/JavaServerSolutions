package org.spring_boot.paymentsystem.repository;

import org.spring_boot.paymentsystem.entity.Mastercard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;


public interface MastercardRepository extends JpaRepository<Mastercard, Integer> {

    Optional<Mastercard> findByCardNumberAndValidityPeriodAndCvvAndFirstNameAndLastNameAndCity(String cardNumber, LocalDate validityPeriod, int cvv, String firstName, String lastName, String city);

}
