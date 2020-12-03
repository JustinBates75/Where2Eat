package com.example.where2eat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {
    private Button startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        startButton =findViewById(R.id.buttonStart);
        startButton.setOnClickListener((v ->{
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }));
    }
}