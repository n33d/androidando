package com.example.jdk.quicknote;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.jdk.quicknote.data.Contract.Note;

import java.util.Date;

/**
 * Created by jdk on 13/06/15.
 */
public class ShowResult extends Activity {

    private final LoaderManager.LoaderCallbacks<Cursor> callbacks =
            new LoaderManager.LoaderCallbacks<Cursor>() {
                @Override
                public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                    Uri requestUri=Note.CONTENT_URI.buildUpon().appendPath(String.valueOf(args.getLong("noteId"))).build();
                    Log.d("TEST", requestUri.toString());
                    CursorLoader loader = new CursorLoader(ShowResult.this,
                            requestUri,
                            /*projection*/null,
                            /*where*/null,
                            /*arg where*/null,
                            Note.TITLE+ " ASC");
                    return loader;
                }

                @Override
                public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                    if (data.moveToFirst()){
                        TextView content=(TextView) findViewById(R.id.showNote_content);
                        TextView date=(TextView) findViewById(R.id.showNote_date);
                        TextView title=(TextView) findViewById(R.id.showNote_title);
                        title.setText(data.getString(data.getColumnIndex(Note.TITLE)));
                        content.setText(data.getString(data.getColumnIndex(Note.CONTENT)));
                        date.setText(new Date(data.getInt(data.getColumnIndex(Note.DATE))).toString());
                    }
                }

                @Override
                public void onLoaderReset(Loader<Cursor> loader) {
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_note);
        Intent intent = getIntent();
        long noteId= intent.getLongExtra("SHOW_NOTE", 0);
        Bundle noteBundle=new Bundle();
        noteBundle.putLong("noteId", noteId);
        getLoaderManager().initLoader(/*id*/R.id.LOAD_ONE_NOTE, noteBundle, callbacks);
    }
}
