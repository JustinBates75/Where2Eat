package com.example.where2eat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        startButton =findViewById(R.id.buttonStart);

        startButton.setOnClickListener((v ->{
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            Editor ed = sharedPref.edit();
            ed.putString("Player1Name", editTextTextPersonName.getText().toString());
            ed.putString("player2Name", editTextTextPersonName2.getText().toString());
        }));
        sharedPref =getSharedPreferences("lastInputs", MODE_PRIVATE);
    }
}