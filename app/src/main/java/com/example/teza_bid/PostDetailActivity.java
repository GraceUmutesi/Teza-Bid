package com.example.teza_bid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostDetailActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.rv_comment) RecyclerView rvComment;
    @BindView(R.id.comment) EditText etComment;
    @BindView(R.id.image_button) ImageButton send;

    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference commentdb;
    CommentAdapter commentAdapter;
    User u;
    List<Comment> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        init();


    }

    private void init() {
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        u=new User();

        ButterKnife.bind(this);

        send.setOnClickListener(this);
        comments = new ArrayList<>();

    }

    @Override
    public void onClick(View v) {
        if (!TextUtils.isEmpty(etComment.getText().toString())){
            Comment comment = new Comment(etComment.getText().toString(),u.getName());
            etComment.setText("");
            commentdb.push().setValue(comment);
        }

        else
        {
            Toast.makeText(getApplicationContext(),"The blank comment cannot be sent", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        final FirebaseUser currentUser = auth.getCurrentUser();

        u.setUid(currentUser.getUid());
        u.setEmail(currentUser.getEmail());

        firebaseDatabase.getReference("Users").child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                u = dataSnapshot.getValue(User.class);
                u.setUid(currentUser.getUid());
                AllMethods.name = u.getName();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        commentdb = firebaseDatabase.getReference("comments");
        commentdb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Comment comment = dataSnapshot.getValue(Comment.class);
                comment.setKey(dataSnapshot.getKey());
                comments.add(comment);
                displayComments(comments);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Comment comment = dataSnapshot.getValue(Comment.class);
                comment.setKey(dataSnapshot.getKey());

                List<Comment> newComments = new ArrayList<Comment>();

                for (Comment com: comments){
                    if (com.getKey().equals(comment.getKey())){
                        newComments.add(comment);
                    }
                    else
                    {
                        newComments.add(com);
                    }
                }
                comments = newComments;
                displayComments(comments);


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                Comment comment = dataSnapshot.getValue(Comment.class);
                comment.setKey(dataSnapshot.getKey());
                List<Comment> newComments = new ArrayList<Comment>();

                for (Comment com:comments){
                    if (!com.getKey().equals(comment.getKey())){
                        newComments.add(com);
                    }
                }

                comments = newComments;
                displayComments(comments);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        comments = new ArrayList<>();
    }

    private void displayComments(List<Comment> comments) {

        rvComment.setLayoutManager(new LinearLayoutManager(PostDetailActivity.this));
        rvComment.setHasFixedSize(true);
        commentAdapter = new CommentAdapter(PostDetailActivity.this,comments,commentdb);
        rvComment.setAdapter(commentAdapter);
    }
}
