package com.example.oqulyk.Book;

import java.io.Serializable;

public class Kitap implements Serializable {
    private String title;
    private String image;
    private String description;
    private int fee;
    private int numberInCart;

    public Kitap(String title, String image, String description, int fee) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.fee = fee;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
