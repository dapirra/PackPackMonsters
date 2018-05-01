package com.team2.packpackmonsters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

public class HelpActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setTitle(R.string.help_activity_title);

        ListView helpLst = findViewById(R.id.help_lst);
        helpLst.setAdapter(new HelpAdapter(this));
    }
}
