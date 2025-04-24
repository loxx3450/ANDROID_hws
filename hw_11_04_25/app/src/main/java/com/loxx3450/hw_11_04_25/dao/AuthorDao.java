package com.loxx3450.hw_11_04_25.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.loxx3450.hw_11_04_25.entity.Author;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface AuthorDao {

    @Query("SELECT * FROM author")
    LiveData<List<Author>> getAll();

    @Query("SELECT * FROM author WHERE id = :id")
    Single<Author> getById(int id);

    @Insert
    Completable insert(Author author);

    @Update
    Completable update(Author author);

    @Delete
    Completable delete(Author author);
}
