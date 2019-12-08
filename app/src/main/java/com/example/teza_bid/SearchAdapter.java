package com.example.teza_bid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<String> imageUrlList;
    ArrayList<String>nameList;
    ArrayList<String>priceList;
    class SearchViewHolder extends RecyclerView.ViewHolder{
     ImageView img;
     TextView name_list,price_list;
        public SearchViewHolder( View itemView) {

         super(itemView);
         img=(ImageView)itemView.findViewById(R.id.img_list);
         name_list=(TextView) itemView.findViewById(R.id.name_list);
         price_list=(TextView)itemView.findViewById(R.id.price);
        }
    }
    public SearchAdapter(Context context, ArrayList<String> imageUrlList, ArrayList<String> nameList, ArrayList<String> priceList) {
        this.context = context;
        this.imageUrlList = imageUrlList;
        this.nameList = nameList;
        this.priceList = priceList;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.search_list_items,parent,false);
        return new SearchAdapter.SearchViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.name_list.setText(nameList.get(position));
        holder.price_list.setText(priceList.get(position));
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.ic_launcher_round);
        requestOptions.error(R.mipmap.ic_person_black_24dp);
        Glide.with(context).asBitmap().load(imageUrlList.get(position)).apply(requestOptions).into(holder.img);
    }


    @Override
    public int getItemCount() {
        return nameList.size();
    }
}
