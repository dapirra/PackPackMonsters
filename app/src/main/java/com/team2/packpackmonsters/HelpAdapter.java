package com.team2.packpackmonsters;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class HelpAdapter extends BaseAdapter {
    private final int PADDING;
    private final int IMG_SIZE;
    private final int MARGIN;
    private final float TEXT_SIZE;
    private Context context;
    private ArrayList<Integer> fireStringResources;
    private ArrayList<Integer> waterStringResources;
    private ArrayList<Integer> airStringResources;
    private ArrayList<Integer> earthStringResources;

    public HelpAdapter(Context context) {
        this.context = context;

        float density = context.getResources().getDisplayMetrics().density;

        PADDING = (int) (context.getResources().getDimension(R.dimen.help_padding) / density);
        IMG_SIZE = (int) (context.getResources().getDimension(R.dimen.pack_img_size) / density);
        MARGIN = (int) (context.getResources().getDimension(R.dimen.help_margin) / density);
        TEXT_SIZE = context.getResources().getDimension(R.dimen.help_text_size) / context.getResources().getDisplayMetrics().scaledDensity;

        initializeStringResources();
    }

    private void initializeStringResources() {
        fireStringResources = new ArrayList<>();
        fireStringResources.add(R.string.fire_against_fire);
        fireStringResources.add(R.string.fire_against_earth);
        fireStringResources.add(R.string.fire_against_air);
        fireStringResources.add(R.string.fire_against_water);

        earthStringResources = new ArrayList<>();
        earthStringResources.add(R.string.earth_against_fire);
        earthStringResources.add(R.string.earth_against_earth);
        earthStringResources.add(R.string.earth_against_air);
        earthStringResources.add(R.string.earth_against_water);

        airStringResources = new ArrayList<>();
        airStringResources.add(R.string.air_against_fire);
        airStringResources.add(R.string.air_against_earth);
        airStringResources.add(R.string.air_against_air);
        airStringResources.add(R.string.air_against_water);

        waterStringResources = new ArrayList<>();
        waterStringResources.add(R.string.water_against_fire);
        waterStringResources.add(R.string.water_against_earth);
        waterStringResources.add(R.string.water_against_air);
        waterStringResources.add(R.string.water_against_water);
    }

    @Override
    public int getCount() {
        return 20; //16 type effectiveness + 4 type labels
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout llo = new LinearLayout(context);
        llo.setOrientation(LinearLayout.HORIZONTAL);
        llo.setPadding(PADDING, PADDING, PADDING, PADDING);
        llo.setGravity(Gravity.CENTER);
        llo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        if (position % 5 == 0) {
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
                case 1: //Earth
                    packImg = R.drawable.earth_icon;
                    packType = R.string.earth_types_underlined;
                    break;
                case 2: //Air
                    packImg = R.drawable.air_icon;
                    packType = R.string.air_types_underlined;
                    break;
                default: //Water
                    packImg = R.drawable.water_icon;
                    packType = R.string.water_types_underlined;
            }

            imgType.setImageResource(packImg);
            txtType.setText(packType);

            llo.addView(imgType);
            llo.addView(txtType);
        } else {
            TextView txtEffectiveness = new TextView(context);
            txtEffectiveness.setTextSize(TEXT_SIZE);
            txtEffectiveness.setTextColor(Color.BLACK);
            txtEffectiveness.setGravity(Gravity.CENTER);

            int resourceID;
            int typePosition = position % 5 - 1;

            //TODO Optimize for readability.
            if (position >= 1 && position <= 4) {
                resourceID = fireStringResources.get(typePosition);
            } else if (position >= 6 && position <= 9) {
                resourceID = earthStringResources.get(typePosition);
            } else if (position >= 11 && position <= 14) {
                resourceID = airStringResources.get(typePosition);
            } else { //position >= 16 && position <= 20
                resourceID = waterStringResources.get(typePosition);
            }

            txtEffectiveness.setText(resourceID);

            llo.addView(txtEffectiveness);
        }

        return llo;
    }
}
