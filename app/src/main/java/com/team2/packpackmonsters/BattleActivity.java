package com.team2.packpackmonsters;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        setTitle(getResources().getString(R.string.battle_activity_title));

        battleVfp = findViewById(R.id.battle_vfp);

        initializeOnClickListeners();
    }

    private void initializeOnClickListeners()
    {
        ArrayList<Button> battleBtns = new ArrayList<>();
        battleBtns.add((Button)findViewById(R.id.battle_btn_top_left));
        battleBtns.add((Button)findViewById(R.id.battle_btn_top_right));
        battleBtns.add((Button)findViewById(R.id.battle_btn_bot_left));
        battleBtns.add((Button)findViewById(R.id.battle_btn_bot_right));

        for(Button btn: battleBtns)
        {
            btn.setOnClickListener(new BattleBtnOnClickListener());
        }

        ArrayList<ConstraintLayout> partyClos = new ArrayList<>();
        partyClos.add((ConstraintLayout)findViewById(R.id.party_clo_first));
        partyClos.add((ConstraintLayout)findViewById(R.id.party_clo_second));
        partyClos.add((ConstraintLayout)findViewById(R.id.party_clo_third));

        for(ConstraintLayout clo: partyClos)
        {
            clo.setOnClickListener(new PartyCloOnClickListener());
        }

        ArrayList<ConstraintLayout> playerItemsClos = new ArrayList<>();
        playerItemsClos.add((ConstraintLayout)findViewById(R.id.player_items_clo_first));
        playerItemsClos.add((ConstraintLayout)findViewById(R.id.player_items_clo_second));
        playerItemsClos.add((ConstraintLayout)findViewById(R.id.player_items_clo_third));

        for(ConstraintLayout clo: playerItemsClos)
        {
            clo.setOnClickListener(new PlayerItemsCloOnClickListener());
        }

        ArrayList<Button> moveBtns = new ArrayList<>();
        moveBtns.add((Button)findViewById(R.id.moves_btn_top_left));
        moveBtns.add((Button)findViewById(R.id.moves_btn_top_right));
        moveBtns.add((Button)findViewById(R.id.moves_btn_bot_left));
        moveBtns.add((Button)findViewById(R.id.moves_btn_bot_right));

        for(Button btn: moveBtns)
        {
            btn.setOnClickListener(new MoveBtnOnClickListener());
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
                    //TODO Replace with BattleResultActivity
                    startActivity(new Intent(v.getContext(), MainActivity.class));
                    break;
            }
        }
    }

    private class PartyCloOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.party_clo_first:
                    break;
                case R.id.party_clo_second:
                    break;
                case R.id.party_clo_third:
                    break;
            }
        }
    }

    private class PlayerItemsCloOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.player_items_clo_first:
                    break;
                case R.id.player_items_clo_second:
                    break;
                case R.id.player_items_clo_third:
                    break;
            }
        }
    }

    private class MoveBtnOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.moves_btn_top_left:
                    break;
                case R.id.moves_btn_top_right:
                    break;
                case R.id.moves_btn_bot_left:
                    break;
                case R.id.moves_btn_bot_right:
                    break;
            }
        }
    }
}
