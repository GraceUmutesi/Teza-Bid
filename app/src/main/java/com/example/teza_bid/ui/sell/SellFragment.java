package com.example.teza_bid.ui.sell;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.teza_bid.CreateAccountforSeller;
import com.example.teza_bid.LoginForSeller;
import com.example.teza_bid.R;
import com.example.teza_bid.Sales;

public class SellFragment extends Fragment {

    private SellViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(SellViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sell, container, false);
        Button createAccount = (Button) root.findViewById(R.id.createUserButton);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Sales.class);
                intent.putExtra("some","this is it");
                startActivity(intent);
            }
        });
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }



}