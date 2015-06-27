package com.example.jdk.quicknote.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jdk on 20/06/15.
 */
public class Dao {
    public static Cursor getAllNotes(NoteDbOpenHelper helper,String select,String order){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(Contract.Note.PATH,null,select,null,null,null,order);
        return cursor;
    }

    public static Cursor getNote(NoteDbOpenHelper helper, long id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(Contract.Note.PATH, null, Contract.Note._ID + " = " + id, null, null, null, null);
        return cursor;
    }

    public static long inserNote(NoteDbOpenHelper helper, ContentValues values){
        SQLiteDatabase db = helper.getWritableDatabase();
        long id = db.insert(Contract.Note.PATH, null, values);
        return id;
    }

    public static int deleteNote(NoteDbOpenHelper mHelper, long id) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        return db.delete(Contract.Note.PATH,Contract.Note._ID + " = " + id,null);
    }
}
