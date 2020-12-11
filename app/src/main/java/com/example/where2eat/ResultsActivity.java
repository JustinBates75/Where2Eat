package com.example.where2eat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ResultsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_results);
        List<Restaurant> results = ((Where2EatApplication)getApplication()).getChoices();
        LinearLayout ll = findViewById(R.id.resultLL);

        Snackbar.make(findViewById(R.id.resultsConstraint), results.size() + " matches found.", Snackbar.LENGTH_LONG).show();
        for (Restaurant curRes: results) {
            ImageView iView = new ImageView(this);
            iView.setAdjustViewBounds(true);
            iView.setImageResource(getResources().getIdentifier("ic_res" + curRes.Id, "drawable", getPackageName()));
            ll.addView(iView);
            TextView textViewRName = new TextView(this);
            textViewRName.setText(curRes.Name);
            ll.addView(textViewRName);
            //Type of Restaurant
            TextView textViewRType= new TextView(this);
            textViewRType.setText("Type of Restaurant:" + curRes.Type);
            ll.addView(textViewRType);
            //Restaurant Price Range
            TextView textViewRPrice = new TextView(this);
            textViewRPrice.setText("Price Range:" + curRes.PriceRange + curRes.MIN + " - " + curRes.MAX);
            ll.addView(textViewRPrice);
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
        switch (item.getItemId()){
            case R.id.menu_reset:
                startActivity(new Intent(getApplicationContext(), RestaurantListActivity.class));
                //reset action
                break;
            case R.id.menu_settings:
                startActivity(new Intent(getApplicationContext(), RestaurantListActivity.class));
                //Show settings
                break;
            default:
                ret = super.onOptionsItemSelected(item);
                break;

        }
        return ret;
    }
}