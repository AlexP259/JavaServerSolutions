package org.spring_boot.paymentsystem.dto;

import java.time.LocalDate;

public class CardRequest {
    private String paymentSystem;
    private String cardNumber;
    private LocalDate validityPeriod; // Срок действия карты
    private int cvv;
    private String firstName;
    private String lastName;
    private String city;

    public CardRequest(String paymentSystem, String cardNumber, LocalDate validityPeriod, int cvv, String firstName, String lastName, String city) {
        this.paymentSystem = paymentSystem;
        this.cardNumber = cardNumber;
        this.validityPeriod = validityPeriod;
        this.cvv = cvv;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    public String getPaymentSystem() {
        return paymentSystem;
    }

    public void setPaymentSystem(String paymentSystem) {
        this.paymentSystem = paymentSystem;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(LocalDate validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
