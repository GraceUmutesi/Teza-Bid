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

public class Rent9FormActivity extends AppCompatActivity {

    TextView texte;
    Button mButtonn;
    ImageView imagee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent9_form);
        texte = (TextView) findViewById(R.id.descii);
        imagee = (ImageView) findViewById(R.id.img7);
        mButtonn = (Button) findViewById(R.id.butto);


        mButtonn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rent2 = new Intent(Rent9FormActivity.this,PaymentActivity.class);
                startActivity(rent2);
            }

        });
    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String Information[];
        int image[];

        MyAdapter(Context c, String information[], int images[]) {
            super(c, R.layout.activity_rent, R.id.descii, information);
            this.context = c;
            this.Information = information;
            this.image = images;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.activity_rent9_form, parent, false);
            ImageView images = row.findViewById(R.id.img7);
            TextView myDescription = row.findViewById(R.id.descii);
            images.setImageResource(image[position]);
            myDescription.setText(Information[position]);


            return row;
        }
    }


}
