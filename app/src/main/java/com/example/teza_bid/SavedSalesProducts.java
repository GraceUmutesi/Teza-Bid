package com.example.teza_bid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
TextView a,b;
Button btn;
DatabaseReference reff;
    EditText nameOfTheProduct,price;
    Member member;
    Button ch,up;
    ListView listViewArtists;
    List<Member> artistList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_sales_products);
        nameOfTheProduct=(EditText)findViewById(R.id.nameOfUserEditText);
        price=(EditText)findViewById(R.id.priceOfUserEditText);
        member=new Member();
        reff=FirebaseDatabase.getInstance().getReference().child("Member");
        listViewArtists=(ListView)findViewById(R.id.listViewArtists) ;
        artistList=new ArrayList<>();





            /*    Toast.makeText(SavedSalesProducts.this,"info successsfully enterred",Toast.LENGTH_LONG).show();
*/


            }


    @Override
    protected void onStart() {
        super.onStart();
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                artistList.clear();
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Member artist =artistSnapshot.getValue(Member.class);
                    artistList.add(artist);
                }
                ArtistList adapter=new ArtistList(SavedSalesProducts.this,artistList);
                listViewArtists.setAdapter(adapter);
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
    }
}
