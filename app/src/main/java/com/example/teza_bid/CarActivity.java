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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CarActivity extends AppCompatActivity {
    ListView listView;
    String onDescription[] = {"Forunner" , "Land Rover Defender", "Rav 4", "Range Rover", "Benz Mercedes", "VolksWagen"};
    String onDescriptione[] = {"100,000Rwf/day" , "120,000Rwf/day",  "90,000Rwf/day",  "150,000Rwf/day",  "100,000Rwf/day",  "80,000Rwf/day"};
    int image[]={R.drawable.forri,
            R.drawable.land1,
            R.drawable.rav,
            R.drawable.range,
            R.drawable.benz,
            R.drawable.volks,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_house);
        listView =(ListView) findViewById(R.id.listview);

        CarActivity.MyAdapter adapter = new CarActivity.MyAdapter(this,onDescription,onDescriptione,image);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {

                    Intent car = new Intent(CarActivity.this, Rent1FormActivity.class);
                    startActivity(car);
                }
                if (position == 1){
                    Intent car1 = new Intent(CarActivity.this, Rent6FormActivity.class);
                    startActivity(car1);
                }
                if (position == 2){
                    Intent car2 = new Intent(CarActivity.this, Rent7FormActivity.class);
                    startActivity(car2);
                }

            }


        });

    }
    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String onDescription[];
        String onDescriptione[];
        int image[];
        MyAdapter (Context c , String descripton[], String descriptione[], int images[]) {
            super(c,R.layout.activity_house,R.id.namee,descripton);
            this.context = c;
            this.onDescription= descripton;
            this.onDescriptione= descriptione;
            this.image = images;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater =(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.rent_list_item,parent,false);
            ImageView images = row.findViewById(R.id.view);
            TextView myDescription = row.findViewById(R.id.name);
            TextView myDescriptione = row.findViewById(R.id.namee);
            images.setImageResource(image[position]);
            myDescription.setText(onDescription[position]);
            myDescriptione.setText(onDescriptione[position]);
            return row;
        }
    }
}
