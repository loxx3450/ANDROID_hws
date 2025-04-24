package com.loxx3450.hw_11_04_25.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.loxx3450.hw_11_04_25.R;
import com.loxx3450.hw_11_04_25.entity.Author;

import java.util.Collections;
import java.util.List;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder> {

    public interface OnAuthorClickListener {
        void onAuthorClick(Author author);
    }

    private List<Author> authors;
    private final OnAuthorClickListener listener;

    public AuthorAdapter(Context context) {
        this.authors = Collections.emptyList();

        if (context instanceof OnAuthorClickListener) {
            listener = (OnAuthorClickListener) context;
        } else {
            throw new RuntimeException("OnAuthorClickListener is not implemented");
        }
    }

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.author_card, parent, false);

        return new AuthorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder holder, int position) {
        Author author = authors.get(position);

        holder.nameTextView.setText(author.getName());
        holder.surnameTextView.setText(author.getSurname());

        // Callbacks
        holder.layout.setOnClickListener(v -> listener.onAuthorClick(author));
    }

    @Override
    public int getItemCount() {
        return authors.size();
    }

    public static class AuthorViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout layout;
        private final TextView nameTextView;
        private final TextView surnameTextView;

        public AuthorViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.authorCardLayout);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            surnameTextView = itemView.findViewById(R.id.surnameTextView);
        }
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
