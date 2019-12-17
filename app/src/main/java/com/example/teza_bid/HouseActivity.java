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

public class HouseActivity extends AppCompatActivity {
    ListView listView;
    String onDescription[] = {"Yaounde,Musanze" , "Gasabo,Kacyiru", "Gasabo,Kimironko", "Nyamata,Bugesera", "Kicukiro,Niboye", "Gisenyi,Rubavu"};
    String onDescriptione[] = {"270,000Rwf/month" , "400,000Rwf/month",  "300,000Rwf/month",  "330,000Rwf/month",  "220,000Rwf/month",  "500,000Rwf/month"};
    int image[]={R.drawable.house7,
            R.drawable.house2,
            R.drawable.house3,
            R.drawable.house6,
            R.drawable.house1,
            R.drawable.house8,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_house);
        listView =(ListView) findViewById(R.id.listview);

        MyAdapter adapter = new MyAdapter(this,onDescription,onDescriptione,image);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                    Intent house = new Intent(HouseActivity.this, RentFormActivity.class);
                    startActivity(house);
                }
                if (position == 1){
                    Intent house1 = new Intent(HouseActivity.this, Rent4FormActivity.class);
                    startActivity(house1);
                }
                if (position == 2){
                    Intent house2 = new Intent(HouseActivity.this, Rent5FormActivity.class);
                    startActivity(house2);
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