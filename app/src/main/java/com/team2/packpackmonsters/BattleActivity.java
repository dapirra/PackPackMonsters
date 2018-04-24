package com.team2.packpackmonsters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class BattleActivity extends AppCompatActivity {

    private ViewFlipper battleVfp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        battleVfp = findViewById(R.id.battle_vfp);

        ArrayList<Button> battleBtns = new ArrayList<>();
        battleBtns.add((Button)findViewById(R.id.battle_btn_top_left));
        battleBtns.add((Button)findViewById(R.id.battle_btn_top_right));
        battleBtns.add((Button)findViewById(R.id.battle_btn_bot_left));
        battleBtns.add((Button)findViewById(R.id.battle_btn_bot_right));

        for(Button btn: battleBtns)
        {
            btn.setOnClickListener(new BattleBtnOnClickListener());
        }
    }

    @Override
    public void onBackPressed()
    {
        if(battleVfp.getDisplayedChild() == 0)
        {
            super.onBackPressed();
        }
        else
        {
            battleVfp.setDisplayedChild(0);
        }
    }

    private class BattleBtnOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.battle_btn_top_left:
                    battleVfp.setDisplayedChild(1);
                    break;
                case R.id.battle_btn_top_right:
                    battleVfp.setDisplayedChild(2);
                    break;
                case R.id.battle_btn_bot_left:
                    battleVfp.setDisplayedChild(3);
                    break;
                case R.id.battle_btn_bot_right:
                    startActivity(new Intent(v.getContext(), MainActivity.class));
                    break;
            }
        }
    }
}
