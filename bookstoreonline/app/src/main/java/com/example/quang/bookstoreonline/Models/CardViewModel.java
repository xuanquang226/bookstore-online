package com.example.quang.bookstoreonline.Models;

/**
 * Created by Quang on 4/7/2018.
 */

public class CardViewModel {
    String imgSource;
    String name;

    public CardViewModel(String imgSource, String name) {
        this.imgSource = imgSource;
        this.name = name;
    }

    public String getImgSource() {
        return imgSource;
    }

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
