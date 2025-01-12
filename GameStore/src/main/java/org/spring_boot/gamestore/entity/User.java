package org.spring_boot.gamestore.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
    private boolean accountNonLocked;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UsersEWallet_id")
    private UsersEWallet eWallet;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_purchased_games",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private List<Game> purchasedGames;



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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UsersEWallet geteWallet() {
        return eWallet;
    }

    public void seteWallet(UsersEWallet eWallet) {
        this.eWallet = eWallet;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;

    }

    public List<Game> getPurchasedGames() {
        return purchasedGames;
    }

    public void setPurchasedGames(List<Game> purchasedGames) {
        this.purchasedGames = purchasedGames;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", eWallet=" + eWallet +
                '}';
    }
}



















