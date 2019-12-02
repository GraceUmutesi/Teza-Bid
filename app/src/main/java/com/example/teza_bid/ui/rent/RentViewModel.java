package com.example.teza_bid.ui.rent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RentViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}