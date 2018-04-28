package com.team2.packpackmonsters;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BattleActivity extends AppCompatActivity {

    private ViewFlipper battleVfp;
    private ArrayList<ImageView> battleImgs;
    private ArrayList<TextView> txtsCurrentHealth;
    private ArrayList<TextView> txtsMaxHealth;
    private ConstraintLayout battleCloTopHealth;
    private ConstraintLayout battleCloTop;
    private ConstraintLayout battleCloBotHealth;
    private ConstraintLayout battleCloBot;
    private ArrayList<ImageView> itemImgs;
    private ArrayList<ImageView> partyImgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        setTitle(getResources().getString(R.string.battle_activity_title));

        battleVfp = findViewById(R.id.battle_vfp);

        //TODO Use these to initialize image for first monster sent out and when another monster is selected using party button.
        battleImgs = new ArrayList<>();
        battleImgs.add((ImageView) findViewById(R.id.battle_img_top_player));
        battleImgs.add((ImageView) findViewById(R.id.battle_img_bot_player));

        //TODO Use these to initialize current health and when a monster's health changes.
        txtsCurrentHealth = new ArrayList<>();
        txtsCurrentHealth.add((TextView) findViewById(R.id.battle_txt_top_player_current_health));
        txtsCurrentHealth.add((TextView) findViewById(R.id.battle_txt_bot_player_current_health));

        //TODO Use these to initialize max health.
        txtsMaxHealth = new ArrayList<>();
        txtsMaxHealth.add((TextView) findViewById(R.id.battle_txt_top_player_max_health));
        txtsMaxHealth.add((TextView) findViewById(R.id.battle_txt_bot_player_max_health));

        battleCloTopHealth = findViewById(R.id.battle_clo_top_health);
        battleCloTop = findViewById(R.id.battle_clo_top);
        battleCloBotHealth = findViewById(R.id.battle_clo_bot_health);
        battleCloBot = findViewById(R.id.battle_clo_bot);

        itemImgs = new ArrayList<>();
        itemImgs.add((ImageView)findViewById(R.id.player_items_img_first));
        itemImgs.add((ImageView)findViewById(R.id.player_items_img_second));
        itemImgs.add((ImageView)findViewById(R.id.player_items_img_third));

        //TODO Use these to initialize party's images.
        partyImgs = new ArrayList<>();
        partyImgs.add((ImageView)findViewById(R.id.party_img_first));
        partyImgs.add((ImageView)findViewById(R.id.party_img_second));
        partyImgs.add((ImageView)findViewById(R.id.party_img_third));

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            adjustLayoutToLandscape();
        }

        initializeListeners();
        setPartyImages();
    }

    private void initializeListeners()
    {
        ArrayList<Button> battleBtns = new ArrayList<>();
        battleBtns.add((Button) findViewById(R.id.battle_btn_top_left));
        battleBtns.add((Button) findViewById(R.id.battle_btn_top_right));
        battleBtns.add((Button) findViewById(R.id.battle_btn_bot_left));
        battleBtns.add((Button) findViewById(R.id.battle_btn_bot_right));

        for (Button btn : battleBtns) {
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
    public void onBackPressed() {
        if (battleVfp.getDisplayedChild() == 0) {
            super.onBackPressed();
        } else {
            battleVfp.setDisplayedChild(0);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            adjustLayoutToLandscape();
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            adjustLayoutToPortrait();
        }
    }

    private void adjustLayoutToLandscape()
    {
        for(ImageView img:  battleImgs)
        {
            img.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.battle_img_width_landscape);
            img.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.battle_img_height_landscape);
        }

        for(ImageView img: itemImgs)
        {
            img.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.player_items_img_width_landscape);
            img.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.player_items_img_height_landscape);
        }

        for(ImageView img: partyImgs)
        {
            img.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.party_img_width_landscape);
            img.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.party_img_height_landscape);
        }

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(battleCloTop);
        constraintSet.clear(battleCloTopHealth.getId(), ConstraintSet.BOTTOM);
        constraintSet.applyTo(battleCloTop);

        constraintSet = new ConstraintSet();
        constraintSet.clone(battleCloBot);
        constraintSet.clear(battleCloBotHealth.getId(), ConstraintSet.TOP);
        constraintSet.applyTo(battleCloBot);
    }

    private void adjustLayoutToPortrait()
    {
        for(ImageView img:  battleImgs)
        {
            img.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.battle_img_width);
            img.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.battle_img_height);
        }

        for(ImageView img: itemImgs)
        {
            img.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.player_items_img_width);
            img.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.player_items_img_height);
        }

        for(ImageView img: partyImgs)
        {
            img.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.party_img_width);
            img.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.party_img_height);
        }

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(battleCloTop);
        constraintSet.connect(battleCloTopHealth.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
        constraintSet.applyTo(battleCloTop);

        constraintSet = new ConstraintSet();
        constraintSet.clone(battleCloBot);
        constraintSet.connect(battleCloBotHealth.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSet.applyTo(battleCloBot);
    }

    private class BattleBtnOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.battle_btn_top_left://Battle button
                    battleVfp.setDisplayedChild(1);
                    break;
                case R.id.battle_btn_top_right: //Items
                    battleVfp.setDisplayedChild(2);
                    break;
                case R.id.battle_btn_bot_left: //Party
                    battleVfp.setDisplayedChild(3);
                    break;
                case R.id.battle_btn_bot_right: //Run
                    Intent intent = new Intent(v.getContext(), BattleResultActivity.class);
                    intent.putExtra(BattleResultActivity.WINNER_KEY, false);

                    startActivity(intent);
                    break;
            }
        }
    }

    private class PartyCloOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //TODO Party OnClickListener() functionality, including changing images
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
            //TODO Item OnClickListener() functionality
            switch(v.getId())
            {
                case R.id.player_items_clo_first: //Revive
                    break;
                case R.id.player_items_clo_second: //Attack up
                    break;
                case R.id.player_items_clo_third: //Heal
                    break;
            }
        }
    }

    private class MoveBtnOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //TODO Move OnClickListener() functionality
            switch(v.getId())
            {
                case R.id.moves_btn_top_left: //Ele gen
                    break;
                case R.id.moves_btn_top_right: //SP
                    break;
                case R.id.moves_btn_bot_left: //Gen
                    break;
                case R.id.moves_btn_bot_right: //SP 2
                    break;
            }
        }
    }

    private void setPartyImages()
    {
        //TODO Add party images.

        ArrayList<Drawable> images = new ArrayList<>(partyImgs.size());

        for(int i = 0; i < images.size(); i++)
        {
            partyImgs.get(i).setImageDrawable(images.get(i));
        }
    }
}
