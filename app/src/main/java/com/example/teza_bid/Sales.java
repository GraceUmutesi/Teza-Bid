package com.example.teza_bid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


import butterknife.BindView;
import butterknife.ButterKnife;

public class Sales extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final int PICK_IMAGE_REQUEST = 1;

    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private Button mTextViewShowUploads;
    private EditText mPrice;
    private ImageView mImageView;
    private ProgressBar mProgressBar;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private static final String TAG ="Sales";
    private StorageTask mUploadTask;
   private Button mView;
    String furn;
   Spinner category;
   private String data;
    private DatabaseReference mDatabaseRef2;

    @BindView(R.id.nameOfUserEditText) EditText mEditTextFileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        ButterKnife.bind(this);
        mButtonChooseImage = findViewById(R.id.choose);
        mButtonUpload = findViewById(R.id.createUserButton);
//        mTextViewShowUploads = findViewById(R.id.text_view_show_uploads);
        mView=findViewById(R.id.View);
        mPrice=findViewById(R.id.priceOfUserEditText);
        mImageView = findViewById(R.id.image_view);
        mProgressBar = findViewById(R.id.progress_bar);
        category = (Spinner) findViewById(R.id.spinner1);


        /*mRecyclerView = findViewById(R.id.recycler_view);
        nameOfTheProduct=(EditText)findViewById(R.id.nameOfUserEditText);
        price=(EditText)findViewById(R.id.priceOfUserEditText);*/
      /*  url=(EditText)findViewById(R.id.)*/

        mStorageRef=FirebaseStorage.getInstance().getReference("Images");

//this is my spinner containing a set of data
        String[] items = new String[]{"Office and Supplies","Furniture","Automobiles and Motorycles","Electronics","Arts and Decoration","Entertainment","Other Accessories"};
        Spinner dropdown = findViewById(R.id.spinner1);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


