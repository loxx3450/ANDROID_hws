package com.loxx3450.hw_11_04_25.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.loxx3450.hw_11_04_25.dto.BookFullDto;

@Entity(
    foreignKeys = @ForeignKey(
        entity = Author.class,
        parentColumns = "id",
        childColumns = "author_id",
        onDelete = ForeignKey.CASCADE
    ),
    indices = @Index(value = "author_id")
)
public class Book {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String genre;
    @ColumnInfo(name = "release_year")
    private int releaseYear;
    @ColumnInfo(name = "author_id")
    private int authorId;

    public Book() {

    }

    @Ignore
    public Book(String title, String genre, int releaseYear, int authorId) {
        this.id = 0;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.authorId = authorId;
    }

    @Ignore
    public Book(String title, String genre, int releaseYear) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    @Ignore
    public Book(int id, String title, String genre, int releaseYear, int authorId) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.authorId = authorId;
    }

    @Ignore
    public Book(BookFullDto bookFullDto) {
        this.id = bookFullDto.getId();
        this.title = bookFullDto.getTitle();
        this.genre = bookFullDto.getGenre();
        this.releaseYear = bookFullDto.getReleaseYear();
        this.authorId = bookFullDto.getAuthor().getId();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
