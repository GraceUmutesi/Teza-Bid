//package com.example.teza_bid;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.icu.math.BigDecimal;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.paypal.android.sdk.payments.PayPalConfiguration;
//import com.paypal.android.sdk.payments.PayPalPayment;
//import com.paypal.android.sdk.payments.PayPalService;
//import com.paypal.android.sdk.payments.PaymentActivity;
//
//public class PayActivity extends AppCompatActivity implements View.OnClickListener{
//
//    private Button buttonPay;
//    private EditText editTextAmount;
//    private String paymentAmount;
//    public static final int PAYPAL_REQUEST_CODE = 123;
//    private static PayPalConfiguration config = new PayPalConfiguration()
//            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
//            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pay);
//
//        buttonPay = (Button) findViewById(R.id.buttonPay);
//        editTextAmount = (EditText) findViewById(R.id.editTextAmount);
//
//        buttonPay.setOnClickListener(this);
//
//        Intent intent = new Intent(this, PayPalService.class);
//
//        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
//
//        startService(intent);
//
//    }
//
//
//    @Override
//    public void onClick(View view) {
//        getPayment();
//    }
//
//    private void getPayment() {
//        paymentAmount = editTextAmount.getText().toString();
//        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(paymentAmount)), "USD", "Simplified Coding Fee",
//                PayPalPayment.PAYMENT_INTENT_SALE);
//        Intent intent = new Intent(this, PaymentActivity.class);
//        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
//        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
//        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
//    }
//    @Override
//    public void onDestroy() {
//        stopService(new Intent(this, PayPalService.class));
//        super.onDestroy();
//    }
//}
