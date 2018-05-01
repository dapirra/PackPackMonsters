package com.team2.packpackmonsters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class NameActivity extends AppCompatActivity {

    EditText nameEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        nameEdit = findViewById(R.id.name_edit);

        //TODO If(name in database) then startActivity() -> MainActivity else continue

        //TEMPORARY
        if(temp_fetchName() != null)
        {
            startActivity(new Intent(this, MainActivity.class));
        }

        nameEdit.setOnEditorActionListener(new NameEditOnEditorActionListener());
    }

    private class NameEditOnEditorActionListener implements TextView.OnEditorActionListener
    {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
        {
            if(actionId == EditorInfo.IME_ACTION_DONE)
            {
                //TODO Save name to database.

                //TEMPORARY
                temp_saveName(nameEdit.getText().toString());

                startActivity(new Intent(v.getContext(), MainActivity.class));
                return true;
            }

            return false;
        }
    }

    private void temp_saveName(String name)
    {
        SharedPreferences pref = getSharedPreferences("app", Context.MODE_PRIVATE);
        pref.edit().putString("name", name).apply();
    }

    private String temp_fetchName()
    {
        SharedPreferences pref = getSharedPreferences("app", Context.MODE_PRIVATE);
        return pref.getString("name", null);
    }
}
