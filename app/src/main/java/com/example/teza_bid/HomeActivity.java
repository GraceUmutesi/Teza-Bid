package com.example.teza_bid;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    SearchView mySearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mySearch = (SearchView)findViewById(R.id.search);

        AnimationDrawable animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.dine), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.bedr), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.car), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.iphone), 3000);
//        animation.addFrame(getResources().getDrawable(R.drawable.fri), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.ca), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.dr), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.jeans), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.me), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.sa), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.sh), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.sui), 3000);
//        animation.addFrame(getResources().getDrawable(R.drawable.wo), 3000);
        animation.setOneShot(false);
        ImageView imageAnim = (ImageView) findViewById(R.id.image);
        imageAnim.setBackgroundDrawable(animation);
        animation.start();
    }
}
