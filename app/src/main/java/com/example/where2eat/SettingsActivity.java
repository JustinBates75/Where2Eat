package com.example.where2eat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {
    private SharedPreferences sharedPref;
    private Boolean themeType;
    private String player1Name;
    private String player2Name;
    private String p1Old;
    private String p2Old;
    private Boolean swipeOn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        //Fix this line with coresponding pages
        themeType =sharedPref.getBoolean("switchTheme", false);

        swipeOn =sharedPref.getBoolean("swipeOn", false);

        if (!themeType){
            setTheme(R.style.AppTheme);
        }
        else
        {
            setTheme(R.style.DarkTheme);
        }
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            boolean ret=true;
            //If not set here return can be set in the indivisual cases
            switch(item.getItemId()){

                case android.R.id.home:
                    onBackPressed();

                    break;
                default:
                    //This occurs if neither ids are gathered
                    ret=super.onOptionsItemSelected(item);
                    break;
            }

            return ret;
        }

    @Override
    protected void onPause() {
        themeType = sharedPref.getBoolean("switchTheme", false);
        if (!themeType) {
            setTheme(R.style.AppTheme);
        } else {
            setTheme(R.style.DarkTheme);
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        themeType = sharedPref.getBoolean("switchTheme", false);
        if (!themeType) {
            setTheme(R.style.AppTheme);
        } else {
            setTheme(R.style.DarkTheme);
        }
        super.onResume();
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
}