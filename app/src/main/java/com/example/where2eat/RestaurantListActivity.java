package com.example.where2eat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class RestaurantListActivity extends AppCompatActivity {
    List<Restaurant> restaurants;
    private TextView restaurantNameText;
    private TextView PlayerNameText;
    private TextView restaurantType;
    private TextView restaurantPrice;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        restaurants = ((Where2EatApplication)getApplication()).getRestaurantList();

        for (int i=0; i<restaurants.size(); i++) {
            Restaurant currentRestaurant = restaurants.get(i);

            LinearLayout linearLayout = new LinearLayout(this);
            setContentView(linearLayout);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            //Restaurant Image
            ImageView restaurantPic = new ImageView(this);
            restaurantPic.setImageResource(getResources().getIdentifier("ic_res" + currentRestaurant.Id, "drawable", getPackageName()));
            //imageView.setImageResource(getResources().getIdentifier("ic_res" + currentRestaurant.Id, "drawable", getPackageName()));
            //Restaurant Name
                TextView textViewRName = new TextView(this);
                textViewRName.setText(currentRestaurant.Name);
                linearLayout.addView(textViewRName);
            //Type of Restaurant
            TextView textViewRType= new TextView(this);
                textViewRType.setText(currentRestaurant.Type);
                linearLayout.addView(textViewRType);
             //Restaurant Price Range
            TextView textViewRPrice = new TextView(this);
                textViewRPrice.setText(currentRestaurant.PriceRange);
                linearLayout.addView(textViewRPrice);


            }
        }
        //imageView.setImageResource(getResources().getIdentifier("ic_res" + currentRestaurant.Id, "drawable", getPackageName()));

    }
