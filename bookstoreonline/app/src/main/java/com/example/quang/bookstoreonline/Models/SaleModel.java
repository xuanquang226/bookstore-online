package com.example.quang.bookstoreonline.Models;

/**
 * Created by Quang on 4/7/2018.
 */

public class SaleModel {
    public String nameBookA;
    public String priceA;
    public String authorA;
    public Integer imgBookA;
    public String oldPrice;
    public String sale;

    public SaleModel(String nameBookA, String priceA, String authorA, Integer imgBookA, String oldPrice, String sale) {
        this.nameBookA = nameBookA;
        this.priceA = priceA;
        this.authorA = authorA;
        this.imgBookA = imgBookA;
        this.oldPrice = oldPrice;
        this.sale = sale;
    }

    public String getNameBookA() {
        return nameBookA;
    }

    public void setNameBookA(String nameBookA) {
        this.nameBookA = nameBookA;
    }

    public String getPriceA() {
        return priceA;
    }

    public void setPriceA(String priceA) {
        this.priceA = priceA;
    }

    public String getAuthorA() {
        return authorA;
    }

    public void setAuthorA(String authorA) {
        this.authorA = authorA;
    }

    public Integer getImgBookA() {
        return imgBookA;
    }

    public void setImgBookA(Integer imgBookA) {
        this.imgBookA = imgBookA;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }
}
