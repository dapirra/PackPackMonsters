package com.team2.packpackmonsters;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import static com.team2.packpackmonsters.Settings.allMonsters;
import static com.team2.packpackmonsters.Settings.opponentMonsters;
import static com.team2.packpackmonsters.Settings.playerMonsters;

public class BattleActivity extends AppCompatActivity {

    private ViewFlipper battleVfp;
    private ArrayList<ImageView> battleImgs;
    private ArrayList<TextView> battleTxtsCurrentHealth;
    private ArrayList<TextView> battleTxtsMaxHealth;
    private ArrayList<TextView> battleTxtsHealthLabel;
    private ArrayList<TextView> battleTxtsSlash;
    private ConstraintLayout battleCloTopHealth;
    private ConstraintLayout battleCloTop;
    private ConstraintLayout battleCloBotHealth;
    private ConstraintLayout battleCloBot;
    private ArrayList<ImageView> itemImgs;
    private ArrayList<TextView> itemTxtsName;
    private ArrayList<ImageView> partyImgs;
    private ArrayList<TextView> partyTxtsCurrentHealth;
    private ArrayList<TextView> partyTxtsMaxHealth;
    private ArrayList<TextView> partyTxtsType;
    private ArrayList<TextView> partyTxtsName;
    ArrayList<Button> moveBtns;
    private Monster currentPlayerMonster;
    private Monster currentOpponentMonster;
    private boolean monsterSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        setTitle(getResources().getString(R.string.battle_activity_title));
        Settings.loadData(this);

