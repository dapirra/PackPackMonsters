package com.team2.packpackmonsters;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
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
    private ArrayList<Button> moveBtns;
    private Monster currentPlayerMonster;
    private int currentPlayerMonsterIndex;
    private Monster currentOpponentMonster;
    private boolean isEnemyTurn;
    private boolean isInitialPartySelection = true;
    private View lastPartyCloSelected = null;
    private boolean playerIsSelectingAnotherMonster;
    private boolean itemSelected;
    private boolean reviveSelected;
    private MediaPlayer musicPlayer;
    private TextView yourMoveTxt;
    private TextView enemyMoveTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        Settings.loadData(this);
        Settings.STATISTICS.surrenders++;
        Settings.saveData();

        musicPlayer = MediaPlayer.create(this, R.raw.battle);
        musicPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                musicPlayer.start();
            }
        });
        musicPlayer.start();

        initializeViews();

        //Keep everything except party view off the screen until a selection is made.
        battleCloTopHealth.setVisibility(View.INVISIBLE);
        battleCloBotHealth.setVisibility(View.INVISIBLE);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
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

        for (int i = 0; i < playerMonsters.size(); i++) {
            partyTxtsName.get(i).setText(playerMonsters.get(i).getName());
            partyImgs.get(i).setImageResource(playerMonsters.get(i).getImage());
            partyTxtsCurrentHealth.get(i).setText(playerMonsters.get(i).getCurrentHp() + "");
            partyTxtsMaxHealth.get(i).setText(playerMonsters.get(i).getMaxHp() + "");
            partyTxtsType.get(i).setText(playerMonsters.get(i).getTypeString());
        }

        Button btnParty = findViewById(R.id.battle_btn_bot_left);
        btnParty.callOnClick();

        Toast.makeText(this, "Select a party member.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        musicPlayer.stop();
    }

    private void initializeViews() {

        battleVfp = findViewById(R.id.battle_vfp);

        battleImgs = new ArrayList<>();
        battleImgs.add((ImageView) findViewById(R.id.battle_img_top_player));
        battleImgs.add((ImageView) findViewById(R.id.battle_img_bot_player));

        battleTxtsHealthLabel = new ArrayList<>();
        battleTxtsHealthLabel.add((TextView) findViewById(R.id.battle_txt_top_player_health_label));
        battleTxtsHealthLabel.add((TextView) findViewById(R.id.battle_txt_bot_player_health_label));

        battleTxtsCurrentHealth = new ArrayList<>();
        battleTxtsCurrentHealth.add((TextView) findViewById(R.id.battle_txt_top_player_current_health));
        battleTxtsCurrentHealth.add((TextView) findViewById(R.id.battle_txt_bot_player_current_health));

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
        itemImgs.add((ImageView) findViewById(R.id.player_items_img_first));
        itemImgs.add((ImageView) findViewById(R.id.player_items_img_second));
        itemImgs.add((ImageView) findViewById(R.id.player_items_img_third));

        itemTxtsName = new ArrayList<>();
        itemTxtsName.add((TextView) findViewById(R.id.player_items_txt_first));
        itemTxtsName.add((TextView) findViewById(R.id.player_items_txt_second));
        itemTxtsName.add((TextView) findViewById(R.id.player_items_txt_third));

        partyImgs = new ArrayList<>();
        partyImgs.add((ImageView) findViewById(R.id.party_img_first));
        partyImgs.add((ImageView) findViewById(R.id.party_img_second));
        partyImgs.add((ImageView) findViewById(R.id.party_img_third));

        partyTxtsCurrentHealth = new ArrayList<>();
        partyTxtsCurrentHealth.add((TextView) findViewById(R.id.party_txt_first_current_health));
        partyTxtsCurrentHealth.add((TextView) findViewById(R.id.party_txt_second_current_health));
        partyTxtsCurrentHealth.add((TextView) findViewById(R.id.party_txt_third_current_health));

        partyTxtsMaxHealth = new ArrayList<>();
        partyTxtsMaxHealth.add((TextView) findViewById(R.id.party_txt_first_max_health));
        partyTxtsMaxHealth.add((TextView) findViewById(R.id.party_txt_second_max_health));
        partyTxtsMaxHealth.add((TextView) findViewById(R.id.party_txt_third_max_health));

        partyTxtsType = new ArrayList<>();
        partyTxtsType.add((TextView) findViewById(R.id.party_txt_first_type));
        partyTxtsType.add((TextView) findViewById(R.id.party_txt_second_type));
        partyTxtsType.add((TextView) findViewById(R.id.party_txt_third_type));

        partyTxtsName = new ArrayList<>();
        partyTxtsName.add((TextView) findViewById(R.id.party_txt_first_name));
        partyTxtsName.add((TextView) findViewById(R.id.party_txt_second_name));
        partyTxtsName.add((TextView) findViewById(R.id.party_txt_third_name));

        yourMoveTxt = findViewById(R.id.your_move_text);
        enemyMoveTxt = findViewById(R.id.enemy_move_text);
    }

    private void initializeListeners() {
        ArrayList<Button> battleBtns = new ArrayList<>();
        battleBtns.add((Button) findViewById(R.id.battle_btn_top_left));
        battleBtns.add((Button) findViewById(R.id.battle_btn_top_right));
        battleBtns.add((Button) findViewById(R.id.battle_btn_bot_left));
        battleBtns.add((Button) findViewById(R.id.battle_btn_bot_right));

        for (Button btn : battleBtns) {
            btn.setOnClickListener(new BattleBtnOnClickListener());
        }

        ArrayList<ConstraintLayout> partyClos = new ArrayList<>();
        partyClos.add((ConstraintLayout) findViewById(R.id.party_clo_first));
        partyClos.add((ConstraintLayout) findViewById(R.id.party_clo_second));
        partyClos.add((ConstraintLayout) findViewById(R.id.party_clo_third));

        for (ConstraintLayout clo : partyClos) {
            clo.setOnClickListener(new PartyCloOnClickListener());
        }

        ArrayList<ConstraintLayout> playerItemsClos = new ArrayList<>();
        playerItemsClos.add((ConstraintLayout) findViewById(R.id.player_items_clo_first));
        playerItemsClos.add((ConstraintLayout) findViewById(R.id.player_items_clo_second));
        playerItemsClos.add((ConstraintLayout) findViewById(R.id.player_items_clo_third));

        for (ConstraintLayout clo : playerItemsClos) {
            clo.setOnClickListener(new PlayerItemsCloOnClickListener());
        }

        moveBtns = new ArrayList<>();
        moveBtns.add((Button) findViewById(R.id.moves_btn_top_left));
        moveBtns.add((Button) findViewById(R.id.moves_btn_top_right));
        moveBtns.add((Button) findViewById(R.id.moves_btn_bot_left));
        moveBtns.add((Button) findViewById(R.id.moves_btn_bot_right));

        for (Button btn : moveBtns) {
            btn.setOnClickListener(new MoveBtnOnClickListener());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    public void onBackPressed() {
        if (battleVfp.getDisplayedChild() == 0 || playerIsSelectingAnotherMonster || isInitialPartySelection) {
            showRunAlertDialog();
        } else {
            battleVfp.setDisplayedChild(0);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            adjustLayoutToLandscape();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            adjustLayoutToPortrait();
        }
    }

    private void adjustLayoutToLandscape() {
        int imageWidth;
        int imageHeight;

        for (ImageView img : battleImgs) {
            imageWidth = (int) (getResources().getDimension(R.dimen.battle_img_width_landscape));
            imageHeight = (int) (getResources().getDimension(R.dimen.battle_img_height_landscape));

            img.getLayoutParams().width = imageWidth;
            img.getLayoutParams().height = imageHeight;
        }

        for (ImageView img : itemImgs) {
            imageWidth = (int) (getResources().getDimension(R.dimen.player_items_img_width_landscape));
            imageHeight = (int) (getResources().getDimension(R.dimen.player_items_img_height_landscape));

            img.getLayoutParams().width = imageWidth;
            img.getLayoutParams().height = imageHeight;
        }

        for (ImageView img : partyImgs) {
            imageWidth = (int) (getResources().getDimension(R.dimen.party_img_width_landscape));
            imageHeight = (int) (getResources().getDimension(R.dimen.party_img_height_landscape));

            img.getLayoutParams().width = imageWidth;
            img.getLayoutParams().height = imageHeight;
        }

        float textSize = getResources().getDimension(R.dimen.battle_txt_text_size_landscape) / getResources().getDisplayMetrics().scaledDensity;

        for (TextView txt : battleTxtsHealthLabel) {
            txt.setTextSize(textSize);
        }

        for (TextView txt : battleTxtsCurrentHealth) {
            txt.setTextSize(textSize);
        }

        for (TextView txt : battleTxtsSlash) {
            txt.setTextSize(textSize);
        }

        for (TextView txt : battleTxtsMaxHealth) {
            txt.setTextSize(textSize);
        }

        for (TextView txt : battleTxtsMaxHealth) {
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

    private void adjustLayoutToPortrait() {
        int imageWidth;
        int imageHeight;

        for (ImageView img : battleImgs) {
            imageWidth = (int) (getResources().getDimension(R.dimen.battle_img_width));
            imageHeight = (int) (getResources().getDimension(R.dimen.battle_img_height));

            img.getLayoutParams().width = imageWidth;
            img.getLayoutParams().height = imageHeight;
        }

        for (ImageView img : itemImgs) {
            imageWidth = (int) (getResources().getDimension(R.dimen.player_items_img_width));
            imageHeight = (int) (getResources().getDimension(R.dimen.player_items_img_height));

            img.getLayoutParams().width = imageWidth;
            img.getLayoutParams().height = imageHeight;
        }

        for (ImageView img : partyImgs) {
            imageWidth = (int) (getResources().getDimension(R.dimen.party_img_width));
            imageHeight = (int) (getResources().getDimension(R.dimen.party_img_height));

            img.getLayoutParams().width = imageWidth;
            img.getLayoutParams().height = imageHeight;
        }

        float textSize = getResources().getDimension(R.dimen.battle_txt_text_size) / getResources().getDisplayMetrics().scaledDensity;

        for (TextView txt : battleTxtsHealthLabel) {
            txt.setTextSize(textSize);
        }

        for (TextView txt : battleTxtsCurrentHealth) {
            txt.setTextSize(textSize);
        }

        for (TextView txt : battleTxtsSlash) {
            txt.setTextSize(textSize);
        }

        for (TextView txt : battleTxtsMaxHealth) {
            txt.setTextSize(textSize);
        }

        for (TextView txt : battleTxtsMaxHealth) {
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

    private void goToResultActivity(boolean isWinner) {
        yourMoveTxt.setVisibility(View.INVISIBLE);
        startActivity(new Intent(this, BattleResultActivity.class)
                .putExtra(BattleResultActivity.WINNER_KEY, isWinner));
    }

    private void onSuccessfulPartySelection(View v) {
        switch (v.getId()) {
            case R.id.party_clo_first:
                currentPlayerMonster = playerMonsters.get(0);
                currentPlayerMonsterIndex = 0;
                break;
            case R.id.party_clo_second:
                currentPlayerMonster = playerMonsters.get(1);
                currentPlayerMonsterIndex = 1;
                break;
            case R.id.party_clo_third:
                currentPlayerMonster = playerMonsters.get(2);
                currentPlayerMonsterIndex = 2;
                break;
        }

        if (currentPlayerMonster.isDead()) {
            if (reviveSelected) {
                currentPlayerMonster.setCurrentHp(currentPlayerMonster.getMaxHp());
                currentPlayerMonster.setUltimateUsed(false);
                reviveSelected = false;
            } else {
                Toast.makeText(BattleActivity.this, "Pick another monster", Toast.LENGTH_SHORT).show();
                return;
            }
        } else if (reviveSelected) {
            Toast.makeText(BattleActivity.this, "Pick another monster to revive", Toast.LENGTH_SHORT).show();
            return;
        }

        if (playerIsSelectingAnotherMonster) {
            playerIsSelectingAnotherMonster = false;
        }

        battleCloTopHealth.setVisibility(View.VISIBLE);
        battleCloBotHealth.setVisibility(View.VISIBLE);
        battleVfp.setDisplayedChild(0); //Return to initial screen (fight, party, item, run)

        if (lastPartyCloSelected != null) {
            lastPartyCloSelected.setBackgroundResource(R.drawable.border_default);
        }

        v.setBackgroundResource(R.drawable.border_selected);
        lastPartyCloSelected = v;

        if (isInitialPartySelection) {
            battleImgs.get(0).startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_from_left));
            battleImgs.get(1).startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_from_right));
            currentOpponentMonster = opponentMonsters.get(0);
            if (Math.random() > 0.5) {
                new EnemyMove().execute();
            } else {
                yourMoveTxt.setVisibility(View.VISIBLE);
            }
        } else {
            new EnemyMove().execute();
        }
        isInitialPartySelection = false;

        String healthLabelText = currentPlayerMonster.getName() + " " + v.getContext().getResources().getString(R.string.health);
        battleTxtsHealthLabel.get(1).setText(healthLabelText);

        healthLabelText = v.getContext().getResources().getString(R.string.enemy) + " " + currentOpponentMonster.getName() + " " + v.getContext().getResources().getString(R.string.health);
        battleTxtsHealthLabel.get(0).setText(healthLabelText);

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
    }

    private void onSuccessfulItemSelection(View v) {
        test:
        switch (v.getId()) {
            case R.id.player_items_clo_first: // Revive
                for (Monster m : playerMonsters) {
                    if (m.isDead()) {
                        itemSelected = true;
                        reviveSelected = true;
                        battleVfp.setDisplayedChild(3);
                        break test;
                    }
                }
                Toast.makeText(BattleActivity.this, "You have no monstersList to revive", Toast.LENGTH_SHORT).show();
                break;
            case R.id.player_items_clo_second: // Finish Him
                currentOpponentMonster.setCurrentHp(1);
                onDamageDoneToEnemy();

                battleVfp.setDisplayedChild(1);
                itemSelected = true;
                break;
            case R.id.player_items_clo_third: // Heal and Deal
                currentPlayerMonster.setCurrentHp(currentPlayerMonster.getCurrentHp() + 10);
                currentOpponentMonster.setCurrentHp(currentOpponentMonster.getCurrentHp() - 10);

                battleTxtsCurrentHealth.get(1).setText(currentPlayerMonster.getCurrentHp() + "");
                partyTxtsCurrentHealth.get(currentPlayerMonsterIndex).setText(currentPlayerMonster.getCurrentHp() + "");

                onDamageDoneToEnemy();
                Toast.makeText(BattleActivity.this, "Player monster gained 10hp", Toast.LENGTH_SHORT).show();
                battleVfp.setDisplayedChild(1);
                itemSelected = true;
                break;
        }
    }

    private void createAndShowAlertDialog(String message, String positiveMessage, DialogInterface.OnClickListener positiveListener, String negativeMessage, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setMessage(message);
        alertBuilder.setCancelable(true);
        alertBuilder.setPositiveButton(positiveMessage, positiveListener);
        alertBuilder.setNegativeButton(negativeMessage, negativeListener);
        alertBuilder.show();
    }

    private void showRunAlertDialog() {
        createAndShowAlertDialog("Are you sure you want to leave?\nThis will count as a surrender.",
                getString(android.R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        goToResultActivity(false);
                    }
                },
                getString(android.R.string.cancel),
                new CancelDialogOnClickListener()
        );
    }

    private void showPartySelectionAlertDialog(final View v) {
        createAndShowAlertDialog("Are you sure you want to use this monster?",
                getString(android.R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onSuccessfulPartySelection(v);
                    }
                },
                getString(android.R.string.cancel),
                new CancelDialogOnClickListener()
        );
    }

    private void showItemSelectionAlertDialog(final View v) {
        createAndShowAlertDialog("Are you sure you want to use this item?",
                getString(android.R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onSuccessfulItemSelection(v);
                    }
                },
                getString(android.R.string.cancel),
                new CancelDialogOnClickListener()
        );
    }

    private void onDamageDoneToEnemy() {
        if (currentOpponentMonster.isDead()) {
            if (!opponentMonsters.get(1).isDead()) {
                currentOpponentMonster = opponentMonsters.get(1);
            } else if (!opponentMonsters.get(2).isDead()) {
                currentOpponentMonster = opponentMonsters.get(2);
            } else {
                Settings.STATISTICS.surrenders--;
                Settings.STATISTICS.wins++;
                Settings.saveData();
                goToResultActivity(true);
                return;
            }

            battleTxtsMaxHealth.get(0).setText(currentOpponentMonster.getMaxHp() + "");
            battleTxtsHealthLabel.get(0).setText(getResources().getString(R.string.enemy) + " " + currentOpponentMonster.getName() + " " + getResources().getString(R.string.health));
            battleImgs.get(0).setImageResource(currentOpponentMonster.getImage());
            isEnemyTurn = false;
        } else {
            new EnemyMove().execute();
        }

        battleTxtsCurrentHealth.get(0).setText(currentOpponentMonster.getCurrentHp() + "");
    }

    private class PartyCloOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            showPartySelectionAlertDialog(v);
        }
    }

    private class PlayerItemsCloOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (!isEnemyTurn) {
                showItemSelectionAlertDialog(v);
            }
        }
    }

    private class MoveBtnOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (isEnemyTurn) {
                return;
            }

            switch (v.getId()) {
                case R.id.moves_btn_top_left:
                    currentPlayerMonster.doMove(currentOpponentMonster, currentPlayerMonster.getMoves().get(0));
                    break;
                case R.id.moves_btn_top_right:
                    if (currentPlayerMonster.isUltimateUsed()) {
                        Toast.makeText(BattleActivity.this, "You monster can only use their ultimate once", Toast.LENGTH_SHORT).show();
                        isEnemyTurn = false;
                        return;
                    } else {
                        currentPlayerMonster.doMove(currentOpponentMonster, currentPlayerMonster.getMoves().get(1));
                    }
                    break;
                case R.id.moves_btn_bot_left:
                    currentPlayerMonster.doMove(currentOpponentMonster, currentPlayerMonster.getMoves().get(3));
                    break;
                case R.id.moves_btn_bot_right:
                    currentPlayerMonster.doMove(currentOpponentMonster, currentPlayerMonster.getMoves().get(2));
                    break;
            }

            Toast.makeText(BattleActivity.this, "You did " + Monster.damageDealt + " damage.", Toast.LENGTH_LONG).show();

            onDamageDoneToEnemy();
        }
    }

    private class EnemyMove extends AsyncTask<Void, Void, Move> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            isEnemyTurn = true;
            enemyMoveTxt.setVisibility(View.VISIBLE);
            yourMoveTxt.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Move doInBackground(Void... voids) {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return currentOpponentMonster.getMoves().get((int) (Math.random() * 4));
        }

        protected void onPostExecute(Move enemyMove) {
            currentOpponentMonster.doMove(currentPlayerMonster, enemyMove);

            Toast.makeText(BattleActivity.this, "Enemy used " + enemyMove.getName() +
                    ".\nIt did " + Monster.damageDealt + " damage.", Toast.LENGTH_LONG).show();

            battleTxtsCurrentHealth.get(1).setText(currentPlayerMonster.getCurrentHp() + "");
            partyTxtsCurrentHealth.get(currentPlayerMonsterIndex).setText(currentPlayerMonster.getCurrentHp() + "");

            isEnemyTurn = false;
            enemyMoveTxt.setVisibility(View.INVISIBLE);
            yourMoveTxt.setVisibility(View.VISIBLE);

            if (currentPlayerMonster.isDead()) {
                for (Monster m : playerMonsters) {
                    if (!m.isDead()) {
                        playerIsSelectingAnotherMonster = true;
                        battleVfp.setDisplayedChild(3);
                        return;
                    }
                }
                Settings.STATISTICS.surrenders--;
                Settings.STATISTICS.losses++;
                Settings.saveData();
                goToResultActivity(false);
            }
        }
    }

    private class CancelDialogOnClickListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int id) {
            dialog.cancel();
        }
    }

    private class BattleBtnOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.battle_btn_top_left://Battle button
                    battleVfp.setDisplayedChild(1);
                    break;
                case R.id.battle_btn_top_right: //Items
                    if (!isEnemyTurn) {
                        if (itemSelected) {
                            Toast.makeText(BattleActivity.this, "You already used your item", Toast.LENGTH_SHORT).show();
                        } else {
                            battleVfp.setDisplayedChild(2);
                            Toast.makeText(BattleActivity.this, "You can only use 1 item", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case R.id.battle_btn_bot_left: //Party
                    if (!isEnemyTurn) {
                        battleVfp.setDisplayedChild(3);
                    }
                    break;
                case R.id.battle_btn_bot_right: //Run
                    showRunAlertDialog();
                    break;
            }
        }
    }
}
