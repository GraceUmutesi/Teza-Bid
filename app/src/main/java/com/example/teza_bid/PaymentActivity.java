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

    @BindView(R.id.mtn) ImageButton mtn;
    @BindView(R.id.airteltigo) ImageButton airteltigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        ButterKnife.bind(this);
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
//            Uri number = Uri.parse("tel:*182*3*123"+"tel:#");
            Intent intentCall = new Intent(Intent.ACTION_DIAL);
            intentCall.setData(Uri.parse("tel:*182*3*12345#"));

            if (ActivityCompat.checkSelfPermission(PaymentActivity.this,
                    Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(intentCall);
            Toast.makeText(PaymentActivity.this, "Please, add # and proceed with payments!", Toast.LENGTH_LONG).show();
        }

        else if (view == airteltigo){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            String number="tel:*182*4*1*44444/#";
            intent.setData(Uri.parse(number));

            if (ActivityCompat.checkSelfPermission(PaymentActivity.this,
                    Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                return;
            }
            startActivity(intent);
            Toast.makeText(PaymentActivity.this, "Please, add # and proceed with payments!", Toast.LENGTH_LONG).show();

        }

    }
}
