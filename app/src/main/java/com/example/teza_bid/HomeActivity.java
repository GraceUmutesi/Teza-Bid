package com.example.teza_bid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.transition.Slide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity {
    ViewPager viewPager;

//    private List<Slide> slideList = new ArrayList<>();
//    private ViewPagerAdapter pager;
//    private PagerAdapter adapter;
//    private Timer timer;
//    private int current_position = 0;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPager = (ViewPager) findViewById(R.id.View);
//        pager = findViewById(R.id.viewpager);
//        prepareSlide();
//        adapter = new PagerAdapter(slideList, this);
//        pager.setAdapter(adapter);
//        createSlideShow();
//    }
//
//    private void createSlideShow() {
//        private void createSlideShow() {
//            final Handler handle = new Handler();
//            final Runnable runnable = new Runnable() {
//                @Override
//                public void run() {
//                    if(current_position == Integer.MAX_VALUE)
//                        current_position = 0;
//                    pager.setCurrentItem(current_position++, true);
//                }
//            };
//            timer = new Timer();
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                   handle.post(runnable);
//                }
//            },250, 2450);
//        }
//    }
//
//    private void prepareSlide() {
//        int [] images = {R.drawable.iphone,R.drawable.dine,R.drawable.bed,R.drawable.fri,R.drawable.sams,R.drawable.bedr,R.drawable.din,R.drawable.ref};
//        List<String> titles = Arrays.asList(getResources().getStringArray(R.array.main_title));
//        List<String> sub_title = Arrays.asList(getResources().getStringArray(R.array.description));
//
//        for(int count = 0;count<images.length;count++){
//            boolean add = slideList.add(new Slide(images[count], titles.get(count), sub_title.get(count)));
//        }
//
//
//
//
//}

}
