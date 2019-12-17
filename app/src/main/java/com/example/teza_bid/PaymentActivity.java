package com.example.teza_bid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton mtn;
    ImageButton airteltigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

//        ButterKnife.bind(this);

        mtn = (ImageButton) findViewById(R.id.mtn);
        airteltigo = (ImageButton) findViewById(R.id.airteltigo);
        mtn.setOnClickListener(this);
        airteltigo.setOnClickListener(this);

        Animation bounce = AnimationUtils.loadAnimation(this,R.anim.bounce);
        mtn.startAnimation(bounce);

        Animation lefttoright = AnimationUtils.loadAnimation(this,R.anim.lefttoright);
        airteltigo.startAnimation(lefttoright);
    }


    @Override
    public void onClick(View view) {
        if (view == mtn){

            Intent intentCall = new Intent(Intent.ACTION_DIAL);
            intentCall.setData(Uri.parse(Uri.parse("tel:*182*8*1*303030")+Uri.encode("#")));

            if (ActivityCompat.checkSelfPermission(PaymentActivity.this,
                    Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                return;
            }
//            mtn.setEnabled(true);
            startActivity(intentCall);
//            Toast.makeText(PaymentActivity.this, "Please, add # and proceed with payments!", Toast.LENGTH_LONG).show();
        }

        else if (view == airteltigo){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(Uri.parse("tel:*182*4*1*3030")+Uri.encode("#")));

            if (ActivityCompat.checkSelfPermission(PaymentActivity.this,
                    Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                return;
            }
//            airteltigo.setEnabled(true);
            startActivity(intent);
//            Toast.makeText(PaymentActivity.this, "Please, add # and proceed with payments!", Toast.LENGTH_LONG).show();

        }

    }
}