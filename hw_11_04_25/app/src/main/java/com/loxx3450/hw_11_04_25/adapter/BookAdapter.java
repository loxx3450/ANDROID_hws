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
import com.loxx3450.hw_11_04_25.dto.BookFullDto;
import com.loxx3450.hw_11_04_25.entity.Author;
import com.loxx3450.hw_11_04_25.entity.Book;

import java.util.Collections;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    public interface OnBookClickListener {
        void onBookClick(BookFullDto book);
        void onBookDelete(BookFullDto book);
    }

    private List<BookFullDto> books;
    private final OnBookClickListener listener;

    public BookAdapter(Context context) {
        this.books = Collections.emptyList();

        if (context instanceof OnBookClickListener) {
            listener = (OnBookClickListener) context;
        } else {
            throw new RuntimeException("OnBookClickListener is not implemented");
        }
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.book_card, parent, false);

        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        BookFullDto book = books.get(position);

        holder.titleTextView.setText(book.getTitle());
        holder.genreTextView.setText(book.getGenre());
        holder.releaseYearTextView.setText(String.valueOf(book.getReleaseYear()));

        Author author = book.getAuthor();
        String authorFullName = author.getName() + author.getSurname();
        holder.authorTextView.setText(authorFullName.toString());

        // Callbacks
        holder.layout.setOnClickListener(v -> listener.onBookClick(book));
        holder.deleteButton.setOnClickListener(v -> listener.onBookDelete(book));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {

        private final ConstraintLayout layout;
        private final TextView titleTextView;
        private final TextView authorTextView;
        private final TextView genreTextView;
        private final TextView releaseYearTextView;
        private final Button deleteButton;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.bookCardLayout);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);
            genreTextView = itemView.findViewById(R.id.genreTextView);
            releaseYearTextView = itemView.findViewById(R.id.releaseYearTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    public void setBooks(List<BookFullDto> books) {
        this.books = books;
    }
}
