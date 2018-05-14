package com.example.quang.bookstoreonline.Models;

/**
 * Created by Quang on 4/7/2018.
 */

public class NewBookModel {
    public Integer idBookC;
    public String nameBookC;
    public String priceC;
    public String imgBookC;
    public String decsA;



    public NewBookModel(Integer idBookC, String nameBookC, String priceC, String imgBookC, String decsA) {
        this.idBookC = idBookC;
        this.nameBookC = nameBookC;
        this.priceC = priceC;
        this.imgBookC = imgBookC;
        this.decsA = decsA;


    }

    public Integer getIdBookC() {
        return idBookC;
    }

    public void setIdBookC(Integer idBookC) {
        this.idBookC = idBookC;
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

    public String getImgBookC() {
        return imgBookC;
    }

    public void setImgBookC(String imgBookC) {
        this.imgBookC = imgBookC;
    }

    public String getDecsA() {
        return decsA;
    }

    public void setDecsA(String decsA) {
        this.decsA = decsA;
    }
}
