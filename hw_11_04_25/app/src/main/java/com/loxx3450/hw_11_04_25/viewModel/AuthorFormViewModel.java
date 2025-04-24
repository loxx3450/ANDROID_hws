package com.loxx3450.hw_11_04_25.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.loxx3450.hw_11_04_25.App;
import com.loxx3450.hw_11_04_25.db.AppDatabase;
import com.loxx3450.hw_11_04_25.entity.Author;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class AuthorFormViewModel extends AndroidViewModel {
    public AuthorFormViewModel(@NonNull Application application) {
        super(application);
    }

    public Completable insertAuthor(Author author) {
        AppDatabase db = App.getInstance().getDatabase();

        return db.authorDao()
            .insert(author)
            .subscribeOn(Schedulers.io());
    }
}
