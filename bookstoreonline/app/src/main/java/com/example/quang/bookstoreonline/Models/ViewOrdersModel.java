package com.example.quang.bookstoreonline.Models;

/**
 * Created by Quang on 4/7/2018.
 */

public class ViewOrdersModel {
    public String nameBook;
    public String price;
    public String author;
    public Integer imgBook;

    public ViewOrdersModel(String nameBook, String price, String author, Integer imgBook) {
        this.nameBook = nameBook;
        this.price = price;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getImgBook() {
        return imgBook;
    }

    public void setImgBook(Integer imgBook) {
        this.imgBook = imgBook;
    }
}
