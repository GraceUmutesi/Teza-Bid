package com.example.teza_bid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class seeBidAdapter extends RecyclerView.Adapter<seeBidAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Member> mUploads;
    private List<NewPrice> mUpload;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference mDatabaseRef2;

    public seeBidAdapter(Context context, List<NewPrice> uploads) {
        mContext = context;
        mUpload = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.last_layout, parent, false);
        return new ImageViewHolder(v);


    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        NewPrice uploadCurrent = mUpload.get(position);
        holder.textViewBid.setText(uploadCurrent.getBid());



    }

    @Override
    public int getItemCount() {
        return mUpload.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewBid;

        public ImageViewHolder(View itemView) {
            super(itemView);

            textViewBid = itemView.findViewById(R.id.text_view_bid);


        }
    }


}
