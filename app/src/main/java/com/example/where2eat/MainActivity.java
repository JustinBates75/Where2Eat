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

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button dineButton;
    private Button dashButton;
    private TextView restaurantNameText;
    private TextView PlayerNameText;
    private TextView restaurantType;
    private TextView restaurantPrice;

    private static int currentSwipeCount = 0;
    private static final int MAX_SWIPE_COUNT = 10;
    private boolean isPlayer1 = true;
    List<Restaurant> restaurants;
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
        restaurantType = findViewById(R.id.restaurantType);
        restaurantPrice = findViewById(R.id.restaurantPrice);

        restaurants = ((Where2EatApplication)getApplication()).getRestaurantList();
        PlayerNameText.setText(((Where2EatApplication)getApplication()).getPlayer1Name());
        ChangeToNextRestaurant();
        imageView.animate().alpha(1f).setDuration(1);
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
            ((Where2EatApplication)getApplication()).addChoice(isPlayer1, currentSwipeCount);

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
                //if resturant id equals restaurant id of player one display snackbar
                //End player 2's turn
                //Move to results page
                //Delete this, testing only
                /*currentSwipeCount = 0;
                PlayerNameText.setText(((Where2EatApplication)getApplication()).getPlayer1Name());
                isPlayer1 = true;
                ChangeToNextRestaurant();*/
                //if (((Where2EatApplication)getApplication()).isMatch(currentSwipeCount) &&!isPlayer1){
                    //Snackbar.make(this, )findViewById(R.id.mainLayout)


                    //get amount of choices
                    //amount of choices concat iwht matches found
                //}
                startActivity(new Intent(getApplicationContext(), ResultsActivity.class));
            }
        }
        else { //Otherwise, change to next restaurant
            ChangeToNextRestaurant();

        }
    }

    public void ChangeToNextRestaurant()
    {
        Restaurant currentRestaurant = restaurants.get(currentSwipeCount);
        // animation here
        imageView.setAlpha(1f);
        imageView.animate().alpha(0f).setDuration(500);
        Timer timer = new Timer(true);
        //imageView.setImageResource(getResources().getIdentifier("ic_res" + currentRestaurant.Id, "drawable", getPackageName()));
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageResource(getResources().getIdentifier("ic_res" + currentRestaurant.Id, "drawable", getPackageName()));
                        imageView.animate().alpha(1f).setDuration(500);
                    }
                });
            }
        }, 500);


        restaurantNameText.setText(currentRestaurant.Name + " : " + (currentSwipeCount + 1));
        restaurantType.setText("Type of Restaurant: " + currentRestaurant.Type);
        restaurantPrice.setText("Price Range: " + currentRestaurant.PriceRange +" " + currentRestaurant.MIN +" - " + currentRestaurant.MAX);
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
                startActivity(new Intent(getApplicationContext(), RestaurantListActivity.class));
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

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}