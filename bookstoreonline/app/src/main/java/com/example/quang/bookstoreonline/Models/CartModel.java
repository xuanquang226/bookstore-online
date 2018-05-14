package com.example.quang.bookstoreonline.Models;

/**
 * Created by Quang on 4/7/2018.
 */

public class CartModel {
    public String nameBookB;
    public float priceB;
    public Integer amount;
    public String imgBookB;

    public CartModel() {
    }

    public CartModel(String nameBookB, float priceB,  Integer amount, String imgBookB) {
        this.nameBookB = nameBookB;
        this.priceB = priceB;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getImgBookB() {
        return imgBookB;
    }

    public void setImgBookB(String imgBookB) {
        this.imgBookB = imgBookB;
    }
}
