package com.team2.packpackmonsters;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class BattleResultActivity extends AppCompatActivity {
    public static final String WINNER_KEY = "Winner";

    private MediaPlayer musicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_result);

        boolean result = getIntent().getBooleanExtra(WINNER_KEY, false);

        musicPlayer = MediaPlayer.create(this, result ? R.raw.win : R.raw.lose);
        musicPlayer.start();

        ((TextView) findViewById(R.id.battle_result_txt)).setText(
                result ? R.string.victory_text : R.string.lose_text);

        findViewById(R.id.battle_result_btn).setOnClickListener(new HomeBtnOnClickListener());

        findViewById(R.id.play_again_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BattleResultActivity.this, BattleActivity.class));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        musicPlayer.stop();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private class HomeBtnOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(BattleResultActivity.this, MainActivity.class));
        }
    }
}
