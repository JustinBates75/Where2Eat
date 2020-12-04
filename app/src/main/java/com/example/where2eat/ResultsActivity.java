package com.example.where2eat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.List;

public class ResultsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        List<Restaurant> results = ((Where2EatApplication)getApplication()).getChoices();
        ScrollView sV = new ScrollView(this);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        for (Restaurant curRes: results) {
            ImageView iView = new ImageView(this);
            iView.setBackgroundResource(getResources().getIdentifier("ic_res" + curRes.Id, "drawable", getPackageName()));
            ll.addView(iView);
        }
        ll.setGravity(Gravity.CENTER);
        sV.addView(ll);
        setContentView(sV);
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
                //reset action
                break;
            case R.id.menu_settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                //Show settings
                break;
            default:
                ret = super.onOptionsItemSelected(item);
                break;

        }
        return ret;
    }
}