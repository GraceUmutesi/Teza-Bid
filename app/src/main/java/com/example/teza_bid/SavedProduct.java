package com.example.teza_bid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedProduct extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    @BindView (R.id.nameOfUserTextView) TextView mName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_product);
        ButterKnife.bind(this);
        Intent intent2 = getIntent();
        String Name = intent2.getStringExtra("NAME");
        System.out.println(Name);
        mName.setText(  "NAME: " + Name);
        Toast.makeText(SavedProduct.this, "Congratulations,your Event Has Been Saved!", Toast.LENGTH_LONG).show();


    }
}
