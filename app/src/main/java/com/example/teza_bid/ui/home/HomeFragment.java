package com.example.teza_bid.ui.home;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.teza_bid.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    SearchView mySearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
//        mySearch = (SearchView) root.findViewById(R.id.search);

        AnimationDrawable animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.dine), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.bedr), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.car), 3000);
//        animation.addFrame(getResources().getDrawable(R.drawable.iphone), 3000);
//        animation.addFrame(getResources().getDrawable(R.drawable.fri), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.ca), 3000);
//        animation.addFrame(getResources().getDrawable(R.drawable.dr), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.jeans), 3000);
//        animation.addFrame(getResources().getDrawable(R.drawable.me), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.sa), 3000);
//        animation.addFrame(getResources().getDrawable(R.drawable.sh), 3000);
//        animation.addFrame(getResources().getDrawable(R.drawable.sui), 3000);
//        animation.addFrame(getResources().getDrawable(R.drawable.wo), 3000);
        animation.setOneShot(false);
        ImageView imageAnim = (ImageView) root.findViewById(R.id.image);
        imageAnim.setBackgroundDrawable(animation);
        animation.start();


        return root;
    }
}