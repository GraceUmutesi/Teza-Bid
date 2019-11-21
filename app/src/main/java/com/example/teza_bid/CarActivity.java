package com.example.teza_bid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CarActivity extends AppCompatActivity {
    ListView listView;
    String onDescription[] = {"Type:Forunner" , "Type:Land Rover Defender", "Type:Rav 4", "Type:Range Rover", "Type:Benz Mercedes", "Type:VolksWagen"};
    String onDescriptione[] = {"Price:100,000Rwf/day" , "Price:120,000Rwf/day",  "Price:90,000Rwf/day",  "Price:150,000Rwf/day",  "Price:100,000Rwf/day",  "Price:80,000Rwf/day"};
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
