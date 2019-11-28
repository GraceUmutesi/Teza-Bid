package com.example.teza_bid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Wendy extends AppCompatActivity {
    Spinner category;
    private String data;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wendy);

        category = (Spinner) findViewById(R.id.spinner1);
        String[] items = new String[]{"Furniture","Automobiles and Motorycles","Electronics","Arts and Decoration","Entertainment","Other Accessories"};
        Spinner dropdown = findViewById(R.id.spinner1);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data =category.getSelectedItem().toString();
                System.out.println(data);

            }
        });

    }
}
