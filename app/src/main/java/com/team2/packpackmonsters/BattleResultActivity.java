package com.team2.packpackmonsters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BattleResultActivity extends AppCompatActivity
{
    public static final String WINNER_KEY = "Winner";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_result);

        boolean winner = getIntent().getBooleanExtra(WINNER_KEY, false);

        TextView resultTxt = findViewById(R.id.battle_result_txt);
        String resultText;

        if(winner)
        {
            resultText = getResources().getString(R.string.victory_text);
        }
        else
        {
            resultText = getResources().getString(R.string.lose_text);
        }

        resultTxt.setText(resultText);

        Button homeBtn = findViewById(R.id.battle_result_btn);
        homeBtn.setOnClickListener(new HomeBtnOnClickListener());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(this, MainActivity.class));
    }

    private class HomeBtnOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            startActivity(new Intent(v.getContext(), MainActivity.class));
        }
    }
}
