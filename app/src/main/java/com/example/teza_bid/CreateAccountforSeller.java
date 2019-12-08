package com.example.teza_bid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccountforSeller extends AppCompatActivity  implements View.OnClickListener{

    @BindView(R.id.createUserButton)
    Button mCreate;
    @BindView(R.id.nameOfUserEditText)
    EditText mNameOfUserEditText;
    @BindView(R.id.emailOfUserEditText) EditText mEmailOfUserEditText;
    @BindView(R.id.passwordOfUserEditText) EditText mPassworOfUserdEditText;
    @BindView(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;
    @BindView(R.id.loginTextView)
    TextView mLoginTextView;
    public static final String TAG = CreateAccountforSeller.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_accountfor_seller);
        TextView textView = findViewById(R.id.terms);
        String text = "By tapping the button sign up, you agree to our terms and conditions and therefore will obey them";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
               /* Toast.makeText(CreateAccountforSeller.this, "One", Toast.LENGTH_SHORT).show();*/
                if (widget == textView) {
                    Intent webIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.termsconditionstemplate.net/live.php?token=8lrqFmVsLoGKMcX4rLqXLvdTKQF1Fed4"));
                    startActivity(webIntent);
                }
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan1, 48, 68, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        mLoginTextView.setOnClickListener(this);
        mCreate.setOnClickListener(this);
        mCreate = (Button) findViewById(R.id.createUserButton);
        createAuthStateListener();
    }

    @Override
    public void onClick(View view) {

        if (view == mLoginTextView) {
            Intent intent = new Intent(CreateAccountforSeller.this, LoginForSeller.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        if (view == mCreate) {
            createNewUser();
            Intent intent = new Intent(CreateAccountforSeller.this, LoginForSeller.class);
            startActivity(intent);

        }

    }

    private void createNewUser() {
         String name = mNameOfUserEditText.getText().toString().trim();
        SharedPreferences mySharedPreferences = CreateAccountforSeller.this.getSharedPreferences("com.example.teza_bid", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString("name",name);
        editor.apply();
//        Intent intent2 = new Intent();
//        new Intent(getApplicationContext(), LoginForSeller.class);
//        intent2.putExtra("name", name);
//        startActivity(intent2);
         String email = mEmailOfUserEditText.getText().toString().trim();
//        editor.putString("email",email);

        String password = mPassworOfUserdEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Authentication successful");
                        } else {
                            Toast.makeText(CreateAccountforSeller.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            getSupportActionBar().setTitle(  user.getDisplayName());
                        } else {                }
                    }
                };
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}

