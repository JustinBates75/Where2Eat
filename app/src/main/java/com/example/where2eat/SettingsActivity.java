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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        player1Name=(((Where2EatApplication)getApplication()).getPlayer1Name());
        player2Name=(((Where2EatApplication)getApplication()).getPlayer2Name());
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        //Fix this line with coresponding pages
        themeType =sharedPref.getBoolean("switchTheme", false);
        player1Name = sharedPref.getString("editName1",player1Name);
        player2Name = sharedPref.getString("editName2",player2Name);
        if (player1Name!="player1Name")
        {
            ((Where2EatApplication)getApplication()).createUser(player1Name);

        }
        if (player2Name != "player2Name")
        {
            ((Where2EatApplication)getApplication()).createUser(player2Name);
        }
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

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            boolean ret=true;
            //If not set here return can be set in the indivisual cases
            switch(item.getItemId()){

                case android.R.id.home:
                    //super.onBackPressed();
                    // or ret=true;
                    //statistics menu button
                    break;
                default:
                    //This occurs if neither ids are gathered
                    ret=super.onOptionsItemSelected(item);
                    break;
            }

            return ret;
        }
    }
}