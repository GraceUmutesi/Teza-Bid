package com.example.teza_bid;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentAdapterViewHolder> {


    Context context;
    List<Comment> comments;
    DatabaseReference commentDb;

    public CommentAdapter(Context context, List<Comment> comments, DatabaseReference commentDb){
        this.context=context;
        this.commentDb=commentDb;
        this.comments=comments;
    }
    @Override
    public CommentAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_comment,parent,false);
        return new CommentAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapterViewHolder holder, int position) {

        Comment comment = comments.get(position);

        if (comment.getName().equals(AllMethods.name)){
            holder.tvTitle.setText("You: " +comment.getComment());
            holder.tvTitle.setGravity(Gravity.START);
            holder.l1.setBackgroundColor(Color.parseColor("EF9E73"));

        }
        else
        {
            holder.tvTitle.setText(comment.getName() + ": \n" +comment.getComment());
            holder.tvTitle.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        ImageButton ibDelete;
        LinearLayout l1;

        public CommentAdapterViewHolder( View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            ibDelete = (ImageButton) itemView.findViewById(R.id.ibDelete);
            l1 = (LinearLayout) itemView.findViewById(R.id.l1Comment) ;

            ibDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commentDb.child(comments.get(getAdapterPosition()).getKey()).removeValue();
                }
            });
        }
    }
}
