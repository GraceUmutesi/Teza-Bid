//package com.example.teza_bid;
//
//import android.content.Context;
//import android.transition.Slide;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//
//import java.util.List;
//
//public class PagerAdapter extends android.support.v4.view.ViewPagerAdapter{
//    private List<Slide> slides;
//    private Context context;
//    private LayoutInflater layoutInflater;
//    private int custom_position = 0;
//
//
//    public PagerAdapter(List<Slide> slides, Context context){
//        this.slides = slides;
//        this.context = context;
//        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//    }
//    @Override
//    public int getCount(){
//        return integer.MAX_VALUE;
//    }
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object o){
//        return  view == o;
//    }
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position){
//
//        if(custom_position>8)
//            custom_position = 0;
//        Slide slide = slides.get(custom_position);
//        custom_position++;
//        View itemView = layoutInflater.inflate(R.layout.slider_item_layout,container,false);
//        ImageView mImage = itemView.findViewById(R.id.sliderImage);
//        TextView mTitle = itemView.findViewById(R.id.slider_title);
//    }
//}
