package com.team2.packpackmonsters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class IndividualPackPackActivity extends AppCompatActivity
{
    public static final String PACK_PACK_KEY = "Pack Pack";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_pack_pack);

        TextView indPackTxtName = findViewById(R.id.ind_pack_txt_name);
        indPackTxtName.setText(getIntent().getStringExtra(PACK_PACK_KEY));
    }
}
