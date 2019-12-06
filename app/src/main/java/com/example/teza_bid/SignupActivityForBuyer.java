package com.example.teza_bid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import com.google.firebase.auth.UserProfileChangeRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivityForBuyer extends AppCompatActivity implements View.OnClickListener{

    Button newUserButton;
    EditText newUserName;
    EditText newUserEmail;
    EditText newUserPassword;
    EditText passConfirmation;
    TextView backToLogin;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private ProgressDialog progressDialog;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_for_buyer);
//        ButterKnife.bind(this);

        newUserButton = (Button) findViewById(R.id.createUserButton);
        newUserName = (EditText) findViewById(R.id.nameEditText);
        newUserEmail = (EditText) findViewById(R.id.emailEditText);
        newUserPassword = (EditText) findViewById(R.id.passwordEditText);
        passConfirmation = (EditText) findViewById(R.id.confirmPasswordEditText);
        backToLogin = (TextView) findViewById(R.id.loginTextView);

        backToLogin.setOnClickListener(this);
        newUserButton.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
        createAuthProgressDialog();
    }

    public void createAuthProgressDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Authenticating with Firebase...");
        progressDialog.setCancelable(true);
    }

    @Override
    public void onClick(View view){
        if(view==backToLogin){
            Intent intent = new Intent(SignupActivityForBuyer.this, LoginActivityForBuyer.class);
            startActivity(intent);
        }
        if (view == newUserButton){
            createNewUser();
            Intent intent = new Intent(SignupActivityForBuyer.this, LoginActivityForBuyer.class);
            startActivity(intent);
        }
    }

    public void createNewUser(){
        final String userName = newUserName.getText().toString();
        final String email = newUserEmail.getText().toString();
        String password = newUserPassword.getText().toString();
        String confirm = passConfirmation.getText().toString().trim();
        name = newUserName.getText().toString().trim();

        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(userName);
        boolean validPassword = isValidPassword(password, confirm);
        if (!validEmail || !validName || !validPassword) return;
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (task.isSuccessful()){
                    createFirebaseUserProfile(task.getResult().getUser());
                }
                else {
                    Toast.makeText(SignupActivityForBuyer.this, "Authentication failed.", Toast.LENGTH_SHORT);
                }
            }
        });
    }
    private void createAuthStateListener(){
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Intent intent = new Intent(SignupActivityForBuyer.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };

    }
    private boolean isValidEmail(String email){
        boolean isGoodEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail){
            newUserEmail.setError("Please enter a valid address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidName(String name){
        if (name.equals("")){
            newUserName.setError("Please enter your name");
            return false;
        }
        return true;
    }
    private boolean isValidPassword(String password, String confirmPassword){
        if (password.length()<6){
            newUserPassword.setError("Please create a password containing at least 6 characters");
            return false;
        }
        else if (!password.equals(confirmPassword)){
            newUserPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    private void createFirebaseUserProfile(final FirebaseUser user){
        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(name).build();
        user.updateProfile(addProfileName).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SignupActivityForBuyer.this, "The displayed name has been set", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
