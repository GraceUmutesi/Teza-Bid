package com.example.teza_bid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SavedSalesProducts extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArtistList mAdapter;

    private ProgressBar mProgressCircle;

    private DatabaseReference mDatabaseRef;
    protected List<Member> mUploads;
    private static final String TAG ="SavedSalesProducts";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_sales_products);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressCircle = findViewById(R.id.progress_circle);

        mUploads = new ArrayList<>();

        Intent intent2 = getIntent();
        String category = intent2.getStringExtra("category");
        System.out.println(category);
//        String uploadId = mDatabaseRef.push().getKey();
        SharedPreferences mySharedPreferences1 = SavedSalesProducts.this.getSharedPreferences("com.example.teza_bid", Context.MODE_PRIVATE);
//        String username = mySharedPreferences.getString("name", "");
        String Name = mySharedPreferences1.getString("name", "");
        SharedPreferences mySharedPreferences = getSharedPreferences("com.example.teza_bid", Context.MODE_PRIVATE);
//        String username = mySharedPreferences.getString("name", "");
        String email = mySharedPreferences.getString("furn", "");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(category);

//        mDatabaseRef.child(email).child(uploadId).setValue(mUploads);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Member upload = postSnapshot.getValue(Member.class);
                    mUploads.add(upload);

                }

                mAdapter = new ArtistList(SavedSalesProducts.this, mUploads);

                mRecyclerView.setAdapter(mAdapter);
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(SavedSalesProducts.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

}