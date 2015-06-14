package com.example.jdk.quicknote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import me.aktor.quicknote.R;

/**
 * Created by jdk on 13/06/15.
 */
public class ShowResult extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_note);
        TextView textView=(TextView) findViewById(R.id.showNote);
        Intent intent = getIntent();
        String param= intent.getStringExtra("SHOW_NOTE");
        textView.setText(param);

    }
}
