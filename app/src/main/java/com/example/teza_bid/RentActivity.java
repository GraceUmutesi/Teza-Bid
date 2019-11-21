package com.example.teza_bid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RentActivity extends AppCompatActivity implements View.OnClickListener {
    CardView house;
    CardView car;
    CardView estate;
    CardView room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        house = (CardView) findViewById(R.id.view1);
        car = (CardView) findViewById(R.id.view2);
        estate = (CardView) findViewById(R.id.view3);
        room = (CardView) findViewById(R.id.view4);

        house.setOnClickListener(this);
        car.setOnClickListener(this);
        estate.setOnClickListener(this);
        room.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == house) {
            Intent one = new Intent(RentActivity.this, HouseActivity.class);
            startActivity(one);
        }
        if (v == car) {
            Intent two = new Intent(RentActivity.this, CarActivity.class);
            startActivity(two);
        }
        if (v == estate) {
            Intent three = new Intent(RentActivity.this, EstateActivity.class);
            startActivity(three);
        }
        if (v == room) {
            Intent four = new Intent(RentActivity.this, RoomActivity.class);
            startActivity(four);
        }

    }
}
