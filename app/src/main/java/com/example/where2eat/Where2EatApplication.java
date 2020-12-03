package com.example.where2eat;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Where2EatApplication extends Application {
    private static final String DB_NAME = "db_Consensus";
    private static int DB_VERSION =1;
    private SQLiteOpenHelper helper;
    @Override
    public void onCreate() {
        helper = new SQLiteOpenHelper(this, DB_NAME, null, DB_VERSION ){
            @Override
            public void onCreate(SQLiteDatabase db) {
                //Create Tables
                //CREATE PriceRange Table
                db.execSQL("CREATE TABLE IF NOT EXISTS PriceRange (priceRangeId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, min REAL, max REAL, name TEXT);");
                //CREATE Users Table
                db.execSQL("CREATE TABLE IF NOT EXISTS Users (userId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name TEXT);");
                //CREATE RestaurantTypes Table
                db.execSQL("CREATE TABLE IF NOT EXISTS RestaurantTypes (typeId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name TEXT);");
                //CREATE Restaurant Table
                db.execSQL("CREATE TABLE IF NOT EXISTS Restaurants (restaurantId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, priceRangeId INTEGER NOT NULL," +
                        "typeId INTEGER NOT NULL, name TEXT, FOREIGN KEY (priceRangeId) REFERENCES PriceRange (priceRangeId), FOREIGN KEY (typeId) REFERENCES RestaurantTypes (typeId));");
                //CREATE UserChoices Table
                db.execSQL("CREATE TABLE IF NOT EXISTS UserChoices (userId INTEGER NOT NULL, restaurantId INTEGER NOT NULL," +
                        "FOREIGN KEY (userId) REFERENCES Users (userId), FOREIGN KEY (restaurantId) " +
                        "REFERENCES Restaurants (restaurantId), PRIMARY KEY (userId, restaurantId));");

                //Insert Default DB data
                //INSERT RestaurantType Data
                db.execSQL("INSERT INTO RestaurantTypes (name) VALUES " +
                        "(\"Fast food\"), " +
                        "(\"Family\"), " +
                        "(\"Pub/grill\"), " +
                        "(\"Bar\");");
                //INSERT PriceRange Data
                db.execSQL("INSERT INTO PriceRange (min, max, name) VALUES " +
                        "(\"5\", \"10\", \"Snack\"), " +
                        "(\"10\", \"25\", \"Cheap Meal\"), " +
                        "(\"25\", \"50\", \"Average Meal\"), " +
                        "(\"50\", \"100\", \"Expensive Meal\");");
                //INSERT Restaurant Data
                db.execSQL("INSERT INTO Restaurants (priceRangeId, typeId, name) VALUES" +
                        "(\"4\", \"3\", \"The Keg\"), " +
                        "(\"2\", \"1\", \"McDonald's\"), " +
                        "(\"2\", \"1\", \"A&W\"), " +
                        "(\"3\", \"2\", \"East Side Mario's\")," +
                        "(\"1\", \"1\", \"Dairy Queen\"), " +
                        "(\"3\", \"3\", \"Beer Town\")," +
                        "(\"1\", \"1\", \"Tim Horton's\"), " +
                        "(\"2\", \"1\", \"New York Fries\")," +
                        "(\"2\", \"1\", \"Pizza Pizza\"), " +
                        "(\"2\", \"2\", \"Pizza Hut\");");
                //INSERT Users
                /* db.execSQL("INSERT INTO Users (name) VALUES (\"User 1\"), (\"User 2\");"); */
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                //DROP UserChoices Table
                db.execSQL("DROP TABLE IF EXISTS UserChoices;");
                //DROP Users Table
                db.execSQL("DROP TABLE IF EXISTS Users;");
                //DROP RestaurantTypes Table
                db.execSQL("DROP TABLE IF EXISTS RestaurantTypes;");
                //DROP PriceRange Table
                db.execSQL("DROP TABLE IF EXISTS PriceRange;");
                //DROP Restaurants Table
                db.execSQL("DROP TABLE IF EXISTS Restaurants;");
                onCreate(db);
            }
        };
        super.onCreate();
    }

    public List<Restaurant> getRestaurantList(){
        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Restaurants.restaurantId, Restaurants.name, RestaurantTypes.name, PriceRange.name FROM Restaurants, RestaurantTypes, PriceRange WHERE RestaurantTypes.typeId == Restaurants.typeId AND PriceRange.priceRangeId == Restaurants.priceRangeId;",null);
        cursor.moveToFirst();
        while(!cursor.isClosed())
        {
            Restaurant newRes = new Restaurant(cursor.getInt(0), cursor.getString(3), cursor.getString(2), cursor.getString(1));
            restaurants.add(newRes);
            cursor.moveToNext();
            if(cursor.isAfterLast())
                cursor.close();
        }
        return restaurants;
    }

    public class Restaurant{
        public int Id;
        public String PriceRange;
        public String Type;
        public String Name;

        public Restaurant(int id, String priceRange, String type, String name) {
            this.Id = id;
            this.PriceRange = priceRange;
            this.Type = type;
            this.Name = name;
        }
    }
}
