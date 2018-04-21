package com.example.quang.bookstoreonline.Models;

/**
 * Created by Quang on 4/7/2018.
 */

public class NewBookModel {
    public String nameBookC;
    public String priceC;
    public String authorC;
    public Integer imgBookC;


    public NewBookModel(String nameBookC, String priceC, String authorC, Integer imgBookC) {
        this.nameBookC = nameBookC;
        this.priceC = priceC;
        this.authorC = authorC;
        this.imgBookC = imgBookC;

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

}
