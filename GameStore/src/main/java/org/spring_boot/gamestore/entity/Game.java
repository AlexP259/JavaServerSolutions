package org.spring_boot.gamestore.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String name;

    @Column(length = 2000)
    private String description;

    private String platform;

    private String genre;

    @Column(length = 2000)
    private String systemRequirements;

    private boolean pg18;

    @ElementCollection
    @CollectionTable(name = "game_images", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "image_name")
    private List<String> images;

    private BigDecimal price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_purchased_games",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> user;



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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSystemRequirements() {
        return systemRequirements;
    }

    public void setSystemRequirements(String systemRequirements) {
        this.systemRequirements = systemRequirements;
    }

    public boolean isPg18() {
        return pg18;
    }

    public void setPg18(boolean pg18) {
        this.pg18 = pg18;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", platform='" + platform + '\'' +
                ", genre='" + genre + '\'' +
                ", systemRequirements='" + systemRequirements + '\'' +
                ", pg18=" + pg18 +
                ", images=" + images +
                ", price=" + price +
                '}';
    }
}
