package com.example.quang.bookstoreonline.Models;

/**
 * Created by Quang on 4/7/2018.
 */

public class CartModel {
    public String nameBookB;
    public float priceB;
    public String authorB;
    public Integer amount;
    public Integer imgBookB;

    public CartModel() {
    }

    public CartModel(String nameBookB, float priceB, String authorB, Integer amount, Integer imgBookB) {
        this.nameBookB = nameBookB;
        this.priceB = priceB;
        this.authorB = authorB;
        this.amount = amount;
        this.imgBookB = imgBookB;
    }

    public String getNameBookB() {
        return nameBookB;
    }

    public void setNameBookB(String nameBookB) {
        this.nameBookB = nameBookB;
    }

    public float getPriceB() {
        return priceB;
    }

    public void setPriceB(float priceB) {
        this.priceB = priceB;
    }

    public String getAuthorB() {
        return authorB;
    }

    public void setAuthorB(String authorB) {
        this.authorB = authorB;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getImgBookB() {
        return imgBookB;
    }

    public void setImgBookB(Integer imgBookB) {
        this.imgBookB = imgBookB;
    }
}
