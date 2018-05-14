package com.example.quang.bookstoreonline.Models;

/**
 * Created by Quang on 4/11/2018.
 */

public class FeatureBookModel {
    public Integer idBookD ;
    public String nameBookD;
    public String priceD;
    public String imgBookD;
    public String descD;



    public FeatureBookModel(Integer idBookD, String nameBookD, String priceD, String imgBookD, String descD) {
        this.idBookD   = idBookD;
        this.nameBookD = nameBookD;
        this.priceD = priceD;
        this.imgBookD = imgBookD;
        this.descD = descD;

    }

    public Integer getIdBookD() {
        return idBookD;
    }

    public void setIdBookD(Integer idBookD) {
        this.idBookD = idBookD;
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

    public String getImgBookD() {
        return imgBookD;
    }

    public void setImgBookD(String imgBookD) {
        this.imgBookD = imgBookD;
    }

    public String getDescD() {
        return descD;
    }

    public void setDescD(String descD) {
        this.descD = descD;
    }
}
