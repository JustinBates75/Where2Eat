package com.example.where2eat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.TextView;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.content.Context;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button dineButton;
    private Button dashButton;
    private TextView restaurantNameText;
    private TextView PlayerNameText;

    private static int currentSwipeCount = 0;
    private static final int MAX_SWIPE_COUNT = 10;
    private boolean isPlayer1 = true;
    List<Where2EatApplication.Restaurant> restaurants;

    private SharedPreferences sharedPref;

    /*ToDO:
    Make an array to hold the restaurants and their id's
    Populate array form database
    cycle through array on any button click

    store both players checked resturants

    cross reference the two checks

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        dineButton = findViewById(R.id.buttonDine);
        dashButton = findViewById(R.id.buttonDash);
        restaurantNameText = findViewById(R.id.resturantNameText);
        PlayerNameText = findViewById(R.id.PlayerNameText);

        restaurants = ((Where2EatApplication)getApplication()).getRestaurantList();
        PlayerNameText.setText(((Where2EatApplication)getApplication()).getPlayer1Name());
        ChangeToNextRestaurant();
        dineButton.setOnClickListener(v -> {
            onDineOrDash(true);
        }); //end of dine button listener

        dashButton.setOnClickListener(v -> {
            onDineOrDash(false);
        }); //end of dash button listener

        //sharedPref = getSharedPreferences("lastInputs", MODE_PRIVATE);
    }// end of create

    public void onDineOrDash(boolean isDine)
    {
        if(isDine)
        {
            //Add choice to db
        }

        //Check if player change needed or end of player 2's turn
        if(currentSwipeCount >= MAX_SWIPE_COUNT ||
            currentSwipeCount >= restaurants.size())
        {
            if (isPlayer1) {
                isPlayer1 = false;
                PlayerNameText.setText(((Where2EatApplication)getApplication()).getPlayer2Name());
                currentSwipeCount = 0;
                ChangeToNextRestaurant();
            }
            else {
                //End player 2's turn
                //Move to results page

                //Delete this, testing only
                currentSwipeCount = 0;
                PlayerNameText.setText(((Where2EatApplication)getApplication()).getPlayer1Name());
                isPlayer1 = true;
                ChangeToNextRestaurant();
            }
        }
        else { //Otherwise, change to next restaurant
            ChangeToNextRestaurant();
        }
    }

    public void ChangeToNextRestaurant()
    {
        Where2EatApplication.Restaurant currentRestaurant = restaurants.get(currentSwipeCount);
        imageView.setImageResource(getResources().getIdentifier("ic_res" + currentRestaurant.Id, "drawable", getPackageName()));
        restaurantNameText.setText(currentRestaurant.Name + " : " + (currentSwipeCount + 1));
        currentSwipeCount++;
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