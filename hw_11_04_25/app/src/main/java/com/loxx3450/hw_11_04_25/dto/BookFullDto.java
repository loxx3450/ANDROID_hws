package com.loxx3450.hw_11_04_25.dto;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.loxx3450.hw_11_04_25.entity.Author;

public class BookFullDto implements Parcelable {
    private int id;
    private String title;
    private String genre;
    private int releaseYear;

    @Embedded(prefix = "author_")
    private Author author;

    public BookFullDto() {

    }

    protected BookFullDto(Parcel source) {
        id = source.readInt();
        title = source.readString();
        genre = source.readString();
        releaseYear = source.readInt();

        int authorId = source.readInt();
        String authorName = source.readString();
        String authorSurname = source.readString();
        author = new Author(authorId, authorName, authorSurname);
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(genre);
        dest.writeInt(releaseYear);

        dest.writeInt(author.getId());
        dest.writeString(author.getName());
        dest.writeString(author.getSurname());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BookFullDto> CREATOR = new Creator<BookFullDto>() {
        @Override
        public BookFullDto createFromParcel(Parcel source) {
            return new BookFullDto(source);
        }

        @Override
        public BookFullDto[] newArray(int size) {
            return new BookFullDto[size];
        }
    };

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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
