package com.example.teza_bid.ui.sell;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.teza_bid.CreateAccountforSeller;
import com.example.teza_bid.LoginForSeller;
import com.example.teza_bid.R;
import com.example.teza_bid.Sales;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import butterknife.BindView;

public class SellFragment extends Fragment {

    private SellViewModel galleryViewModel;
    public static final String TAG = SellFragment.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @BindView(R.id.nameOfUserEditText)
    EditText mNameOfUserEditText;
    @BindView(R.id.emailOfUserEditText) EditText mEmailOfUserEditText;
    @BindView(R.id.passwordOfUserEditText) EditText mPassworOfUserdEditText;
    @BindView(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(SellViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sell, container, false);
        TextView mNameOfUserEditText=(TextView)root.findViewById(R.id.nameOfUserEditText);
        EditText mEmailOfUserEditText = (EditText)root.findViewById(R.id.emailOfUserEditText);
        EditText mPassworOfUserdEditText =(EditText) root.findViewById(R.id.passwordOfUserEditText);
        EditText  mConfirmPasswordEditText =(EditText) root.findViewById(R.id.confirmPasswordEditText);
        EditText  mPhoneNumber =(EditText) root.findViewById(R.id.phone);
        TextView mLoginTextView =(TextView) root.findViewById(R.id.loginTextView);
        TextView textTerm = (TextView)root.findViewById(R.id.terms);
        String text = "By tapping the button sign up, you agree to our terms and conditions and therefore will obey them";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                /* Toast.makeText(CreateAccountforSeller.this, "One", Toast.LENGTH_SHORT).show();*/
                if (widget == textTerm) {
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

        textTerm.setText(ss);
        textTerm.setMovementMethod(LinkMovementMethod.getInstance());
        mAuth = FirebaseAuth.getInstance();
        mLoginTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (v == mLoginTextView) {
                    Intent intent = new Intent(getActivity(), LoginForSeller.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });

        Button mcreate = (Button) root.findViewById(R.id.createUserButton);
        mcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), LoginForSeller.class);
                intent.putExtra("some","this is it");
                startActivity(intent);
            }
        });

        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private void createNewUser() {
        final String name = mNameOfUserEditText.getText().toString().trim();
        final String email = mEmailOfUserEditText.getText().toString().trim();
        String password = mPassworOfUserdEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Authentication successful");
                        } else {
                            Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();

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

                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(  user.getDisplayName());

                } else {                }
            }
        };
    }


    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}