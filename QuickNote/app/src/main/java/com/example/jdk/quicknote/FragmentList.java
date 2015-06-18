package com.example.jdk.quicknote;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jdk.quicknote.data.data.FakeData;
import com.example.jdk.quicknote.data.data.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import me.aktor.quicknote.R;


/**
 * Created by Andrea Tortorella on 6/13/15.
 */
public class FragmentList extends Fragment {

    private NotesAdapter mAdapter;
    private ListView mList;
    private List<Note> mNotes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        mNotes = new ArrayList<>();
        List<Note> notes = FakeData.generateMany(50);
        mNotes.addAll(notes);
        mList = (ListView)v.findViewById(R.id.list);
        mAdapter = new NotesAdapter(getActivity(),mNotes);
        mList.setOnItemClickListener(mAdapter);
        mList.setOnItemLongClickListener(mAdapter);
        mList.setAdapter(mAdapter);
        return v;
    }

    public void addNote(Note note) {
        mNotes.add(0, note);
        mAdapter.notifyDataSetChanged();
    }


    private class NotesAdapter extends BaseAdapter
            implements AdapterView.OnItemClickListener ,AdapterView.OnItemLongClickListener{

        private final List<Note> mAdapterNotes;
        private Context mContext;
        private final LayoutInflater mInflater;
        java.text.DateFormat sdf;

        private NotesAdapter(Context context,List<Note> notes){
            mContext = context;
            mAdapterNotes = notes;
            mInflater =  LayoutInflater.from(context);
            sdf = SimpleDateFormat.getDateInstance();
        }

        @Override
        public int getCount() {
            return mAdapterNotes.size();
        }

        @Override
        public Note getItem(int position) {
            return mAdapterNotes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.i("tag","onItemClick()");
            Note note = getItem(position);
            String[] strings=new String[3];
            strings[0]=note.title;
            strings[1]=note.date.toString();
            strings[2]=note.content;
            showItemContent(strings);
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Log.i("tag","onItemLongClick");
            deleteItem(position);
            return true;
        }

        private class ViewHolder {
            TextView title;
            TextView date;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.i("tag","getView()");
            ViewHolder h;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_note, parent, false);
                h = new ViewHolder();
                h.title =(TextView)convertView.findViewById(R.id.tv_note_title);
                h.date = (TextView)convertView.findViewById(R.id.tv_note_date);
                convertView.setTag(h);
            } else {
                h = (ViewHolder)convertView.getTag();
            }


            Note note = getItem(position);
            h.date.setText(sdf.format(note.date));
            h.title.setText(note.title);
            return convertView;
        }
    }

    private void deleteItem(int position) {
        mNotes.remove(position);
        mAdapter.notifyDataSetChanged();
    }

    private void showItemContent(String[] content){
        Log.i("tag","showItemContent()");
        Intent intent = new Intent(getActivity(),ShowResult.class);
        intent.putExtra("SHOW_NOTE", content);
        this.startActivity(intent);
        //this.startActivityForResult(intent, 1);
    }
}
