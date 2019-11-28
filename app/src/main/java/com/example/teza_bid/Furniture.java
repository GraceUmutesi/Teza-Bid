package com.example.teza_bid;

public class Furniture {
    private String mName;
    private String mPrice;
    private String mImageUrl;
    public Furniture() {
        //empty constructor needed
    }

    public Furniture(String name, String price,String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        mName = name;
        mPrice=price;
        mImageUrl = imageUrl;
    }

    public String getName() {
        return mName;
    }
    public void setName(String name) {
        mName = name;
    }
    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        this.mPrice = price;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}
