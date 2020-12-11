package com.example.where2eat;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class RestaurantInfoView extends LinearLayout {
    public RestaurantInfoView(Context context) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);

        LayoutInflater.from(context).inflate(R.layout.restaurantinfo_view, this, true);
    }

    public void setValues(String title, String type, String price, int min, int max, boolean darkTheme) {
        ((TextView) findViewById(R.id.resTitle)).setText("Restaurant name: " + title);
        ((TextView) findViewById(R.id.resType)).setText("Type of Restaurant: " + type);
        ((TextView) findViewById(R.id.resPrice)).setText("Price Range: " + price + " " + min + " - " + max);
        if(darkTheme)
        {
            setBackgroundColor(Color.BLACK);
            ((TextView) findViewById(R.id.resTitle)).setTextColor(Color.WHITE);
            ((TextView) findViewById(R.id.resType)).setTextColor(Color.WHITE);
            ((TextView) findViewById(R.id.resPrice)).setTextColor(Color.WHITE);
        }
    }
}
