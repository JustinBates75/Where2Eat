package com.example.where2eat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ResultsActivity extends AppCompatActivity {
    private SharedPreferences sharedPref;
    private Boolean themeType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        themeType = sharedPref.getBoolean("switchTheme", false);
        setContentView(R.layout.activity_results);
        List<Restaurant> results = ((Where2EatApplication) getApplication()).getChoices();
        LinearLayout ll = findViewById(R.id.resultLL);

        Snackbar.make(findViewById(R.id.resultsConstraint), results.size() + " matches found.", Snackbar.LENGTH_LONG).show();
        for (Restaurant curRes : results) {
            ImageView iView = new ImageView(this);
            iView.setAdjustViewBounds(true);
            iView.setImageResource(getResources().getIdentifier("ic_res" + curRes.Id, "drawable", getPackageName()));
            ll.addView(iView);
            //Restaurant Info View (custom control)
            RestaurantInfoView resIView = new RestaurantInfoView(this);
            resIView.setValues(curRes.Name, curRes.Type, curRes.PriceRange, curRes.MIN, curRes.MAX);
            ll.addView(resIView);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.consensus_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean ret = true;
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(getApplicationContext(), HomeScreen.class));
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