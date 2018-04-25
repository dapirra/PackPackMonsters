package com.team2.packpackmonsters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
    private final int TEXT_SIZE;

    public PackAdapter(Context context)
    {
        this.context = context;

        PADDING = context.getResources().getDimensionPixelSize(R.dimen.pack_padding);
        IMG_SIZE = context.getResources().getDimensionPixelSize(R.dimen.pack_img_size);
        MARGIN = context.getResources().getDimensionPixelSize(R.dimen.pack_margin);
        TEXT_SIZE = context.getResources().getDimensionPixelSize(R.dimen.pack_text_size);
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
        llo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        if(position % 4 == 0)
        {
            ImageView imgType = new ImageView(context);
            TextView txtType = new TextView(context);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(IMG_SIZE, IMG_SIZE);
            layoutParams.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);

            imgType.setLayoutParams(layoutParams);
            //imgType.setBackgroundColor(Color.BLACK);

            layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);

            txtType.setLayoutParams(layoutParams);
            txtType.setTextSize(TEXT_SIZE);
            //txtType.setText(context.getResources().getString(R.string.type));

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
            txtPack.setText(context.getResources().getString(R.string.pack)); //TODO Temporary used for testing individual pack pack screen
            txtPack.setTextColor(Color.BLACK);
            txtPack.setGravity(Gravity.CENTER);

            llo.addView(txtBullet);
            llo.addView(txtPack);
        }


        return llo;
    }
}

