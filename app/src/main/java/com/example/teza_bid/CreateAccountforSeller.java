package com.example.teza_bid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

        }

    }

    private void createNewUser() {
        final String name = mNameOfUserEditText.getText().toString().trim();
        final String email = mEmailOfUserEditText.getText().toString().trim();
        String password = mPassworOfUserdEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            /*Intent intent= new Intent(CreateAccountforSeller.this, Sales.class);*/
                            Log.d(TAG, "Authentication successful");
                        } else {
                            Intent intent = new Intent(CreateAccountforSeller.this, Sales.class);
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

