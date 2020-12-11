package com.example.where2eat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.content.Context;
import android.widget.EditText;

public class HomeScreen extends AppCompatActivity {
    private Button startButton;
    private SharedPreferences sharedPref;
    private EditText editTextTextPersonName;
    private EditText editTextTextPersonName2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean themeType = sharedPref.getBoolean("switchTheme", false);
        if (!themeType) {
            setTheme(R.style.AppTheme);
        } else {
            setTheme(R.style.DarkTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        startButton = findViewById(R.id.buttonStart);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);

        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        if (themeType) {
            editTextTextPersonName.setTextColor(Color.WHITE);
            editTextTextPersonName2.setTextColor(Color.WHITE);
        }
        //Drop user table and choice table from db
        ((Where2EatApplication) getApplication()).resetUsers();

        startButton.setOnClickListener((v -> {
            // Enter user data to db
            String player1Name = editTextTextPersonName.getText().toString();
            String player2Name = editTextTextPersonName2.getText().toString();
            // Fill default player names if blank text boxes
            if (player1Name.isEmpty()) {
                player1Name = "Player 1";
            }
            if (player2Name.isEmpty()) {
                player2Name = "Player 2";
            }
            ((Where2EatApplication) getApplication()).createUser(player1Name);
            ((Where2EatApplication) getApplication()).createUser(player2Name);
            MainActivity.currentSwipeCount = 1;
            finishActivity(1);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }));
    }
}