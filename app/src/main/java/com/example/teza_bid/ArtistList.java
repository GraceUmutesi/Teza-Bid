package com.example.teza_bid;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ArtistList extends ArrayAdapter<Member> {
    private Activity context;
    private List<Member> artistList;

    public ArtistList( Activity context, List<Member> artistList) {
        super(context, R.layout.list_layout,artistList);
        this.context=context;
        this.artistList=artistList;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.list_layout,null,true);
        TextView textViewName=(TextView)listViewItem.findViewById(R.id.textViewName);
        TextView textViewPrice=(TextView)listViewItem.findViewById(R.id.textViewPrice);
        Member artist=artistList.get(position);
        textViewName.setText(artist.getName());
        textViewPrice.setText(artist.getPrice());
        return listViewItem;

    }
}
