package com.example.teza_bid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BidAdapter extends RecyclerView.Adapter<BidAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Member> mUploads;
    private List<NewPrice> mUpload;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference mDatabaseRef2;

    public BidAdapter(Context context, List<Member> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.another_layout, parent, false);
        return new ImageViewHolder(v);


    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Member uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        holder.textViewPrice.setText(uploadCurrent.getPrice());
        holder.bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, Result.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
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
            bid=itemView.findViewById(R.id.bid1);

        }
    }


}
