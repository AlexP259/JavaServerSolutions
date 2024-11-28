package org.spring_boot.gamestore.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class MethodOfReplenishment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ewallet_replenishment",
            joinColumns = @JoinColumn(name = "methodOfReplenishment_id"),
            inverseJoinColumns = @JoinColumn(name = "usersewallet_id")
    )
    private List<UsersEWallet> usersEWallet;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UsersEWallet> getUsersEWallet() {
        return usersEWallet;
    }

    public void setUsersEWallet(List<UsersEWallet> usersEWallet) {
        this.usersEWallet = usersEWallet;
    }
}
