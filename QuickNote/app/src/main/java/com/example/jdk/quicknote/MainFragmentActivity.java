package com.example.jdk.quicknote;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.example.jdk.quicknote.data.Note;


public class MainFragmentActivity extends FragmentActivity implements OnSaveNoteListener,OnDeleteListener {

    public static final int TASK_ADD = 1;
//    private FragmentCreateNote mCreate;
//    private FragmentList mList;
    private NoteTask mTask;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragments);

        mTask = NoteTask.newInstance(this);
        /*FragmentManager fm = getSupportFragmentManager();
        mCreate =(FragmentCreateNote) fm.findFragmentById(R.id.FragmentInsert);
        mList = (FragmentList)fm.findFragmentById(R.id.FragmentList);*/

    }


    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof FragmentCreateNote) {
            ((FragmentCreateNote) fragment).setOnSaveNoteListener(this);
        }
        if (fragment instanceof FragmentList) {
            ((FragmentList) fragment).setOnDeleteListener(this);
        }
    }

    public void addNote(Note note){
        mTask.addNote(TASK_ADD, note);
//        mList.addNote(note);
    }

    @Override
    public void onSaveNote(Note note) {
        addNote(note);
    }

    @Override
    public void onDeleteNote(long noteId) {
        deeleteNote(noteId);
    }

    private void deeleteNote(long noteId) {
        mTask.deleteNote(noteId);

    }
}
