package com.example.teza_bid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ArtistList extends RecyclerView.Adapter<ArtistList.ImageViewHolder> {
    private Context mContext;
    private List<Member> mUploads;
    private List<NewPrice> mUpload;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference mDatabaseRef2;
    NewPrice price;
    public ArtistList(Context context, List<Member> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_layout, parent, false);
        return new ImageViewHolder(v);


    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Member uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        holder.textViewPrice.setText(uploadCurrent.getPrice());
        mDatabaseRef=FirebaseDatabase.getInstance().getReference(uploadCurrent.getName());

//        Button mShowBid=(Button)findViewById()

        holder.bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "hii", Toast.LENGTH_SHORT).show();

//                AlertDialog.Builder mBuilder=new AlertDialog.Builder(mContext);
//                AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                AlertDialog.Builder alertbox=new AlertDialog.Builder(v.getRootView().getContext());

                View mview =LayoutInflater.from(mContext).inflate(R.layout.activity_bid,null,false);
                alertbox.setView(mview);
                final TextView mText=(TextView) mview.findViewById(R.id.MytextView);
                final EditText mEditText=(EditText)mview.findViewById(R.id.bidOfUserEditText);
                Button mButton=(Button)mview.findViewById(R.id.button3);
                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(mContext, "bla bla bla", Toast.LENGTH_SHORT).show();
                String b=mEditText.getText().toString().trim();
                price.setmBid(b);

                        SharedPreferences mySharedPreferences = mContext.getSharedPreferences("com.example.teza_bid", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = mySharedPreferences.edit();
                        editor.putString("bid",b);
                        editor.apply();
//                Intent intent=new Intent(mContext,Sales.class);
//                intent.putExtra("bid",b);
                       ;

                        try
                        {
                            int bid = Integer.parseInt(b.trim());
                            String price=uploadCurrent.getPrice().trim();
                            int n = Integer.parseInt(price.trim());
                            // do something that throws an exception here

                            if( bid >=  n) {
                                String uploadId = mDatabaseRef2.push().getKey();


                                mDatabaseRef2.child(uploadCurrent.getName()).child(uploadId).setValue(b);
                                Toast.makeText(mContext, "success", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(mContext, "Please Input a Greater Amount", Toast.LENGTH_SHORT).show();
                            }

                        }
                        catch(Exception e)
                        {
                            Toast.makeText(mContext, "Invalid input.Do not exceed 2,147,483,647.", Toast.LENGTH_SHORT).show();
                            // Breakpoint in this line is hit when an exception occurs.
                        }

                
                    }


                });
                AlertDialog alertDialog = alertbox.create();
                alertDialog.show();
               /* Intent intent= new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);*/
            }
        });
        Picasso.with(mContext)
                .load(uploadCurrent.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewPrice;
        public ImageView imageView;
        public Button bid;
        public ImageViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewPrice=itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.image_view_upload);
            bid=itemView.findViewById(R.id.bid);
            price=new NewPrice();


            mDatabaseRef2=FirebaseDatabase.getInstance().getReference().child("bids");
            bid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "hii", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}