package com.example.where2eat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

public class RestaurantListActivity extends AppCompatActivity {
    List<Restaurant> restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        restaurants = ((Where2EatApplication) getApplication()).getRestaurantList();
        ScrollView sv = new ScrollView(this);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        //Page Title TextView
        TextView textTitle = new TextView(this);
        textTitle.setText(R.string.RestaurantListTitle);
        textTitle.setTextSize(40);
        textTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        linearLayout.addView(textTitle);

        for (Restaurant curRes : restaurants) {
            ImageView iView = new ImageView(this);
            iView.setAdjustViewBounds(true);
            iView.setImageResource(getResources().getIdentifier("ic_res" + curRes.Id, "drawable", getPackageName()));
            linearLayout.addView(iView);
            //Restaurant Info View (custom control)
            RestaurantInfoView resIView = new RestaurantInfoView(this);
            resIView.setValues(curRes.Name, curRes.Type, curRes.PriceRange, curRes.MIN, curRes.MAX);
            linearLayout.addView(resIView);
        }
        sv.addView(linearLayout);
        setContentView(sv);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean ret = true;
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_RestaurantList:
                startActivity(new Intent(getApplicationContext(), RestaurantListActivity.class));
                break;
            case R.id.menu_settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                break;
            default:
                ret = super.onOptionsItemSelected(item);
                break;
        }
        return ret;
    }
}
