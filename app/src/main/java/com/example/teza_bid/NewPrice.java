package com.example.teza_bid;

public class NewPrice {
    private String mBid;
    public NewPrice() {
    }
    public NewPrice(String bid) {


        mBid = bid;

    }

    public String getBid() {
        return mBid;
    }

    public void setBid(String mBid) {
        this.mBid = mBid;
    }
}
