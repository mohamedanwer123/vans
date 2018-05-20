package com.example.cm.clothes.MODELS;

public class ProductData {

    int img,price;

    public  ProductData()
    {}

    public ProductData(int img, int price) {
        this.img = img;
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
