package com.example.quang.bookstoreonline.Models;

/**
 * Created by Quang on 4/7/2018.
 */

public class NewBookModel {
    public String nameBookC;
    public String priceC;
    public String authorC;
    public Integer imgBookC;
    public Integer imgCart;

    public NewBookModel(String nameBookC, String priceC, String authorC, Integer imgBookC, Integer imgCart) {
        this.nameBookC = nameBookC;
        this.priceC = priceC;
        this.authorC = authorC;
        this.imgBookC = imgBookC;
        this.imgCart = imgCart;
    }

    public String getNameBookC() {
        return nameBookC;
    }

    public void setNameBookC(String nameBookC) {
        this.nameBookC = nameBookC;
    }

    public String getPriceC() {
        return priceC;
    }

    public void setPriceC(String priceC) {
        this.priceC = priceC;
    }

    public String getAuthorC() {
        return authorC;
    }

    public void setAuthorC(String authorC) {
        this.authorC = authorC;
    }

    public Integer getImgBookC() {
        return imgBookC;
    }

    public void setImgBookC(Integer imgBookC) {
        this.imgBookC = imgBookC;
    }

    public Integer getImgCart() {
        return imgCart;
    }

    public void setImgCart(Integer imgCart) {
        this.imgCart = imgCart;
    }
}
