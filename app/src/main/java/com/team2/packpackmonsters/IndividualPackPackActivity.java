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

        String monster = getIntent().getStringExtra(MONSTER_KEY);

        TextView txtName = findViewById(R.id.ind_pack_txt_name);
        txtName.setText(monster);

        //TODO Fill in info based on monster passed to this activity.

        ImageView img = findViewById(R.id.ind_pack_img);
        //img.setImageDrawable();

        TextView txtType = findViewById(R.id.ind_pack_txt_type);
        //txtType.setText();

        TextView txtHealth = findViewById(R.id.ind_pack_txt_health);
        //txtHealth.setText();

        TextView txtMove1 = findViewById(R.id.ind_pack_txt_move_1);
        //txtMove1.setText();

        TextView txtMove1Type = findViewById(R.id.ind_pack_txt_move_1_type);
        //txtMove1Type.setText();

        TextView txtMove2 = findViewById(R.id.ind_pack_txt_move_2);
        //txtMove2.setText();

        TextView txtMove2Type = findViewById(R.id.ind_pack_txt_move_2_type);
        //txtMove2Type.setText();

        TextView txtMove3 = findViewById(R.id.ind_pack_txt_move_3);
        //txtMove3.setText();

        TextView txtMove3Type = findViewById(R.id.ind_pack_txt_move_3_type);
        //txtMove3Type.setText();

        TextView txtMove4 = findViewById(R.id.ind_pack_txt_move_4);
        //txtMove4.setText();

        TextView txtMove4Type = findViewById(R.id.ind_pack_txt_move_4_type);
        //txtMove4Type.setText();
    }
}
