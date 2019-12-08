package com.example.teza_bid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Profile extends AppCompatActivity {
//    @BindView(R.id.emaiL)
//    TextView mlocationEmailText;
    @BindView(R.id.name)
    TextView mlocationNameText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        SharedPreferences mySharedPreferences1 = getSharedPreferences("com.example.teza_bid", Context.MODE_PRIVATE);
//        String username = mySharedPreferences.getString("name", "");
        String email = mySharedPreferences1.getString("name", "");
        mlocationNameText.setText(  email);
//        mlocationEmailText.setText(  "Email: " + email);
    }
}
