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
        TextView content=(TextView) findViewById(R.id.showNote_content);
        TextView date=(TextView) findViewById(R.id.showNote_date);
        TextView title=(TextView) findViewById(R.id.showNote_title);

        Intent intent = getIntent();
        String[] params= intent.getStringArrayExtra("SHOW_NOTE");
        title.setText(params[0]);
        date.setText(params[1]);
        content.setText(params[2]);

    }
}
