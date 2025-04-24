package com.loxx3450.hw_11_04_25.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.loxx3450.hw_11_04_25.dto.BookFullDto;
import com.loxx3450.hw_11_04_25.entity.Author;
import com.loxx3450.hw_11_04_25.entity.Book;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface BookDao {

    @Query("SELECT b.id, b.title, b.genre, b.release_year AS releaseYear, " +
            "a.id AS author_id, a.name AS author_name, a.surname AS author_surname " +
            "FROM Book b " +
            "JOIN Author a ON b.author_id = a.id")
    LiveData<List<BookFullDto>> getAllWithAuthors();

    @Query("SELECT b.id, b.title, b.genre, b.release_year AS releaseYear, " +
            "a.id AS author_id, a.name AS author_name, a.surname AS author_surname " +
            "FROM Book b " +
            "JOIN Author a ON b.author_id = a.id " +
            "WHERE a.id = :authorId")
    LiveData<List<BookFullDto>> getAllByAuthorIdWithAuthors(int authorId);

    @Query("SELECT * FROM book WHERE id = :id")
    Single<Book> getById(int id);

    @Insert
    Completable insert(Book book);

    @Update
    Completable update(Book book);

    @Delete
    Completable delete(Book book);
}

