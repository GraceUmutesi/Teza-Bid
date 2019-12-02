package com.example.teza_bid.ui.rent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.teza_bid.CarActivity;
import com.example.teza_bid.EstateActivity;
import com.example.teza_bid.HouseActivity;
import com.example.teza_bid.R;
import com.example.teza_bid.RentActivity;
import com.example.teza_bid.RoomActivity;

public class RentFragment extends Fragment implements View.OnClickListener {

    private RentViewModel rentViewModel;
    CardView house;
    CardView car;
    CardView estate;
    CardView room;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rentViewModel =
                ViewModelProviders.of(this).get(RentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_rent, container, false);
        final TextView textView = root.findViewById(R.id.text_send);

        house = (CardView)root.findViewById(R.id.view1);
        car = (CardView) root.findViewById(R.id.view2);
        estate = (CardView) root.findViewById(R.id.view3);
        room = (CardView) root.findViewById(R.id.view4);

        house.setOnClickListener(this);
        car.setOnClickListener(this);
        estate.setOnClickListener(this);
        room.setOnClickListener( this);


        rentViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }

        });


        return root;
    }
    @Override
    public void onClick(View v) {
        if (v == house) {
            Intent one = new Intent(getActivity(), HouseActivity.class);
            startActivity(one);
        }
        if (v == car) {
            Intent two = new Intent(getActivity(), CarActivity.class);
            startActivity(two);
        }
        if (v == estate) {
            Intent three = new Intent(getActivity(), EstateActivity.class);
            startActivity(three);
        }
        if (v == room) {
            Intent four = new Intent(getActivity(), RoomActivity.class);
            startActivity(four);
        }

    }


}