package com.example.teza_bid.ui.bid;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BidViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BidViewModel() {
        mText = new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        return mText;
    }
}