        initializeViews();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            adjustLayoutToLandscape();
        }

        initializeListeners();

        playerMonsters = new ArrayList<>();
        opponentMonsters = new ArrayList<>();

        playerMonsters.add(allMonsters.get((int) (Math.random() * allMonsters.size())).clone());
        playerMonsters.add(allMonsters.get((int) (Math.random() * allMonsters.size())).clone());
        playerMonsters.add(allMonsters.get((int) (Math.random() * allMonsters.size())).clone());

        opponentMonsters.add(allMonsters.get((int) (Math.random() * allMonsters.size())).clone());
        opponentMonsters.add(allMonsters.get((int) (Math.random() * allMonsters.size())).clone());
        opponentMonsters.add(allMonsters.get((int) (Math.random() * allMonsters.size())).clone());

        partyTxtsName.get(0).setText(playerMonsters.get(0).getName());
        partyTxtsName.get(1).setText(playerMonsters.get(1).getName());
        partyTxtsName.get(2).setText(playerMonsters.get(2).getName());

        partyImgs.get(0).setImageResource(playerMonsters.get(0).getImage());
        partyImgs.get(1).setImageResource(playerMonsters.get(1).getImage());
        partyImgs.get(2).setImageResource(playerMonsters.get(2).getImage());

        partyTxtsCurrentHealth.get(0).setText(playerMonsters.get(0).getCurrentHp() + "");
        partyTxtsCurrentHealth.get(1).setText(playerMonsters.get(1).getCurrentHp() + "");
        partyTxtsCurrentHealth.get(2).setText(playerMonsters.get(2).getCurrentHp() + "");

        partyTxtsMaxHealth.get(0).setText(playerMonsters.get(0).getMaxHp() + "");
        partyTxtsMaxHealth.get(1).setText(playerMonsters.get(1).getMaxHp() + "");
        partyTxtsMaxHealth.get(2).setText(playerMonsters.get(2).getMaxHp() + "");

        partyTxtsType.get(0).setText(playerMonsters.get(0).getTypeString());
        partyTxtsType.get(1).setText(playerMonsters.get(1).getTypeString());
        partyTxtsType.get(2).setText(playerMonsters.get(2).getTypeString());
    }

    private void initializeViews()
    {

        battleVfp = findViewById(R.id.battle_vfp);

        //TODO Use these to initialize image for first monster sent out and when another monster is selected using party button.
        battleImgs = new ArrayList<>();
        battleImgs.add((ImageView) findViewById(R.id.battle_img_top_player));
        battleImgs.add((ImageView) findViewById(R.id.battle_img_bot_player));

        //TODO Use these to initialize monster's name.
        battleTxtsHealthLabel = new ArrayList<>();
        battleTxtsHealthLabel.add((TextView) findViewById(R.id.battle_txt_top_player_health_label));
        battleTxtsHealthLabel.add((TextView) findViewById(R.id.battle_txt_bot_player_health_label));

        //TODO Use these to initialize current health and when a monster's health changes.
        battleTxtsCurrentHealth = new ArrayList<>();
        battleTxtsCurrentHealth.add((TextView) findViewById(R.id.battle_txt_top_player_current_health));
        battleTxtsCurrentHealth.add((TextView) findViewById(R.id.battle_txt_bot_player_current_health));

        //TODO Use these to initialize max health.
        battleTxtsMaxHealth = new ArrayList<>();
        battleTxtsMaxHealth.add((TextView) findViewById(R.id.battle_txt_top_player_max_health));
        battleTxtsMaxHealth.add((TextView) findViewById(R.id.battle_txt_bot_player_max_health));

        battleTxtsSlash = new ArrayList<>();
        battleTxtsSlash.add((TextView) findViewById(R.id.battle_txt_top_player_slash));
        battleTxtsSlash.add((TextView) findViewById(R.id.battle_txt_bot_player_slash));

        battleCloTopHealth = findViewById(R.id.battle_clo_top_health);
        battleCloTop = findViewById(R.id.battle_clo_top);
        battleCloBotHealth = findViewById(R.id.battle_clo_bot_health);
        battleCloBot = findViewById(R.id.battle_clo_bot);

        itemImgs = new ArrayList<>();
        itemImgs.add((ImageView)findViewById(R.id.player_items_img_first));
        itemImgs.add((ImageView)findViewById(R.id.player_items_img_second));
        itemImgs.add((ImageView)findViewById(R.id.player_items_img_third));

        //TODO Use these to initialize item names
        itemTxtsName = new ArrayList<>();
        itemTxtsName.add((TextView) findViewById(R.id.player_items_txt_first));
        itemTxtsName.add((TextView) findViewById(R.id.player_items_txt_second));
        itemTxtsName.add((TextView) findViewById(R.id.player_items_txt_third));

        //TODO Use these to initialize party's images.
        partyImgs = new ArrayList<>();
        partyImgs.add((ImageView)findViewById(R.id.party_img_first));
        partyImgs.add((ImageView)findViewById(R.id.party_img_second));
        partyImgs.add((ImageView)findViewById(R.id.party_img_third));

        //TODO Use these to initialize party current health.
        partyTxtsCurrentHealth = new ArrayList<>();
        partyTxtsCurrentHealth.add((TextView) findViewById(R.id.party_txt_first_current_health));
        partyTxtsCurrentHealth.add((TextView) findViewById(R.id.party_txt_second_current_health));
        partyTxtsCurrentHealth.add((TextView) findViewById(R.id.party_txt_third_current_health));

        //TODO Use these to initialize party max health
        partyTxtsMaxHealth = new ArrayList<>();
        partyTxtsMaxHealth.add((TextView) findViewById(R.id.party_txt_first_max_health));
        partyTxtsMaxHealth.add((TextView) findViewById(R.id.party_txt_second_max_health));
        partyTxtsMaxHealth.add((TextView) findViewById(R.id.party_txt_third_max_health));

        //TODO Use these to initialize party types
        partyTxtsType = new ArrayList<>();
        partyTxtsType.add((TextView) findViewById(R.id.party_txt_first_type));
        partyTxtsType.add((TextView) findViewById(R.id.party_txt_second_type));
        partyTxtsType.add((TextView) findViewById(R.id.party_txt_third_type));

        //TODO Use these to initialize party names
        partyTxtsName = new ArrayList<>();
        partyTxtsName.add((TextView) findViewById(R.id.party_txt_first_name));
        partyTxtsName.add((TextView) findViewById(R.id.party_txt_second_name));
        partyTxtsName.add((TextView) findViewById(R.id.party_txt_third_name));
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

        moveBtns = new ArrayList<>();
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
            showRunAlertDialog();
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
        float density = getResources().getDisplayMetrics().density;
        int imageWidth;
        int imageHeight;

        for(ImageView img:  battleImgs)
        {
            imageWidth = (int) (getResources().getDimension(R.dimen.battle_img_width_landscape) / density);
            imageHeight = (int) (getResources().getDimension(R.dimen.battle_img_height_landscape) / density);

            img.getLayoutParams().width = imageWidth;
            img.getLayoutParams().height = imageHeight;
        }

        for(ImageView img: itemImgs)
        {
            imageWidth = (int) (getResources().getDimension(R.dimen.player_items_img_width_landscape) / density);
            imageHeight = (int) (getResources().getDimension(R.dimen.player_items_img_height_landscape) / density);

            img.getLayoutParams().width = imageWidth;
            img.getLayoutParams().height = imageHeight;
        }

        for(ImageView img: partyImgs)
        {
            imageWidth = (int) (getResources().getDimension(R.dimen.party_img_width_landscape) / density);
            imageHeight = (int) (getResources().getDimension(R.dimen.party_img_height_landscape) / density);

            img.getLayoutParams().width = imageWidth;
            img.getLayoutParams().height = imageHeight;
        }

        float textSize = getResources().getDimension(R.dimen.battle_txt_text_size_landscape) / density;

        for (TextView txt : battleTxtsHealthLabel)
        {
            txt.setTextSize(textSize);
        }

        for (TextView txt : battleTxtsCurrentHealth)
        {
            txt.setTextSize(textSize);
        }

        for (TextView txt : battleTxtsSlash)
        {
            txt.setTextSize(textSize);
        }

        for (TextView txt : battleTxtsMaxHealth)
        {
            txt.setTextSize(textSize);
        }

        for (TextView txt : battleTxtsMaxHealth)
        {
            txt.setTextSize(textSize);
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
        float density = getResources().getDisplayMetrics().density;
        int imageWidth = (int) (getResources().getDimension(R.dimen.battle_img_width) / density);
        int imageHeight = (int) (getResources().getDimension(R.dimen.battle_img_height) / density);

        for(ImageView img:  battleImgs)
        {
            img.getLayoutParams().width = imageWidth;
            img.getLayoutParams().height = imageHeight;
        }

        for(ImageView img: itemImgs)
        {
            img.getLayoutParams().width = imageWidth;
            img.getLayoutParams().height = imageHeight;
        }

        for(ImageView img: partyImgs)
        {
            img.getLayoutParams().width = imageWidth;
            img.getLayoutParams().height = imageHeight;
        }

        float textSize = getResources().getDimension(R.dimen.battle_txt_text_size) / density;

        for (TextView txt : battleTxtsHealthLabel)
        {
            txt.setTextSize(textSize);
        }

        for (TextView txt : battleTxtsCurrentHealth)
        {
            txt.setTextSize(textSize);
        }

        for (TextView txt : battleTxtsSlash)
        {
            txt.setTextSize(textSize);
        }

        for (TextView txt : battleTxtsMaxHealth)
        {
            txt.setTextSize(textSize);
        }

        for (TextView txt : battleTxtsMaxHealth)
        {
            txt.setTextSize(textSize);
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

    private void goToResultActivity(boolean isWinner)
    {
        Intent intent = new Intent(this, BattleResultActivity.class);
        intent.putExtra(BattleResultActivity.WINNER_KEY, isWinner);

        startActivity(intent);
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
                    currentPlayerMonster = playerMonsters.get(0);
                    break;
                case R.id.party_clo_second:
                    currentPlayerMonster = playerMonsters.get(1);
                    break;
                case R.id.party_clo_third:
                    currentPlayerMonster = playerMonsters.get(2);
                    break;
            }

            currentOpponentMonster = opponentMonsters.get(0);

            battleTxtsCurrentHealth.get(0).setText(currentOpponentMonster.getCurrentHp() + "");
            battleTxtsCurrentHealth.get(1).setText(currentPlayerMonster.getCurrentHp() + "");

            battleTxtsMaxHealth.get(0).setText(currentOpponentMonster.getMaxHp() + "");
            battleTxtsMaxHealth.get(1).setText(currentPlayerMonster.getMaxHp() + "");

            moveBtns.get(0).setText(currentPlayerMonster.getMoves().get(0).getName());
            moveBtns.get(1).setText(currentPlayerMonster.getMoves().get(1).getName());
            moveBtns.get(2).setText(currentPlayerMonster.getMoves().get(3).getName());
            moveBtns.get(3).setText(currentPlayerMonster.getMoves().get(2).getName());

            battleImgs.get(0).setImageResource(currentOpponentMonster.getImage());
            battleImgs.get(1).setImageResource(currentPlayerMonster.getImage());

            monsterSelected = true;
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
                case R.id.moves_btn_top_left:
                    currentPlayerMonster.doMove(currentOpponentMonster, currentPlayerMonster.getMoves().get(0));
                    break;
                case R.id.moves_btn_top_right:
                    currentPlayerMonster.doMove(currentOpponentMonster, currentPlayerMonster.getMoves().get(1));
                    break;
                case R.id.moves_btn_bot_left:
                    currentPlayerMonster.doMove(currentOpponentMonster, currentPlayerMonster.getMoves().get(3));
                    break;
                case R.id.moves_btn_bot_right:
                    currentPlayerMonster.doMove(currentOpponentMonster, currentPlayerMonster.getMoves().get(2));
                    break;
            }

            if (currentOpponentMonster.isDead()) {
                goToResultActivity(true);
            }

            if (currentPlayerMonster.isDead()) {
                goToResultActivity(false);
            }

            battleTxtsCurrentHealth.get(0).setText(currentOpponentMonster.getCurrentHp() + "");
        }
    }

    private void showRunAlertDialog()
    {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setMessage("Are you sure you want to leave? This counts as a surrender.");
        alertBuilder.setCancelable(true);

        alertBuilder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        goToResultActivity(false);
                    }
                }
        );

        alertBuilder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                }
        );

        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    private class BattleBtnOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.battle_btn_top_left://Battle button
                    if (monsterSelected) {
                        battleVfp.setDisplayedChild(1);
                    } else {
                        Toast.makeText(BattleActivity.this, "You need to select a monster from the party menu first.", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.battle_btn_top_right: //Items
                    battleVfp.setDisplayedChild(2);
                    break;
                case R.id.battle_btn_bot_left: //Party
                    battleVfp.setDisplayedChild(3);
                    break;
                case R.id.battle_btn_bot_right: //Run
                    showRunAlertDialog();
                    break;
            }
        }
    }
}
