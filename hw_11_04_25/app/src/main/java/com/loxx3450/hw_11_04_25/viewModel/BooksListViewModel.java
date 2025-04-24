package com.loxx3450.hw_11_04_25.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.loxx3450.hw_11_04_25.App;
import com.loxx3450.hw_11_04_25.dao.BookDao;
import com.loxx3450.hw_11_04_25.db.AppDatabase;
import com.loxx3450.hw_11_04_25.dto.BookFullDto;
import com.loxx3450.hw_11_04_25.entity.Author;
import com.loxx3450.hw_11_04_25.entity.Book;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

public class BooksListViewModel extends AndroidViewModel {

    private final BookDao bookDao;

    public BooksListViewModel(@NonNull Application application) {
        super(application);

        bookDao = App.getInstance()
                .getDatabase()
                .bookDao();
    }

    public LiveData<List<BookFullDto>> getBooks() {
        return bookDao.getAllWithAuthors();
    }

    public LiveData<List<BookFullDto>> getBooksByAuthorId(int authorId) {
        return bookDao.getAllByAuthorIdWithAuthors(authorId);
    }

    public Completable deleteBook(Book book) {
        return bookDao.delete(book)
            .subscribeOn(Schedulers.io());
    }
}
