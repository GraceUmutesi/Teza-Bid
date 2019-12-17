package com.example.teza_bid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginForSeller extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.nameOfUserEditText)
    EditText loginName;
    @BindView(R.id.emailEditText)
    EditText loginEmail;
    @BindView(R.id.passwordEditText)
    EditText loginPassword;
    @BindView(R.id.passwordLoginButton)
    Button loginButton;
    @BindView(R.id.registerTextView)
    TextView toSignUp;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private ProgressDialog progressDialog;
    DatabaseReference reff;
    TextView username;
    Seller seller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_for_seller);
        seller = new Seller();
        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        ButterKnife.bind(this);
        toSignUp.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(LoginForSeller.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
        createAuthProgressDialog();
    }

    private void createAuthProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Authenticating with Firebase...");
        progressDialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        if (v == toSignUp) {
            Intent intent = new Intent(LoginForSeller.this, CreateAccountforSeller.class);

            startActivity(intent);

            finish();
        }

        if (v == loginButton) {
            loginWithPassword();
            String Email = loginEmail.getText().toString();
            seller.setEmail(loginEmail.getText().toString().trim());
            seller.setName(loginName.getText().toString().trim());
            reff.push().setValue(seller);
//            Intent intent2 = getIntent();
//            Bundle extras = intent2.getExtras();
//            String tmp = extras.getString("name");
//            Intent intent3 = new Intent();
//            new Intent(getApplicationContext(), LoginForSeller.class);
//            intent2.putExtra("tmp", tmp);
//            startActivity(intent2);
            Intent intent = new Intent(LoginForSeller.this, SellerDashboard.class);

//            SharedPreferences mySharedPreferences = getSharedPreferences("com.example.teza_bid", Context.MODE_PRIVATE);
//            String username = mySharedPreferences.getString("name", "");
//            intent.putExtra("username", username);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void loginWithPassword() {
        String name = loginName.getText().toString().trim();
        String email = loginEmail.getText().toString().trim();
        SharedPreferences mySharedPreferences1 = LoginForSeller.this.getSharedPreferences("com.example.teza_bid", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences1.edit();
        editor.putString("name",name);
        editor.apply();
        String password = loginPassword.getText().toString().trim();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(LoginForSeller.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (email.equals("")) {
            loginEmail.setError("Please enter your Email");
            return;
        }
        if (password.equals("")) {
            loginPassword.setError("Password cannot be blank");
            return;
        }

        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (!task.isSuccessful()) {
                    Toast.makeText(LoginForSeller.this, "Authentication failed", Toast.LENGTH_LONG);
                }
            }
        });
    }

}