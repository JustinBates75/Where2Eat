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
        restaurants = ((Where2EatApplication) getApplication()).getRestaurantList();
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for (Restaurant curRes : restaurants) {
            ImageView iView = new ImageView(this);
            iView.setAdjustViewBounds(true);
            iView.setImageResource(getResources().getIdentifier("ic_res" + curRes.Id, "drawable", getPackageName()));
            linearLayout.addView(iView);
            TextView textViewRName = new TextView(this);
            textViewRName.setText(curRes.Name);
            linearLayout.addView(textViewRName);
            //Type of Restaurant
            TextView textViewRType = new TextView(this);
            textViewRType.setText("Type of Restaurant:" + curRes.Type);
            linearLayout.addView(textViewRType);
            //Restaurant Price Range
            TextView textViewRPrice = new TextView(this);
            textViewRPrice.setText("Price Range:" + curRes.PriceRange + curRes.MIN + " - " + curRes.MAX);
            linearLayout.addView(textViewRPrice);
        }
        setContentView(linearLayout);
    }
    //imageView.setImageResource(getResources().getIdentifier("ic_res" + currentRestaurant.Id, "drawable", getPackageName()));

}
