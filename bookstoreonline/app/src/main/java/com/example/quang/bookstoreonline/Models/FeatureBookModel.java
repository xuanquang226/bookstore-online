package com.example.quang.bookstoreonline.Models;

/**
 * Created by Quang on 4/11/2018.
 */

public class FeatureBookModel {
    public String nameBookD;
    public String priceD;
    public String authorD;
    public Integer imgBookD;


    public FeatureBookModel(String nameBookD, String priceD, String authorD, Integer imgBookD) {
        this.nameBookD = nameBookD;
        this.priceD = priceD;
        this.authorD = authorD;
        this.imgBookD = imgBookD;

    }

    public String getNameBookD() {
        return nameBookD;
    }

    public void setNameBookD(String nameBookD) {
        this.nameBookD = nameBookD;
    }

    public String getPriceD() {
        return priceD;
    }

    public void setPriceD(String priceD) {
        this.priceD = priceD;
    }

    public String getAuthorD() {
        return authorD;
    }

    public void setAuthorD(String authorD) {
        this.authorD = authorD;
    }

    public Integer getImgBookD() {
        return imgBookD;
    }

    public void setImgBookD(Integer imgBookD) {
        this.imgBookD = imgBookD;
    }


}
