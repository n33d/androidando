package com.example.jdk.quicknote;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.jdk.quicknote.data.Contract;
import com.example.jdk.quicknote.data.Note;


public class NoteTask extends AsyncQueryHandler {
    private static final String TAG = "NoteTask";

    public static NoteTask newInstance(Context context){
        Context app = context.getApplicationContext();
        ContentResolver cr = app.getContentResolver();
        return new NoteTask(cr);
    }

    private NoteTask(ContentResolver cr) {
        super(cr);
    }


    public void addNote(int taskId,Note note){
        ContentValues values = new ContentValues();
        values.put(Contract.Note.TITLE,note.getTitle());
        values.put(Contract.Note.CONTENT,note.getContent());
        values.put(Contract.Note.DATE,note.getDate().toString());
        values.put(Contract.Note.FAVOURITE, note.getFavourite());
        Log.i("addNote()", Contract.Note.CONTENT_URI.toString());
        startInsert(taskId, null, Contract.Note.CONTENT_URI, values);
    }

    @Override
    protected void onInsertComplete(int token, Object cookie, Uri uri) {
        super.onInsertComplete(token, cookie, uri);
        Log.d(TAG, "Inserted: " + uri);
    }

    public void deleteNote(long noteId) {
        Uri requestUri= Contract.Note.CONTENT_URI.buildUpon().appendPath("delete").appendPath(String.valueOf(noteId)).build();
        Log.i("delete",requestUri.toString());
        startDelete(1234, null, requestUri, null, new String[]{String.valueOf(noteId)});
    }

    @Override
    protected void onDeleteComplete(int token, Object cookie, int result) {
        super.onDeleteComplete(token, cookie, result);
        Log.d(TAG, "Deleted");
    }
}
