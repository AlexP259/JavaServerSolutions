package org.spring_boot.gamestore.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class MethodOfReplenishment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String paymentSystem;
    private String cardNumber;
    private LocalDate validityPeriod;
    private int cvv;

    private String firstName;
    private String lastName;
    private String city;

    @ManyToOne
    @JoinColumn(name = "users_ewallet_id")
    private UsersEWallet usersEWallet;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UsersEWallet getUsersEWallet() {
        return usersEWallet;
    }

    public void setUsersEWallet(UsersEWallet usersEWallet) {
        this.usersEWallet = usersEWallet;
    }

    @Override
    public String toString() {
        return "MethodOfReplenishment{" +
                "id=" + id +
                ", paymentSystem='" + paymentSystem + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", validityPeriod=" + validityPeriod +
                ", cvv=" + cvv +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", usersEWallet=" + usersEWallet +
                '}';
    }
}
