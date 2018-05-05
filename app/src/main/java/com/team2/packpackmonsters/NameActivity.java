package com.team2.packpackmonsters;

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

        nameEdit.setOnEditorActionListener(new NameEditOnEditorActionListener());
    }

    private class NameEditOnEditorActionListener implements TextView.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                Settings.STATISTICS.name = nameEdit.getText().toString();
                Settings.saveData();
                finish();
                return true;
            }
            return false;
        }
    }
}
