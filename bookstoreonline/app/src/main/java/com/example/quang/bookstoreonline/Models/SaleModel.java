package com.example.quang.bookstoreonline.Models;

/**
 * Created by Quang on 4/7/2018.
 */

public class SaleModel {
    public int idBookA;
    public String nameBookA;
    public Double priceA;
    public String decsA;
    public String imgBookA;
    public String oldPrice;
    public String sale;



    public SaleModel(int id, String nameBookA, Double priceA, String decsA, String imgBookA, String oldPrice, String sale) {
        this.idBookA = id;
        this.nameBookA = nameBookA;
        this.priceA = priceA;
        this.decsA = decsA;

        this.imgBookA = imgBookA;
        this.oldPrice = oldPrice;
        this.sale = sale;
    }


    public int getIdBookA() {
        return idBookA;
    }

    public void setIdBookA(int idBookA) {
        this.idBookA = idBookA;
    }

    public String getNameBookA() {
        return nameBookA;
    }

    public void setNameBookA(String nameBookA) {
        this.nameBookA = nameBookA;
    }

    public Double getPriceA() {
        return priceA;
    }

    public void setPriceA(Double priceA) {
        this.priceA = priceA;
    }

    public String  getImgBookA() {
        return imgBookA;
    }

    public void setImgBookA(String imgBookA) {
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

    public String getDecsA() {
        return decsA;
    }

    public void setDecsA(String decsA) {
        this.decsA = decsA;
    }
}
