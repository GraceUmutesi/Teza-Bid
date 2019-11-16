package com.example.teza_bid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Sales extends AppCompatActivity {
    EditText nameOfTheProduct,price;
    Member member;
  Button ch,up,move;
   ImageView img;
   DatabaseReference reff;
   StorageReference mStorageRef;
   private StorageTask uploadTask;
   public Uri imguri;
   ListView listViewArtists;
   List<Member>artistList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        nameOfTheProduct=(EditText)findViewById(R.id.nameOfUserEditText);
        price=(EditText)findViewById(R.id.priceOfUserEditText);
        member=new Member();
        mStorageRef=FirebaseStorage.getInstance().getReference("Images");
        reff=FirebaseDatabase.getInstance().getReference().child("Member");
        ch=(Button)findViewById(R.id.choose);
        up=(Button)findViewById(R.id.createUserButton);
        move=(Button)findViewById(R.id.shift);

        img=(ImageView) findViewById(R.id.profile);
        listViewArtists=(ListView)findViewById(R.id.listViewArtists) ;
        artistList=new ArrayList<>();
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filechooser();

            }


        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                member.setName(nameOfTheProduct.getText().toString().trim());
                member.setPrice(price.getText().toString().trim());
                reff.push().setValue(member);
                Toast.makeText(Sales.this,"info successsfully enterred",Toast.LENGTH_LONG).show();

                if (uploadTask !=null && uploadTask.isInProgress()){
                    Toast.makeText(Sales.this,"Upload in progress",Toast.LENGTH_LONG).show();
                }else {
                    Fileuploader();
                }


            }
        });
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==move){
                    Intent intent = new Intent(Sales.this, SavedSalesProducts.class);
                    startActivity(intent);
                }
            }
        });


    }



    private  String getExtension(Uri uri)
    {
        ContentResolver cr =getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    private void Fileuploader(){
        StorageReference Ref=mStorageRef.child(System.currentTimeMillis()+"."+getExtension(imguri));
        uploadTask=Ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                      /*  Uri downloadUrl = taskSnapshot.getDownloadUrl();*/
                        Toast.makeText(Sales.this,"Image Uploaded Successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });

    }
    private void Filechooser() {
        Intent intent=new Intent();
        intent.setType("image/'");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imguri=data.getData();
            img.setImageURI(imguri);
        }
    }
}

