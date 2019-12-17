package com.example.teza_bid.ui.rate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.teza_bid.R;

public class RateFragment extends Fragment {

    private RateViewModel rateViewModel;
    private RatingBar ratingBar ;
    private Button Submit_button;
    private TextView RateTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rateViewModel =
                ViewModelProviders.of(this).get(RateViewModel.class);
        View root = inflater.inflate(R.layout.fragment_rate, container, false);
        ratingBar = root.findViewById(R.id.bar);
        Submit_button = root.findViewById(R.id.submit);
        RateTextView = root.findViewById(R.id.tetiew);
        Submit_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RateTextView.setText( "your rating is; " + ratingBar.getRating() );
            }
        } );
        final TextView textView = root.findViewById(R.id.text_share);
        rateViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}