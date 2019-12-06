package com.example.teza_bid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Rent1FormActivity extends AppCompatActivity {

        TextView text;
        Button mButtoon;
        ImageView image;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_rent1_form);
            text = (TextView) findViewById(R.id.desci);
            image = (ImageView) findViewById(R.id.img4);
            mButtoon = (Button) findViewById(R.id.butt);


            mButtoon.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent rent1 = new Intent(Rent1FormActivity.this,LoginActivityForBuyer.class);
                    startActivity(rent1);
                }

            });
        }

        class MyAdapter extends ArrayAdapter<String> {
            Context context;
            String Information[];
            int image[];

            MyAdapter(Context c, String information[], int images[]) {
                super(c, R.layout.activity_rent, R.id.desci, information);
                this.context = c;
                this.Information = information;
                this.image = images;

            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View row = layoutInflater.inflate(R.layout.activity_rent1_form, parent, false);
                ImageView images = row.findViewById(R.id.img4);
                TextView myDescription = row.findViewById(R.id.desci);
                images.setImageResource(image[position]);
                myDescription.setText(Information[position]);


                return row;
            }
        }


    }
