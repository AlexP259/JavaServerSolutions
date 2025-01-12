package org.spring_boot.paymentsystem.service;

import org.spring_boot.paymentsystem.entity.base.Card;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface ICardService {

    Optional<Card> validateCard(String paymentSystem, String cardNumber, LocalDate validityPeriod, int cvv, String firstName, String lastName, String city);

    boolean writeOffMoney(String paymentSystem, String cardNumber, LocalDate validityPeriod, int cvv, String firstName, String lastName, String city, BigDecimal sum);

}
