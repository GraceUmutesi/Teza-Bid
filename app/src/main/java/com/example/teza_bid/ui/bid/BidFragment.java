package com.example.teza_bid.ui.bid;

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

import com.example.teza_bid.R;
import com.example.teza_bid.SampleActivity;
import com.example.teza_bid.SavedSalesProducts;

public class BidFragment extends Fragment {

    private BidViewModel bidViewModel;
    private CardView office;
    private CardView furniture;
    private CardView auto;
    private CardView electronics;
    private CardView arts;
    private CardView entertainment;
    private CardView other;
    private String NAME;
    String furn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bidViewModel = ViewModelProviders.of(this).get(BidViewModel.class);
        View root = inflater.inflate(R.layout.fragment_bid, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);

        office = root.findViewById(R.id.office);
        furniture = root.findViewById(R.id.furniture);
        auto = root.findViewById(R.id.auto);
        electronics = root.findViewById(R.id.electronics);
        arts = root.findViewById(R.id.arts);
        entertainment = root.findViewById(R.id.entertainment);
        other = root.findViewById(R.id.other);
        Intent intent=getActivity().getIntent();
        NAME=intent.getStringExtra("NAME");
        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(getActivity(), SavedSalesProducts.class);
                furn="OfficeSupplies";
                intent2.putExtra("category",furn);
                System.out.println(intent2);
                startActivity(intent2);


            /*    String Name = intent.getStringExtra("NAME");
                System.out.println(Name);*/
                /*  mlocationNameText.setText(  "NAME: " + Name);*/
            }
        });
        furniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(getActivity(), SavedSalesProducts.class);
                furn="Furniture";
                intent2.putExtra("category",furn);
                System.out.println(intent2);
                startActivity(intent2);


            /*    String Name = intent.getStringExtra("NAME");
                System.out.println(Name);*/
                /*  mlocationNameText.setText(  "NAME: " + Name);*/
            }
        });
        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(getActivity(), SavedSalesProducts.class);
                furn="AutomobilesAndMotorycles";
                intent2.putExtra("category",furn);
                System.out.println(intent2);
                startActivity(intent2);


            /*    String Name = intent.getStringExtra("NAME");
                System.out.println(Name);*/
                /*  mlocationNameText.setText(  "NAME: " + Name);*/
            }
        });
        electronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(getActivity(), SavedSalesProducts.class);
                furn="Electronics";
                intent2.putExtra("category",furn);
                System.out.println(intent2);
                startActivity(intent2);


            /*    String Name = intent.getStringExtra("NAME");
                System.out.println(Name);*/
                /*  mlocationNameText.setText(  "NAME: " + Name);*/
            }
        });
        arts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(getActivity(), SavedSalesProducts.class);
                furn="ArtsAndDeco";
                intent2.putExtra("category",furn);
                System.out.println(intent2);
                startActivity(intent2);


            /*    String Name = intent.getStringExtra("NAME");
                System.out.println(Name);*/
                /*  mlocationNameText.setText(  "NAME: " + Name);*/
            }
        });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(getActivity(), SavedSalesProducts.class);
                furn="Entertainment";
                intent2.putExtra("category",furn);
                System.out.println(intent2);
                startActivity(intent2);


            /*    String Name = intent.getStringExtra("NAME");
                System.out.println(Name);*/
                /*  mlocationNameText.setText(  "NAME: " + Name);*/
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(getActivity(), SavedSalesProducts.class);
                furn="OtherAccessories";
                intent2.putExtra("category",furn);
                System.out.println(intent2);
                startActivity(intent2);


            /*    String Name = intent.getStringExtra("NAME");
                System.out.println(Name);*/
                /*  mlocationNameText.setText(  "NAME: " + Name);*/
            }
        });








        bidViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}