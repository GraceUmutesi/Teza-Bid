package com.example.teza_bid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivityForBuyer extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.buyerEmail) EditText loginEmail;
    @BindView(R.id.buyerPassword) EditText loginPassword;
    @BindView(R.id.buyeLoginButton) Button loginButton;
    @BindView(R.id.newUserTextView)TextView toSignUp;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_for_buyer);
        ButterKnife.bind(this);
        toSignUp.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        firebaseAuth=FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Intent intent = new Intent(LoginActivityForBuyer.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
        createAuthProgressDialog();

    }

    private void createAuthProgressDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Authenticating with Firebase...");
        progressDialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        if (v == toSignUp){
            Intent intent = new Intent(LoginActivityForBuyer.this, SignupActivityForBuyer.class);
            startActivity(intent);
            finish();
        }

        if (v == loginButton){
            loginWithPassword();
            Intent intent = new Intent(LoginActivityForBuyer.this, PaymentActivity.class);
            startActivity(intent);
        }
    }

    private void loginWithPassword(){
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(LoginActivityForBuyer.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (email.equals("")){
            loginEmail.setError("Please enter your Email");
            return;
        }
        if (password.equals("")){
            loginPassword.setError("Please enter your password");
            return;
        }

        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (!task.isSuccessful()){
                    Toast.makeText(LoginActivityForBuyer.this, "Authentication failed", Toast.LENGTH_LONG);
                }
            }
        });
    }
}
