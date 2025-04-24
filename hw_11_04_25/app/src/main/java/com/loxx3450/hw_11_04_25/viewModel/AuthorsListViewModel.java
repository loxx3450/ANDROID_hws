package com.loxx3450.hw_11_04_25.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.loxx3450.hw_11_04_25.App;
import com.loxx3450.hw_11_04_25.dao.AuthorDao;
import com.loxx3450.hw_11_04_25.db.AppDatabase;
import com.loxx3450.hw_11_04_25.entity.Author;

import java.util.List;

public class AuthorsListViewModel extends AndroidViewModel {

    private final AuthorDao authorDao;

    public AuthorsListViewModel(@NonNull Application application) {
        super(application);

        authorDao = App.getInstance().getDatabase().authorDao();
    }

    public LiveData<List<Author>> getAuthors() {
        return authorDao.getAll();
    }
}
