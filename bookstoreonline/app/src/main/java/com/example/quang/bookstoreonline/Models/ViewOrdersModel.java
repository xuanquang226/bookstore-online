package com.example.quang.bookstoreonline.Models;

/**
 * Created by Quang on 4/7/2018.
 */

public class ViewOrdersModel {
    public String nameBook;
    public String price;
    public String imgBook;

    public ViewOrdersModel(String nameBook, String price, String imgBook) {
        this.nameBook = nameBook;
        this.price = price;
        this.imgBook = imgBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgBook() {
        return imgBook;
    }

    public void setImgBook(String imgBook) {
        this.imgBook = imgBook;
    }
}