/*if(data=="Office and School supplies"){
    mDatabaseRef=FirebaseDatabase.getInstance().getReference().child("OfficeAndSchoolSupplies");
}
else */
        /*ch=(Button)findViewById(R.id.choose);
        up=(Button)findViewById(R.id.createUserButton);
        mImageView = findViewById(R.id.post_image);

        img=(ImageView) findViewById(R.id.profile);*/
      /*  artistList=new ArrayList<>();*/
        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Filechooser();

            }


        });
        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(Sales.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    data =category.getSelectedItem().toString();
//                    SharedPreferences mySharedPreferences1 = Sales.this.getSharedPreferences("com.example.teza_bid", Context.MODE_PRIVATE);
////        String username = mySharedPreferences.getString("name", "");
//                    String Name = mySharedPreferences1.getString("name", "");


                    if(data =="Office and Supplies"){
                        mDatabaseRef=FirebaseDatabase.getInstance().getReference().child("OfficeSupplies");
                        furn="OfficeSupplies";
                        SharedPreferences mySharedPreferences = Sales.this.getSharedPreferences("com.example.teza_bid", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = mySharedPreferences.edit();
                        editor.putString("furn",furn);
                        editor.apply();
                    }
                    if(data =="Furniture"){
                        mDatabaseRef=FirebaseDatabase.getInstance().getReference().child("Furniture");
                        furn="Furniture";
                        SharedPreferences mySharedPreferences = Sales.this.getSharedPreferences("com.example.teza_bid", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = mySharedPreferences.edit();
                        editor.putString("furn",furn);
                        editor.apply();
                    }
                    if(data =="Automobiles and Motorycles"){
                        mDatabaseRef=FirebaseDatabase.getInstance().getReference().child("AutomobilesAndMotorycles");
                    }
                    if(data =="Electronics"){
                        mDatabaseRef=FirebaseDatabase.getInstance().getReference().child("Electronics");
                    }
                    if(data =="Arts and Decoration"){
                        mDatabaseRef=FirebaseDatabase.getInstance().getReference().child("ArtsAndDeco");
                    }
                    if(data =="Entertainment"){
                        mDatabaseRef=FirebaseDatabase.getInstance().getReference().child("Entertainment");
                    }
                    if(data =="Other Accessories"){
                        mDatabaseRef=FirebaseDatabase.getInstance().getReference().child("OtherAccessories");
                    }


                    Fileuploader();

                }

            }
        });
      /*  move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
//        mTextViewShowUploads.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openImagesActivity();
//            }
//        });
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Sales.this, Bid.class);

                startActivity(intent);

            }
        });

    }


    private void Filechooser() {
        Intent intent=new Intent();
        intent.setType("image/'");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            mImageUri=data.getData();
           Picasso.with(this).load(mImageUri).into(mImageView);
            /*  Picasso.with(this).load(imguri).into(mImageView);*/
             /* Picasso.with(this).load(imguri).into(mImageView);*/

        }
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text= parent.getItemAtPosition(position).toString();
        /*mPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        mEditor=mPreferences.edit();
        mEditor.putString("key",text);
        mEditor.commit();
        String name =mPreferences.getString("key","default");
        Log.d(TAG,"onCreate:name: " + name);*/
      /*  switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                mDatabaseRef=FirebaseDatabase.getInstance().getReference().child("OfficeAndSchoolSupplies");
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                mDatabaseRef=FirebaseDatabase.getInstance().getReference().child("Furniture");
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                mDatabaseRef=FirebaseDatabase.getInstance().getReference().child("AutomobilesAndMotorycles");
                break;
            case 3:
                // Whatever you want to happen when the first item gets selected
                mDatabaseRef=FirebaseDatabase.getInstance().getReference().child("Electronics");
                break;
            case 4:
                // Whatever you want to happen when the second item gets selected
                mDatabaseRef=FirebaseDatabase.getInstance().getReference().child("Furniture");
                break;
            case 5:
                // Whatever you want to happen when the thrid item gets selected
                mDatabaseRef=FirebaseDatabase.getInstance().getReference().child("Entertainment");
                break;
            case 6:
                // Whatever you want to happen when the first item gets selected
                mDatabaseRef=FirebaseDatabase.getInstance().getReference().child("OtherAccessories");
                break;


        }*/
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void Fileuploader(){

        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);

                            Toast.makeText(Sales.this, "Upload successful", Toast.LENGTH_LONG).show();


                            if (mImageUri != null)
                            {
                                mStorageRef.putFile(mImageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>()
                                {
                                    @Override
                                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                                    {
                                        if (!task.isSuccessful())
                                        {
                                            throw task.getException();
                                        }
                                        return taskSnapshot.getStorage().getDownloadUrl();
                                    }
                                }).addOnCompleteListener(new OnCompleteListener<Uri>()
                                {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task)
                                    {

                                        if (task.isSuccessful())
                                        {
                                            Uri downloadUri = task.getResult();
                                            /*Log.e(TAG, "then: " + downloadUri.toString());*/
                                            SharedPreferences mySharedPreferences = getSharedPreferences("com.example.teza_bid", Context.MODE_PRIVATE);
                                            String username = mySharedPreferences.getString("bid", "");

                                            Member upload = new Member(mEditTextFileName.getText().toString().trim(),
                                                    mPrice.getText().toString().trim(),
                                                    downloadUri.toString());

                                            Intent intent1 = new Intent(Sales.this,SavedSalesProducts.class);
                                          /*  intent1.putExtra("data", data);*/

                                            String uploadId = mDatabaseRef.push().getKey();
                                            mDatabaseRef.child(uploadId).setValue(upload);
                                            String nameOfProduct=mEditTextFileName.getText().toString().trim();
                                            String PriceProduct=mPrice.getText().toString().trim();
                                            String uri=downloadUri.toString();
                                            SharedPreferences mySharedPreferences2 = Sales.this.getSharedPreferences("com.example.teza_bid", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = mySharedPreferences2.edit();
                                            editor.putString("nameOfProduct",nameOfProduct);
                                            editor.apply();

                                            SharedPreferences mySharedPreferences3 = Sales.this.getSharedPreferences("com.example.teza_bid", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor1 = mySharedPreferences3.edit();
                                            editor1.putString("PriceProduct",PriceProduct);
                                            editor1.apply();

                                            SharedPreferences mySharedPreferences4 = Sales.this.getSharedPreferences("com.example.teza_bid", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor2 = mySharedPreferences4.edit();
                                            editor2.putString("uri",uri);
                                            editor2.apply();


                                            SharedPreferences mySharedPreferences1 = Sales.this.getSharedPreferences("com.example.teza_bid", Context.MODE_PRIVATE);
//        String username = mySharedPreferences.getString("name", "");
                                            String Name = mySharedPreferences1.getString("name", "");
                                            mDatabaseRef2= FirebaseDatabase.getInstance().getReference().child("seeBids").child(Name);

                                            Member upload1 = new Member(mEditTextFileName.getText().toString().trim(),
                                                    mPrice.getText().toString().trim(),
                                                    downloadUri.toString());
                                            String uploadId1 = mDatabaseRef2.push().getKey();
                                            mDatabaseRef2.child(uploadId1).setValue(upload1);

                                            mPreferences=getSharedPreferences("com.example.teza_bid", Context.MODE_PRIVATE);
                                            mEditor=mPreferences.edit();
                                            String NAME=upload.getName();
                                            Intent intent2 = new Intent(Sales.this,SampleActivity.class);
                                            intent2.putExtra("NAME", NAME);
                                            mEditor.putString("key",NAME);
                                            mEditor.commit();
                                            String name =mPreferences.getString("key","default");
                                            Log.d(TAG,":name: " + NAME);
                                            startActivity(intent2);

                                        } else
                                        {
                                            Toast.makeText(Sales.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                           /* Member upload = new Member(mEditTextFileName.getText().toString().trim(),
                                    mPrice.getText().toString().trim(),
                                    taskSnapshot.getUploadSessionUri().toString());
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(upload);*/
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Sales.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }


    }
//    private void openImagesActivity() {
//        Intent intent = new Intent(this, SavedSalesProducts.class);
//        startActivity(intent);
//    }




}

