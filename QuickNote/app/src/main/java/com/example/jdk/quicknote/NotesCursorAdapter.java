package com.example.jdk.quicknote;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.jdk.quicknote.data.Contract;

/**
 * Created by Andrea Tortorella on 6/20/15.
 */
class NotesCursorAdapter extends CursorAdapter implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor note = (Cursor) getItem(position);
        String[] params=new String[3];
        params[0]=note.getString(
                note.getColumnIndexOrThrow(Contract.Note.TITLE)
        );
        params[1]=note.getString(
                note.getColumnIndexOrThrow(Contract.Note.DATE)
        );
        params[2]=note.getString(
                note.getColumnIndexOrThrow(Contract.Note.CONTENT)
        );
        showItemContent(params);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }

    private static class ViewHolder {
        TextView title;
        TextView date;

        public void bind(Cursor note) {
            int titleIdx = note.getColumnIndexOrThrow(Contract.Note.TITLE);
            int contentIdx =note.getColumnIndexOrThrow(Contract.Note.DATE);

            String titleString = note.getString(titleIdx);
            String dateString = note.getString(contentIdx);

            title.setText(titleString);
            date.setText(dateString);

        }
    }


    private final LayoutInflater mInflater;

    public NotesCursorAdapter(Context context) {
        super(context, /*Cursor*/null,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_note, parent, false);
        ViewHolder h = new ViewHolder();
        h.title =(TextView)view.findViewById(R.id.tv_note_title);
        h.date = (TextView)view.findViewById(R.id.tv_note_date);
        view.setTag(h);
        return view;
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder)view.getTag();
        holder.bind(cursor);
    }

    private void showItemContent(String[] content){
        Log.i("tag", "showItemContent()");
        Intent intent = new Intent(mContext,ShowResult.class);
        intent.putExtra("SHOW_NOTE", content);
        mContext.startActivity(intent);
        //this.startActivityForResult(intent, 1);
    }
}
