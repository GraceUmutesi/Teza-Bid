package com.example.teza_bid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;

public class SampleActivity extends AppCompatActivity {
    private Button office;
    private Button furniture;
    private Button auto;
    private Button electronics;
    private Button arts;
    private Button entertainment;
    private Button other;
    private String NAME;
    String furn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        /* mEditTextFileName = findViewById(R.id.nameOfUserEditText);*/
        office = findViewById(R.id.office);
        furniture = findViewById(R.id.furniture);
        auto = findViewById(R.id.auto);
        electronics = findViewById(R.id.electronics);
        arts = findViewById(R.id.arts);
        entertainment = findViewById(R.id.entertainment);
        other = findViewById(R.id.other);
        Intent intent=getIntent();
        NAME=intent.getStringExtra("NAME");
        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(SampleActivity.this, SavedSalesProducts.class);
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

                Intent intent2 = new Intent(SampleActivity.this, SavedSalesProducts.class);
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

                Intent intent2 = new Intent(SampleActivity.this, SavedSalesProducts.class);
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

                Intent intent2 = new Intent(SampleActivity.this, SavedSalesProducts.class);
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

                Intent intent2 = new Intent(SampleActivity.this, SavedSalesProducts.class);
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

                Intent intent2 = new Intent(SampleActivity.this, SavedSalesProducts.class);
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

                Intent intent2 = new Intent(SampleActivity.this, SavedSalesProducts.class);
                furn="OtherAccessories";
                intent2.putExtra("category",furn);
                System.out.println(intent2);
                startActivity(intent2);


            /*    String Name = intent.getStringExtra("NAME");
                System.out.println(Name);*/
                /*  mlocationNameText.setText(  "NAME: " + Name);*/
            }
        });

    }

}