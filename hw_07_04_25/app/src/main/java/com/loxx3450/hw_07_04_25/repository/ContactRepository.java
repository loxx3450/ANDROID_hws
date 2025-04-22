package com.loxx3450.hw_07_04_25.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.loxx3450.hw_07_04_25.db.DatabaseHelper;
import com.loxx3450.hw_07_04_25.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    private SQLiteDatabase database;
    private final DatabaseHelper dbHelper;

    public ContactRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    private void openReadable() {
        database = dbHelper.getReadableDatabase();
    }

    private void open() {
        database = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }

    public List<Contact> getAll() {
        openReadable();

        Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE, null);

        List<Contact> contacts = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PHONE));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL));

                Contact contact = new Contact(id, name, phone, email);
                contacts.add(contact);
            } while (cursor.moveToNext());
        }

        cursor.close();

        close();

        return contacts;
    }

    public void add(Contact contact) {
        open();

        ContentValues values = getContentValues(contact);

        database.insert(DatabaseHelper.TABLE, null, values);

        close();
    }

    public void update(Contact contact) {
        open();

        ContentValues values = getContentValues(contact);

        database.update(
                DatabaseHelper.TABLE,
                values,
                DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(contact.getId())}
        );

        close();
    }

    public void delete(int id) {
        open();

        database.delete(
                DatabaseHelper.TABLE,
                DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}
        );

        close();
    }

    private ContentValues getContentValues(Contact contact) {
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COLUMN_NAME, contact.getName());
        values.put(DatabaseHelper.COLUMN_PHONE, contact.getPhone());
        values.put(DatabaseHelper.COLUMN_EMAIL, contact.getEmail());

        return values;
    }
}
