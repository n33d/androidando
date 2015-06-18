package com.example.jdk.quicknote;

import android.app.Application;

import com.example.jdk.quicknote.data.data.Note;

import java.util.List;

import me.aktor.quicknote.R;

/**
 * Created by Andrea Tortorella on 6/13/15.
 */
public class QuickNoteApp extends Application {

    static QuickNoteApp self;

    private String mYVar;

    public List<Note> mNotes;


    @Override
    public void onCreate() {
        super.onCreate();
        self = this;
    }

    public static QuickNoteApp get(){
        return self;
    }

    public void setVar(String var){
        mYVar = this.getString(R.string.app_name);
    }

    public String getMyVar() {
        return mYVar;
    }
}
