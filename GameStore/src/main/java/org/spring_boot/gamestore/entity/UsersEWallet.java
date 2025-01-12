package org.spring_boot.gamestore.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;


@Entity
public class UsersEWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(precision = 7, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    @OneToOne(mappedBy = "eWallet", cascade = CascadeType.ALL)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersEWallet")
    private List<MethodOfReplenishment> methodOfReplenishment;


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MethodOfReplenishment> getMethodOfReplenishment() {
        return methodOfReplenishment;
    }

    public void setMethodOfReplenishment(List<MethodOfReplenishment> methodOfReplenishment) {
        this.methodOfReplenishment = methodOfReplenishment;
    }

    @Override
    public String toString() {
        return  "ID = " + id +
                ", баланс = " + balance
                ;
    }
}
