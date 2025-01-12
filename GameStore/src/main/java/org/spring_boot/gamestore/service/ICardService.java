package org.spring_boot.gamestore.service;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ICardService {

    boolean validateCard(String paymentSystem, String cardNumber, LocalDate validityPeriod, int cvv, String firstName, String lastName, String city);

    boolean upBalance(String paymentSystem, String cardNumber, LocalDate validityPeriod, int cvv, String firstName, String lastName, String city, int eWalletId, BigDecimal sum);

}
