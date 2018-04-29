package com.team2.packpackmonsters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PackAdapter extends BaseAdapter
{
    private Context context;
    private final int PADDING;
    private final int IMG_SIZE;
    private final int MARGIN;
    private final float TEXT_SIZE;

    public PackAdapter(Context context)
    {
        this.context = context;

        float density = context.getResources().getDisplayMetrics().density;

        PADDING = (int) (context.getResources().getDimension(R.dimen.pack_padding) / density);
        IMG_SIZE = (int) (context.getResources().getDimensionPixelSize(R.dimen.pack_img_size) / density);
        MARGIN = (int) (context.getResources().getDimension(R.dimen.pack_margin) / density);
        TEXT_SIZE = context.getResources().getDimension(R.dimen.pack_text_size) / density;
    }

    @Override
    public int getCount()
    {
        return 16; //12 monsters + 4 type
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout llo = new LinearLayout(context);
        llo.setOrientation(LinearLayout.HORIZONTAL);
        llo.setPadding(PADDING, PADDING, PADDING, PADDING);
        llo.setGravity(Gravity.CENTER_VERTICAL);
        llo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        int positionModResult = position % 4;

        if(positionModResult == 0)
        {
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

            Drawable packImg;
            String packType;

            switch(position / 4)
            {
                case 0: //Fire
                    packImg = context.getResources().getDrawable(R.drawable.fire_icon);
                    packType = context.getResources().getString(R.string.fire_types_underlined);
                    break;
                case 1: //Earth
                    packImg = context.getResources().getDrawable(R.drawable.earth_icon);
                    packType = context.getResources().getString(R.string.earth_types_underlined);
                    break;
                case 2: //Air
                    packImg = context.getResources().getDrawable(R.drawable.air_icon);
                    packType = context.getResources().getString(R.string.air_types_underlined);
                    break;
                default: //Water
                    packImg = context.getResources().getDrawable(R.drawable.water_icon);
                    packType = context.getResources().getString(R.string.water_types_underlined);
            }

            imgType.setImageDrawable(packImg);
            txtType.setText(packType);

            llo.addView(imgType);
            llo.addView(txtType);
        }
        else
        {
            llo.setGravity(Gravity.CENTER);

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

            //TODO Set text to each of the monster names.

            String packName;

            switch(positionModResult)
            {
                case 1:
                    switch(position / 4)
                    {
                        case 0: //Fire 1
                            packName = "Fire 1";
                            break;
                        case 1: //Earth 1
                            packName = "Earth 1";
                            break;
                        case 2: //Air 1
                            packName = "Air 1";
                            break;
                        default: //Water 1
                            packName = "Water 1";
                    }
                    break;
                case 2:
                    switch (position / 4)
                    {
                        case 0: //Fire 2
                            packName = "Fire 2";
                            break;
                        case 1: //Earth 2
                            packName = "Earth 2";
                            break;
                        case 2: //Air 2
                            packName = "Air 2";
                            break;
                        default: //Water 2
                            packName = "Water 2";
                    }
                    break;
                default:
                    switch (position / 4)
                    {
                        case 0: //Fire 3
                            packName = "Fire 3";
                            break;
                        case 1: //Earth 3
                            packName = "Earth 3";
                            break;
                        case 2: //Air 3
                            packName = "Air 3";
                            break;
                        default: //Water 3
                            packName = "Water 3";
                    }
            }

            txtPack.setText(packName);

            llo.addView(txtBullet);
            llo.addView(txtPack);
        }


        return llo;
    }
}

