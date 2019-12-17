package com.example.teza_bid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SellerDashboard extends AppCompatActivity {
    private Button mButtonProfile;
    private Button mButtonUploads;
    private Button mButtonViewUploads;
    private Button mButtonBids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dashboard);
        mButtonProfile = findViewById(R.id.buttonProfile);
        mButtonUploads = findViewById(R.id.buttonUploads);
        mButtonViewUploads = findViewById(R.id.buttonViewUploads);
        mButtonBids=findViewById(R.id.buttonBids);
        mButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent2 = new Intent(SellerDashboard.this, Profile.class);
//                startActivity(intent2);

            }


        });
        mButtonUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(SellerDashboard.this, Sales.class);
                startActivity(intent2);

            }
        });
        mButtonViewUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(SellerDashboard.this, SampleActivity.class);
                startActivity(intent2);

            }
        });
        mButtonBids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent2 = new Intent(SellerDashboard.this, Bid.class);
//                startActivity(intent2);

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_seller, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(SellerDashboard.this, LoginForSeller.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}