package com.team2.packpackmonsters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class IndividualPackPackActivity extends AppCompatActivity {
    public static final String MONSTER_KEY = "Monster";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_pack_pack);

        int position = getIntent().getIntExtra(MONSTER_KEY, -1);
        Monster currentMonster = Settings.packDexMonsters.get(position);

        TextView txtName = findViewById(R.id.ind_pack_txt_name);
        txtName.setText(currentMonster.getName());

        ImageView img = findViewById(R.id.ind_pack_img);
        img.setImageResource(currentMonster.getImage());

        TextView txtType = findViewById(R.id.ind_pack_txt_type);
        txtType.setText(currentMonster.getTypeString());

        TextView txtHealth = findViewById(R.id.ind_pack_txt_health);
        txtHealth.setText(Integer.toString(currentMonster.getMaxHp()));

        TextView txtMove1 = findViewById(R.id.ind_pack_txt_move_1);
        txtMove1.setText(currentMonster.getMoves().get(0).getName());

        TextView txtMove1Type = findViewById(R.id.ind_pack_txt_move_1_type);
        txtMove1Type.setText("Elemental");

        TextView txtMove2 = findViewById(R.id.ind_pack_txt_move_2);
        txtMove2.setText(currentMonster.getMoves().get(1).getName());

        TextView txtMove2Type = findViewById(R.id.ind_pack_txt_move_2_type);
        txtMove2Type.setText("Ultimate");

        TextView txtMove3 = findViewById(R.id.ind_pack_txt_move_3);
        txtMove3.setText(currentMonster.getMoves().get(2).getName());

        TextView txtMove3Type = findViewById(R.id.ind_pack_txt_move_3_type);
        txtMove3Type.setText("Special");

        TextView txtMove4 = findViewById(R.id.ind_pack_txt_move_4);
        txtMove4.setText(currentMonster.getMoves().get(3).getName());

        TextView txtMove4Type = findViewById(R.id.ind_pack_txt_move_4_type);
        txtMove4Type.setText("Basic");
    }
}
