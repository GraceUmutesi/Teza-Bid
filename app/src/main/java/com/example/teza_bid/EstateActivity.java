package com.example.teza_bid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EstateActivity extends AppCompatActivity {

    ListView listView;
    ImageView imageView;
    String onDescription[] = {"Price:100,00Rwf/day", "Price:200,00Rwf/day", "Price:300,00Rwf/day"};
    int image[]={R.drawable.chair7,
            R.drawable.chair6,
            R.drawable.chair5,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estate);
        listView =(ListView) findViewById(R.id.listview);
        imageView = (ImageView) findViewById(R.id.viewe);

        EstateActivity.MyAdapter adapter = new EstateActivity.MyAdapter(this,onDescription,image);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                    Intent estate = new Intent(EstateActivity.this, Rent2FormActivity.class);
                    startActivity(estate);
                }
                if (position == 1){
                    Intent estate1 = new Intent(EstateActivity.this, Rent8FormActivity.class);
                    startActivity(estate1);
                }
                if (position == 2){
                    Intent estate2 = new Intent(EstateActivity.this, Rent9FormActivity.class);
                    startActivity(estate2);
                }
            }
        });

    }


    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String onDescription[];
        int image[];
        MyAdapter (Context c , String descripton[], int images[]) {
            super(c,R.layout.activity_house,R.id.namei,descripton);
            this.context = c;
            this.onDescription= descripton;
            this.image = images;

        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater =(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.rent1_list_item,parent,false);
            ImageView images = row.findViewById(R.id.viewe);
            TextView myDescription = row.findViewById(R.id.namei);
            images.setImageResource(image[position]);
            myDescription.setText(onDescription[position]);
            return row;
        }
    }


}
