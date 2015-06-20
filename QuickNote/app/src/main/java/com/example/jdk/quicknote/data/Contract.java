package com.example.jdk.quicknote.data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by jdk on 20/06/15.
 */
public final class Contract {
    public static final String AUTHORITY = "com.example.jdk.quicknote";

    public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY);
    private Contract(){}

    public static final class Note{

        private Note(){}

        static final String PATH = "note"; //tabella
        public static final Uri CONTENT_URI= Contract.CONTENT_URI.buildUpon().appendPath(PATH).build();

        public static Uri getNote(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

        public static final String _ID = BaseColumns._ID;
        public static final String TITLE ="title";
        public static final String CONTENT = "content";
        public static final String FAVOURITE = "favourite";
        public static final String DATE = "date";

    }
}
