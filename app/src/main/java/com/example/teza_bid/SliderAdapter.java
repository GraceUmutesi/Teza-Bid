package com.example.teza_bid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public int[] images = {
            R.drawable.iphone,
            R.drawable.sam1,
            R.drawable.fri,
            R.drawable.dine,
            R.drawable.bed,
            R.drawable.car,
            R.drawable.din,
            R.drawable.bedr,
            R.drawable.car1
    };

    public String [] slide_desc ={"Helooooooo"};

    @Override
    public int getCount() {
        return slide_desc.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.images);
        TextView slidedescription = (TextView)view.findViewById(R.id.descr);
        return view;
    }
}
