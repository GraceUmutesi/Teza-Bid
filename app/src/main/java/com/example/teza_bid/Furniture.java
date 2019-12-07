package com.example.teza_bid;

public class Furniture {
    private String mName;
    private String mPrice;
    private String mImageUrl;
    private String mBid;
    public Furniture() {
        //empty constructor needed
    }

    public Furniture(String name, String price,String imageUrl,String bid) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        mName = name;
        mPrice=price;
        mImageUrl = imageUrl;
        mBid=bid;
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
    public String getBid() {
        return mBid;
    }

    public void setBid(String bid) {
        mBid = bid;
    }
}
