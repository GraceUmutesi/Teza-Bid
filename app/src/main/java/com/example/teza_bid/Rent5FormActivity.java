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

public class Rent5FormActivity extends AppCompatActivity {
    TextView textView;
    Button mButton;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent5_form);
        textView =(TextView) findViewById(R.id.desc);
        imageView = (ImageView) findViewById(R.id.img);
        mButton = (Button) findViewById(R.id.but);



        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chooser = null;
                Intent rent = new Intent(Intent.ACTION_SEND);
                rent.setData(Uri.parse("mailto:"));
                rent.putExtra(Intent.EXTRA_EMAIL, new String[] {"ahiode6@gmail.com"});
                rent.putExtra(Intent.EXTRA_SUBJECT, "Rent Information");
                rent.putExtra(Intent.EXTRA_TEXT, "Hi,I wanted more information about the item");
                rent.setType("text/plain");
                startActivity(Intent.createChooser(rent, "Choose Email Client"));

            }

        });
    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String Information[];
        int image[];
        MyAdapter (Context c , String information[], int images[]) {
            super(c,R.layout.activity_rent,R.id.desc,information);
            this.context = c;
            this.Information= information;
            this.image = images;

        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater =(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.activity_rent5_form,parent,false);
            ImageView images = row.findViewById(R.id.img);
            TextView myDescription = row.findViewById(R.id.desc);
            images.setImageResource(image[position]);
            myDescription.setText(Information[position]);


            return row;
        }
    }


}