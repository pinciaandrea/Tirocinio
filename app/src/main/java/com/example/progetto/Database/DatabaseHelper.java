package com.example.progetto.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.progetto.Database.Model.Note;
import com.example.progetto.Database.Model.Umbrella;
import com.example.progetto.Umbrella_obj;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "notes_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Note.CREATE_TABLE);
        db.execSQL(Umbrella.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Note.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Umbrella.TABLE_NAME);

        onCreate(db);
    }


    public long insertUmbrella(int id, boolean prenotato) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Umbrella.COLUMN_ID, id);
        values.put(Umbrella.COLUMN_VALUE, prenotato);
        long tk = db.insert(Umbrella.TABLE_NAME, null, values);
        db.close();
        return tk;
    }

    public void InsertAllUmbrella(){
        insertUmbrella(0, false);  //ombrellone15
        insertUmbrella(1, false); //ombrellone14
        insertUmbrella(2, false); //ombrellone13
        insertUmbrella(3, false); //ombrellone12
        insertUmbrella(4, false); //ombrellone11
        insertUmbrella(5, false); //ombrellone10
        insertUmbrella(6, false); //ombrellone9
        insertUmbrella(7, false); //ombrellone8
        insertUmbrella(8, false); //ombrellone7
        insertUmbrella(9, false); //ombrellone6
        insertUmbrella(10, false); //ombrellone5
        insertUmbrella(11, false);  //ombrellone4
        insertUmbrella(12, true);  //ombrellone3
        insertUmbrella(13, false); //ombrellone2
        insertUmbrella(14, true); //ombrellone1
        insertUmbrella(15, true); //ombrellone0


    }

    public long insertNote(String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Note.COLUMN_NOTE, note);
        // insert row
        long id = db.insert(Note.TABLE_NAME, null, values);
        // close db connection
        db.close();
        // return newly inserted row id
        return id;
    }

    public Note getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Note.TABLE_NAME,
                new String[]{Note.COLUMN_ID, Note.COLUMN_NOTE, Note.COLUMN_TIMESTAMP},
                Note.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Note note = new Note(
                cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)));
        cursor.close();

        return note;
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Note.TABLE_NAME + " ORDER BY " +
                Note.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)));
                note.setNote(cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();
        // return notes list
        return notes;
    }

    public List<Umbrella_obj> getAllUmbrella() {
        List<Umbrella_obj> umbrellas = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Umbrella.TABLE_NAME + " ORDER BY " +
                Umbrella.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Umbrella_obj umbrella = new Umbrella_obj();
                umbrella.setId(cursor.getInt(cursor.getColumnIndex(Umbrella.COLUMN_ID)));
                umbrella.setPrenotato(cursor.getInt(cursor.getColumnIndex(Umbrella.COLUMN_VALUE)));

                umbrellas.add(umbrella);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();
        // return notes list
        return umbrellas;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Note.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

    public int updateUmbrella(Umbrella_obj umbrella) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Umbrella.COLUMN_VALUE, umbrella.getPrenotato());

        // updating row
        return db.update(Umbrella.TABLE_NAME, values, Umbrella.COLUMN_ID + " = ?",
                new String[]{String.valueOf(umbrella.getId())});
    }

    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Note.COLUMN_NOTE, note.getNote());

        // updating row
        return db.update(Note.TABLE_NAME, values, Note.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public void deleteNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Note.TABLE_NAME, Note.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }

}
