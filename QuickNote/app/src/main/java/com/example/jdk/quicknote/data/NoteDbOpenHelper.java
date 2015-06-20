package com.example.jdk.quicknote.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jdk on 20/06/15.
 */
class NoteDbOpenHelper extends SQLiteOpenHelper {
    private static final String NAME = "quick_note.db";
    private static final int VERSION = 3;

    public NoteDbOpenHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    private static final String CREATE_NOTE_TABLE =
            "CREATE TABLE IF NOT EXISTS "+Contract.Note.PATH+" (" +
                    Contract.Note._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +Contract.Note.TITLE + " TEXT NOT NULL, "
            +Contract.Note.CONTENT + " TEXT NOT NULL, "
            +Contract.Note.DATE + " INTEGER NOT NULL, "
            +Contract.Note.FAVOURITE +" TEXT NOT NULL )";
    private static final String DROP_NOTE_TABLE =
            "DROP TABLE "+Contract.Note.PATH;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion<newVersion) {
            db.execSQL(DROP_NOTE_TABLE);
            onCreate(db);
        }
    }
}
