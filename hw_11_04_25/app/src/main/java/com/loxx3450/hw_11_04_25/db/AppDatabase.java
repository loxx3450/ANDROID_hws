package com.loxx3450.hw_11_04_25.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.loxx3450.hw_11_04_25.dao.AuthorDao;
import com.loxx3450.hw_11_04_25.dao.BookDao;
import com.loxx3450.hw_11_04_25.entity.Author;
import com.loxx3450.hw_11_04_25.entity.Book;

@Database(entities = {Author.class, Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AuthorDao authorDao();
    public abstract BookDao bookDao();
}
