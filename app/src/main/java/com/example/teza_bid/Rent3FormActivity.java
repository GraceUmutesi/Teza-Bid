package com.example.teza_bid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Rent3FormActivity extends AppCompatActivity {

    TextView texte1;
    Button mButtonn1;
    ImageView imagee1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent3_form);
        texte1 = (TextView) findViewById(R.id.descii1);
        imagee1 = (ImageView) findViewById(R.id.imgi1);
        mButtonn1 = (Button) findViewById(R.id.butto1);


        mButtonn1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rent3 = new Intent(Rent3FormActivity.this,LoginActivityForBuyer.class);
                startActivity(rent3);
            }

        });
    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String Information[];
        int image[];

        MyAdapter(Context c, String information[], int images[]) {
            super(c, R.layout.activity_rent, R.id.descii1, information);
            this.context = c;
            this.Information = information;
            this.image = images;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.activity_rent3_form, parent, false);
            ImageView images = row.findViewById(R.id.imgi1);
            TextView myDescription = row.findViewById(R.id.descii1);
            images.setImageResource(image[position]);
            myDescription.setText(Information[position]);


            return row;
        }
    }


}
