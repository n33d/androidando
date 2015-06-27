package com.example.jdk.quicknote.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/**
 * Created by jdk on 20/06/15.
 */
//public perche deve essere visibile al sistema, nessuno la usa, solo il sistema operativo
public class QuickNoteProvider extends ContentProvider {

    private static final int ALL_NOTES = 1;
    private static final int ONE_NOTE = 2;

    private NoteDbOpenHelper mHelper;
    private static final UriMatcher MATCHER = createMatcher();

    private static UriMatcher createMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(Contract.AUTHORITY, Contract.Note.PATH,ALL_NOTES);
        uriMatcher.addURI(Contract.AUTHORITY,Contract.Note.PATH+"/#",ONE_NOTE);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        mHelper = new NoteDbOpenHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int match = MATCHER.match(uri);
        Cursor cursor=null;
        switch (match){
            case ALL_NOTES : cursor= Dao.getAllNotes(mHelper,selection,sortOrder);break;
            case ONE_NOTE : {
                cursor = Dao.getNote(mHelper, ContentUris.parseId(uri));
                Log.i("switch","one note uri match");

            }break;
            default:Log.i("switch","no uri match");
        }
        if(cursor != null){
            cursor.setNotificationUri(
                    getContext().getContentResolver(),
                    uri
            );
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        int match= MATCHER.match(uri);
        switch (match){
            case ALL_NOTES:
                return ContentResolver.CURSOR_DIR_BASE_TYPE+"/vnd.note";
            case ONE_NOTE:
                return ContentResolver.CURSOR_ITEM_BASE_TYPE+"/vnd.note";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match=MATCHER.match(uri);
        if(match == ALL_NOTES){
            if(values.containsKey(Contract.Note._ID))return null;
            long id = Dao.inserNote(mHelper,values);
            if(id == -1){
                return null;
            }
            Uri ret= ContentUris.withAppendedId(Contract.Note.CONTENT_URI,id);
            if(ret!=null){
                getContext().getContentResolver().notifyChange(Contract.Note.CONTENT_URI,null);
            }
            return ret;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
