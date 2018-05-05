package com.team2.packpackmonsters;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.team2.packpackmonsters.Settings.packDexMonsters;

public class PackAdapter extends ArrayAdapter<Monster> {
    private final int PADDING;
    private final int IMG_SIZE;
    private final int MARGIN;
    private final float TEXT_SIZE;
    private Context context;

    public PackAdapter(Context context) {
        super(context, 0, Settings.packDexMonsters);

        this.context = context;

        float density = context.getResources().getDisplayMetrics().density;

        PADDING = (int) (context.getResources().getDimension(R.dimen.pack_padding) / density);
        IMG_SIZE = (int) (context.getResources().getDimension(R.dimen.pack_img_size) / density);
        MARGIN = (int) (context.getResources().getDimension(R.dimen.pack_margin) / density);
        TEXT_SIZE = context.getResources().getDimension(R.dimen.pack_text_size) / context.getResources().getDisplayMetrics().scaledDensity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout llo = new LinearLayout(context);
        llo.setOrientation(LinearLayout.HORIZONTAL);
        llo.setPadding(PADDING, PADDING, PADDING, PADDING);
        llo.setGravity(Gravity.CENTER);
        llo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        int positionModResult = position % 4;

        if (positionModResult == 0) {
            ImageView imgType = new ImageView(context);
            TextView txtType = new TextView(context);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(IMG_SIZE, IMG_SIZE);
            layoutParams.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);

            imgType.setLayoutParams(layoutParams);
            imgType.setScaleType(ImageView.ScaleType.FIT_CENTER);

            layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);

            txtType.setLayoutParams(layoutParams);
            txtType.setTextSize(TEXT_SIZE);
            txtType.setTextColor(Color.BLACK);
            txtType.setGravity(Gravity.CENTER);

            int packImg, packType;

            switch (position / 4) {
                case 0: //Fire
                    packImg = R.drawable.fire_icon;
                    packType = R.string.fire_types_underlined;
                    break;
                case 1: //Water
                    packImg = R.drawable.water_icon;
                    packType = R.string.water_types_underlined;
                    break;
                case 2: //Air
                    packImg = R.drawable.air_icon;
                    packType = R.string.air_types_underlined;
                    break;
                default: //Earth
                    packImg = R.drawable.earth_icon;
                    packType = R.string.earth_types_underlined;
            }

            imgType.setImageResource(packImg);
            txtType.setText(packType);

            llo.addView(imgType);
            llo.addView(txtType);
        } else {
            TextView txtBullet = new TextView(context);
            txtBullet.setTextSize(TEXT_SIZE);
            txtBullet.setText("\u2022 ");
            txtBullet.setTextColor(Color.BLACK);
            txtBullet.setGravity(Gravity.CENTER);

            TextView txtPack = new TextView(context);
            txtPack.setTextSize(TEXT_SIZE);
            //txtPack.setText(context.getResources().getString(R.string.pack));
            txtPack.setTextColor(Color.BLACK);
            txtPack.setGravity(Gravity.CENTER);

            txtPack.setText(packDexMonsters.get(position).getName());

            llo.addView(txtBullet);
            llo.addView(txtPack);
        }
        return llo;
    }
}

