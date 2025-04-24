package com.loxx3450.hw_11_04_25.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.loxx3450.hw_11_04_25.App;
import com.loxx3450.hw_11_04_25.dao.AuthorDao;
import com.loxx3450.hw_11_04_25.dao.BookDao;
import com.loxx3450.hw_11_04_25.db.AppDatabase;
import com.loxx3450.hw_11_04_25.entity.Author;
import com.loxx3450.hw_11_04_25.entity.Book;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class BookFormViewModel extends AndroidViewModel {
    private final AuthorDao authorDao;
    private final BookDao bookDao;

    public BookFormViewModel(@NonNull Application application) {
        super(application);

        AppDatabase db = App.getInstance().getDatabase();

        authorDao = db.authorDao();
        bookDao = db.bookDao();
    }

    public LiveData<List<Author>> getAuthors() {
        return authorDao.getAll();
    }

    public Completable insertBook(Book book, Author selectedAuthor) {

        book.setAuthorId(selectedAuthor.getId());

        return bookDao
            .insert(book)
            .subscribeOn(Schedulers.io());
    }

    public Completable updateBook(int id, Book book, Author selectedAuthor) {

        book.setId(id);
        book.setAuthorId(selectedAuthor.getId());

        return bookDao
            .update(book)
            .subscribeOn(Schedulers.io());
    }
}
