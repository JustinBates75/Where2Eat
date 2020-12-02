package com.example.where2eat;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button dineButton;
    private Button dashButton;

    private static final String DB_NAME ="resturant_db";
    private static  int DB_VERSION =1;
    /*ToDO:
    Make an array to hold the resturants and their id's
    Populate array form database
    cycle through array on any button click

    store both players checked resturants

    cross reference the two checks

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    /*new SQLiteOpenHelper(this, DB_NAME, null, DB_VERSION ){

        @Override
        public void onCreate(SQLiteDatabase db) {

        }
    }*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView =findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.ic_thekeg);
        dineButton =findViewById(R.id.buttonDine);
        dashButton =findViewById(R.id.buttonDash);


        dineButton.setOnClickListener((v -> {
            //everything once the dine button is clicked



        }));

        dashButton.setOnClickListener(v -> {
            //everything for when the dash button is clicked
            //move to next picture when clicked
        });
    }


}