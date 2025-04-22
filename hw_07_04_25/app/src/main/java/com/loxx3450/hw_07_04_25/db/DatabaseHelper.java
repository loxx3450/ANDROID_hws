package com.loxx3450.hw_07_04_25.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contacts.db";
    private static final int SCHEMA = 1;
    public static final String TABLE = "contacts";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT NOT NULL," +
                "%s TEXT NOT NULL," +
                "%s TEXT NOT NULL);",
                TABLE, COLUMN_ID, COLUMN_NAME, COLUMN_PHONE, COLUMN_EMAIL));

        addDefaultData(db);
    }

    private void addDefaultData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO " + TABLE + " (" + COLUMN_NAME
                + ", " + COLUMN_PHONE + ", " + COLUMN_EMAIL  + ") " +
                "VALUES ('John Doe', '123-456-7890', 'johndoe@example.com')," +
                    "('Jane Smith', '987-654-3210', 'janesmith@example.com')," +
                    "('Alice Brown', '555-123-4567', 'alicebrown@example.com');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
