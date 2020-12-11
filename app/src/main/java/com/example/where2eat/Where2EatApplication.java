package com.example.where2eat;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.PreparedStatement;
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
                //CREATE RestaurantTypes Table
                db.execSQL("CREATE TABLE IF NOT EXISTS RestaurantTypes (typeId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name TEXT);");
                //CREATE Restaurant Table
                db.execSQL("CREATE TABLE IF NOT EXISTS Restaurants (restaurantId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, priceRangeId INTEGER NOT NULL," +
                        "typeId INTEGER NOT NULL, name TEXT, FOREIGN KEY (priceRangeId) REFERENCES PriceRange (priceRangeId), FOREIGN KEY (typeId) REFERENCES RestaurantTypes (typeId));");
                //CREATE Users Table
                //CREATE UserChoices Table
                createUserTables(db);

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
                //DROP Users Table
                dropUserTables(db);
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
        Cursor cursor = db.rawQuery("SELECT Restaurants.restaurantId, Restaurants.name, RestaurantTypes.name, PriceRange.name, PriceRange.min, PriceRange.max FROM Restaurants, RestaurantTypes, PriceRange WHERE RestaurantTypes.typeId == Restaurants.typeId AND PriceRange.priceRangeId == Restaurants.priceRangeId;",null);
        cursor.moveToFirst();
        while(!cursor.isClosed())
        {
            Restaurant newRes = new Restaurant(cursor.getInt(0), cursor.getString(3), cursor.getString(2), cursor.getString(1), cursor.getInt(4), cursor.getInt(5));
            restaurants.add(newRes);
            cursor.moveToNext();
            if(cursor.isAfterLast())
                cursor.close();
        }
        return restaurants;
    }

    public void resetUsers() {
        SQLiteDatabase db = helper.getWritableDatabase();
        dropUserTables(db);
        createUserTables(db);
    }

    private void dropUserTables(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS UserChoices;");
        db.execSQL("DROP TABLE IF EXISTS Users;");
    }

    private void createUserTables(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Users (userId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS UserChoices (userId INTEGER NOT NULL, restaurantId INTEGER NOT NULL," +
                "FOREIGN KEY (userId) REFERENCES Users (userId), FOREIGN KEY (restaurantId) " +
                "REFERENCES Restaurants (restaurantId), PRIMARY KEY (userId, restaurantId));");
    }

    public void createUser(String userName) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", userName);
        db.insert("Users", null, values);
    }

    public void updateUser(String userName, int userId) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", userName);
        String where = "userId=?";
        String[] whereArgs = new String[]{String.valueOf(userId)};
        db.update("Users", values, where, whereArgs);
    }

    public String getPlayer1Name()
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users;", null);
        cursor.moveToFirst();
        String pName = cursor.getString(1);
        cursor.close();
        return pName;
    }

    public String getPlayer2Name()
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users;", null);
        cursor.moveToFirst();
        cursor.moveToNext();
        String pName = cursor.getString(1);
        cursor.close();
        return pName;
    }

    public void addChoice(boolean isPlayer1, int restaurantId)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        int userId = 0;
        if(isPlayer1) {
            userId = 1;
        }
        else {
            userId = 2;
        }
        db.execSQL("INSERT INTO UserChoices (userId, restaurantId) " +
                "VALUES (\"" + userId + "\",\"" + restaurantId + "\");");
    }

    public List<Restaurant> getChoices() {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<Restaurant> allRes = getRestaurantList();
        List<Restaurant> results = new ArrayList<Restaurant>();
        for(Restaurant res: allRes)
        {
            Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM UserChoices WHERE restaurantId == " + res.Id + ";", null);
            cursor.moveToFirst();
            if(cursor.getInt(0) == 2) { //If more than 2 users, change from == 2
                results.add(res);
            }
        }
        return results;
    }

    public boolean isMatch(int id)
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM UserChoices WHERE userId = 1 AND restaurantId = " + id, null);
        cursor.moveToFirst();
        return !cursor.isNull(0);
    }
}
