package com.example.teza_bid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    EditText simpleSearchView;
  RecyclerView recyclerView;
  DatabaseReference databaseReference;
  FirebaseUser firebaseUser;
  ArrayList<String> imageUrlList;
  ArrayList<String>nameList;
  ArrayList<String>priceList;
  SearchAdapter searchAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
       simpleSearchView = (EditText) findViewById(R.id.search);
       recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
       databaseReference= FirebaseDatabase.getInstance().getReference();
       firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
       recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
       imageUrlList=new ArrayList<>();
       nameList=new ArrayList<>();
       priceList=new ArrayList<>();
       simpleSearchView.addTextChangedListener(new TextWatcher(){

           private Editable s;

           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {

              if (!s.toString().isEmpty()){
                  setAdapter(s.toString());
              }else {
                  nameList.clear();
                  priceList.clear();
                  imageUrlList.clear();
                  recyclerView.removeAllViews();
              }
           }

       });
        searchAdapter=new SearchAdapter(Search.this,nameList,priceList,imageUrlList);
        recyclerView.setAdapter(searchAdapter);
    }
    private void setAdapter(String searchedstring) {

        databaseReference.child("AutomobilesAndMotorycles").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nameList.clear();
                priceList.clear();
                imageUrlList.clear();
                recyclerView.removeAllViews();

                int counter=0;
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String uid=snapshot.getKey();
                    String image=snapshot.child("imageUrl").getValue(String.class);
                    String name=snapshot.child("name").getValue(String.class);
                    String price=snapshot.child("price").getValue(String.class);
                    if (name.toLowerCase().contains(searchedstring)){
                        nameList.add(name);
                        priceList.add(price);
                        imageUrlList.add(image);
                        counter++;
                    }
                    if (counter==15){
                        break;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
