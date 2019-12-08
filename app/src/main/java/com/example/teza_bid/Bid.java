package com.example.teza_bid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Bid extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private BidAdapter mAdapter;

    private ProgressBar mProgressCircle;

    private DatabaseReference mDatabaseRef;
    protected List<Member> mUploads;
    private static final String TAG ="SavedSalesProducts";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid2);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressCircle = findViewById(R.id.progress_circle);

        mUploads = new ArrayList<>();

//        Intent intent2 = getIntent();
//        String category = intent2.getStringExtra("category");
//        System.out.println(category);
//        String uploadId = mDatabaseRef.push().getKey();
        SharedPreferences mySharedPreferences1 = Bid.this.getSharedPreferences("com.example.teza_bid", Context.MODE_PRIVATE);
//        String username = mySharedPreferences.getString("name", "");
        String Name = mySharedPreferences1.getString("name", "");
        SharedPreferences mySharedPreferences = Bid.this.getSharedPreferences("com.example.teza_bid", Context.MODE_PRIVATE);
        String category = mySharedPreferences.getString("furn", "");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("seeBids").child(Name);

//        mDatabaseRef.child(email).child(uploadId).setValue(mUploads);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Member upload = postSnapshot.getValue(Member.class);
                    mUploads.add(upload);

                }

                mAdapter = new BidAdapter(Bid.this, mUploads);

                mRecyclerView.setAdapter(mAdapter);
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Bid.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

}