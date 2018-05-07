package com.team2.packpackmonsters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class PackPackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack_pack);
        Settings.loadData(this);

        ListView packLst = findViewById(R.id.pack_lst);
        packLst.setOnItemClickListener(new PackLstOnItemClickListener());
        packLst.setAdapter(new PackAdapter(this));
    }

    private class PackLstOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position % 4 != 0) //Pack Pack monster was clicked.
            {
                LinearLayout llo = (LinearLayout) view;
                TextView txtPack = (TextView) llo.getChildAt(1);

                Intent intent = new Intent(view.getContext(), IndividualPackPackActivity.class);
                intent.putExtra(IndividualPackPackActivity.MONSTER_KEY, position);

                startActivity(intent);
            }
        }
    }
}
