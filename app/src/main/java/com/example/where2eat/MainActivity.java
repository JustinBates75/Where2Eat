package com.example.where2eat;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button dineButton;
    private Button dashButton;
    private TextView restaurantNameText;
    private TextView PlayerNameText;
    private static int player1SwipeCount=0;
    private static int player2SwipeCount =0;
    private static int player1Choice;
    private static int player2Choice;
    private boolean isPlayer1=true;
    private static int resourceid;
    //private static final String DB_NAME ="resturant_db";
    //private static  int DB_VERSION =1;
    /*ToDO:
    Make an array to hold the resturants and their id's
    Populate array form database
    cycle through array on any button click

    store both players checked resturants

    cross reference the two checks

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
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
        restaurantNameText =findViewById(R.id.resturantNameText);
        PlayerNameText =findViewById(R.id.PlayerNameText);


        dineButton.setOnClickListener((v -> {
            //everything once the dine button is clicked
            //Change to Selected
            //Change restaurant title
            //Change Restaurant Picture
            //Add 1 to swipe count
            if (player1SwipeCount>10) {
                isPlayer1 =false;
            }
                if (isPlayer1 == true) {
                    PlayerNameText.setText("Player 1");
                    player1SwipeCount += 1;
                    restaurantNameText.setText("New Restaurant" + player1SwipeCount);

                    if (player1SwipeCount==2){
                        imageView.setImageResource(R.drawable.ic_awlogo);
                    }
                    else if (player1SwipeCount==3){
                        imageView.setImageResource(R.drawable.ic_beertownlogo);
                    }
                    else if (player1SwipeCount==4){
                        imageView.setImageResource(R.drawable.ic_dairyqueenlogo);
                    }
                    else if (player1SwipeCount==5){
                        imageView.setImageResource(R.drawable.ic_eastsidemarioslogo);
                    }
                    else if (player1SwipeCount==6){
                        imageView.setImageResource(R.drawable.ic_mcdonaldslogo);
                    }
                    else if (player1SwipeCount==7){
                        imageView.setImageResource(R.drawable.ic_newyorkfrieslogo);
                    }
                    else if (player1SwipeCount==8){
                        //imageView.setImageResource(R.drawable.ic_timhortonslogo);
                        resourceid = getResources().getIdentifier("ic_thekeg", "drawable", getPackageName());
                        imageView.setImageResource(resourceid);
                    }
            } else {
                PlayerNameText.setText("Player 2");
                player2SwipeCount += 1;
                restaurantNameText.setText("New Restaurant" + player2SwipeCount);
                    if (player2SwipeCount==1){
                        imageView.setImageResource(R.drawable.ic_awlogo);
                    }
                    else if (player2SwipeCount==2){
                        imageView.setImageResource(R.drawable.ic_dairyqueenlogo);
                    }
                    else{
                        imageView.setImageResource(R.drawable.ic_eastsidemarioslogo);
                    }
            }
        }));

        dashButton.setOnClickListener(v -> {
            //everything for when the dash button is clicked
            //Change to Not Selected
            //Change restaurant title
            //Change Restaurant Picture
            //Add 1 to swipe count
            if (player1SwipeCount>10) {
                isPlayer1 =false;
            }
            if (isPlayer1 == true) {
                PlayerNameText.setText("Player 1");
                player1SwipeCount += 1;
                restaurantNameText.setText("New Restaurant" + player1SwipeCount);
                if (player1SwipeCount==2){
                    imageView.setImageResource(R.drawable.ic_awlogo);
                }
                else if (player1SwipeCount==3){
                    imageView.setImageResource(R.drawable.ic_dairyqueenlogo);
                }
                else {
                    imageView.setImageResource(R.drawable.ic_eastsidemarioslogo);
                }
            } else {
                PlayerNameText.setText("Player 2");
                player2SwipeCount += 1;
                restaurantNameText.setText("New Restaurant" + player2SwipeCount);
                if (player2SwipeCount==1){
                    imageView.setImageResource(R.drawable.ic_awlogo);
                }
                else if (player2SwipeCount==2){
                    imageView.setImageResource(R.drawable.ic_dairyqueenlogo);
                }
                else{
                    imageView.setImageResource(R.drawable.ic_eastsidemarioslogo);
                }
            }
        });
    }


}