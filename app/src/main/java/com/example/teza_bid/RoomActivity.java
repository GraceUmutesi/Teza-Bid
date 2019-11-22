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

public class RoomActivity extends AppCompatActivity {
    ListView listView;
    String onDescription[] = {"Location:Gasabo,Gisozi" , "Location:Rusizi,Cyangugu", "Location:Karongi,Kibuye", "Location:Rubavu,Gisenyi", "Location:Kicukiro,Sonatubes", "Location:Gasabo,Kacyiru"};
    String onDescriptione[] = {"Price:10,000Rwf/night" , "Price:7,000Rwf/night",  "Price:12,000Rwf/night",  "Price:12,000Rwf/night",  "Price:20,000Rwf/night",  "Price:15,000Rwf/month"};
    int image[]={R.drawable.room2,
                R.drawable.room3,
            R.drawable.room6,
            R.drawable.room4,
            R.drawable.room5,
            R.drawable.room8,

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_house);
        listView =(ListView) findViewById(R.id.listview);

        RoomActivity.MyAdapter adapter = new RoomActivity.MyAdapter(this,onDescription,onDescriptione,image);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent estate = new Intent(RoomActivity.this,RentFormActivity.class);
                startActivity(estate);

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
