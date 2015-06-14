package com.example.jdk.quicknote;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.jdk.quicknote.data.data.Note;

import me.aktor.quicknote.R;


public class MainFragmentActivity extends FragmentActivity implements OnSaveNoteListener {

    private FragmentCreateNote mCreate;
    private FragmentList mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragments);

        FragmentManager fm = getSupportFragmentManager();
        mCreate =(FragmentCreateNote) fm.findFragmentById(R.id.FragmentInsert);
        mList = (FragmentList)fm.findFragmentById(R.id.FragmentList);

    }


    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof FragmentCreateNote) {
            ((FragmentCreateNote) fragment).setOnSaveNoteListener(this);
        }
    }

    public void addNote(Note note){
        mList.addNote(note);
    }

    @Override
    public void onSaveNote(Note note) {
        addNote(note);
    }
}