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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class RentFormActivity extends AppCompatActivity {

    TextView textView;
    Button mButton;
    ImageView imageView;
    String Information[] = {"Price:120,00Rwf/day" , "Price:100,00Rwf/day", "Price:200,00Rwf/day", "Price:170,00Rwf/day", "Price:300,00Rwf/day", "Price:350,00Rwf/day"};
    int image[]={R.drawable.chair7,
            R.drawable.chair4,
            R.drawable.chair6,
            R.drawable.chair3,
            R.drawable.chair5,
            R.drawable.chair1,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_form);
        textView =(TextView) findViewById(R.id.desc);
        imageView = (ImageView) findViewById(R.id.img);
        mButton = (Button) findViewById(R.id.but);



           mButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent rent = new Intent(Intent.ACTION_SEND);
                   rent.setData(Uri.parse("mailto:"));
                   String to = "rwakaemma34@gmail.com";
                   rent.putExtra(Intent.EXTRA_EMAIL, to);
                   rent.putExtra(Intent.EXTRA_SUBJECT, "Rent Confirmation");
                   rent.putExtra(Intent.EXTRA_TEXT, "Hi,I wanted more information about the item");
                   rent.setType("message/rfc822");
                   startActivity(rent);

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
            View row = layoutInflater.inflate(R.layout.activity_rent_form,parent,false);
            ImageView images = row.findViewById(R.id.img);
            TextView myDescription = row.findViewById(R.id.desc);
            images.setImageResource(image[position]);
            myDescription.setText(Information[position]);


            return row;
        }
    }


}
