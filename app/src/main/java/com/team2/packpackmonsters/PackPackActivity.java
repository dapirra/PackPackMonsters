package com.team2.packpackmonsters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class PackPackActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack_pack);
        setTitle(getResources().getString(R.string.pack_pack_activity_title));

        ListView packLst = findViewById(R.id.pack_lst);
        packLst.setAdapter(new PackAdapter(this));
    }

}
