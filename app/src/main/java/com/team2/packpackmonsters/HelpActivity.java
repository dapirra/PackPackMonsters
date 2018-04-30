package com.team2.packpackmonsters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class HelpActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setTitle(R.string.help_activity_title);

        //TEMPORARY
        Toast.makeText(this, "This has not been implemented yet.", Toast.LENGTH_LONG).show();
        finish();
    }
}
