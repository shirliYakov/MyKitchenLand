package com.example.shirl1.mykitchenland;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper {

    //for the db:
    private static final String LOG = "DBHandler";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "MANAGER";


    //HERE we define the tables:
    private static final String TABLE_RECIPES = "Recipes";

    //and columns:
    private static final String COLUMN_ID = "recipeid";
    private static final String COLUMN_RECIPENAME = "recipename";
    private static final String COLUMN_RECIPEINSTRUCTIONS = "recipeinstructions";

    /*private static final String CREATE_TABLE_RECIPES = "CREATE TABLE " + TABLE_RECIPES +
            " (" + COLUMN_ID + " ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RECIPENAME + " TEXT" + ");";*/


    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db){

        String CREATE_TABLE_RECIPE= "CREATE TABLE " + TABLE_RECIPES + "(" + COLUMN_ID +
                  " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RECIPENAME + " TEXT " +
                COLUMN_RECIPEINSTRUCTIONS + " TEXT " + ");";

        db.execSQL(CREATE_TABLE_RECIPE);

        //ADD ALLLL TABLES
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        //DROP ALLLL TABLES
        onCreate(db);
    }

    //****here we start the funcs for ALL tables****


    //add new row to the table
    public void addRecipe(Recipes recipe){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_RECIPENAME, recipe.get_recipename());


        db.insert(TABLE_RECIPES, null, values);
        db.close();
    }


    //get specific recipe
    public Recipes getRecipe(int re_id) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_RECIPES + " WHERE "
                + COLUMN_ID + " = " + re_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Recipes re = new Recipes();
        re.set_id(c.getInt(c.getColumnIndex(COLUMN_ID)));
        re.set_recipename(c.getString(c.getColumnIndex(COLUMN_RECIPENAME)));
        db.close();
        return re;
    }


    //delete from table
    public void deleteRecipe(String recipename){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_RECIPES + " WHERE" + COLUMN_RECIPENAME + "=\"" + recipename + "\";");
        db.close();
    }

    //get table of recipes
    public List <Recipes> getAllRecipes() {

        List <Recipes> all_recipes = new ArrayList <Recipes>();
        String selectQuery = "SELECT * FROM " + TABLE_RECIPES;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Recipes re = new Recipes();
                re.set_id(c.getInt((c.getColumnIndex(COLUMN_ID))));
                re.set_recipename(c.getString(c.getColumnIndex(COLUMN_RECIPENAME)));

                // adding to tags list
                all_recipes.add(re);
            } while (c.moveToNext());
        }

        return all_recipes;
    }




}
