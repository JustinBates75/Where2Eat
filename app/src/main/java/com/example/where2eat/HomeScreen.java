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
        startButton = findViewById(R.id.buttonStart);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);

        //Drop user table and choice table from db
        ((Where2EatApplication)getApplication()).resetUsers();

        startButton.setOnClickListener((v ->{
            // Enter user data to db
            String player1Name = editTextTextPersonName.getText().toString();
            String player2Name = editTextTextPersonName2.getText().toString();
            if(player1Name.isEmpty() || player2Name.isEmpty())
            {
                // Validation if either player name is empty
            }
            else // If names are filled out, allow the main activity to start
            {
                ((Where2EatApplication)getApplication()).createUser(player1Name);
                ((Where2EatApplication)getApplication()).createUser(player2Name);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
            /* Editor ed = sharedPref.edit();
            ed.putString("Player1Name", editTextTextPersonName.getText().toString());
            ed.putString("player2Name", editTextTextPersonName2.getText().toString());*/
        }));
        // sharedPref =getSharedPreferences("lastInputs", MODE_PRIVATE);
    }
}