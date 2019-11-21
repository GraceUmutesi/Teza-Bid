//package com.example.teza_bid;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class ConfirmationActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_confirmation);
//
//        Intent intent = getIntent();
//    }
//     try
//
//    {
//        Intent intent;
//        JSONObject jsonDetails = new JSONObject(intent.getStringExtra("PaymentDetails"));
//
//
//        showDetails(jsonDetails.getJSONObject("response"), intent.getStringExtra("PaymentAmount"));
//    } catch(
//    JSONException e)
//
//    {
//        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//    }
//
//    private void showDetails(JSONObject jsonDetails, String paymentAmount) throws JSONException {
//        //Views
//        TextView textViewId = (TextView) findViewById(R.id.paymentId);
//        TextView textViewStatus = (TextView) findViewById(R.id.paymentStatus);
//        TextView textViewAmount = (TextView) findViewById(R.id.paymentAmount);
//
//        //Showing the details from json object
//        textViewId.setText(jsonDetails.getString("id"));
//        textViewStatus.setText(jsonDetails.getString("state"));
//        textViewAmount.setText(paymentAmount + " USD");
//    }
//}
