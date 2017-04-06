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
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MANAGER";

    //private  static final String TAG = "noanachmias";

    //HERE we define the tables:
    private static final String TABLE_RECIPES = "Recipes";

    //and columns:
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_RECIPENAME = "recipename";

    //public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //SQLiteDatabase db = this.getWritableDatabase();
    }


    public void onCreate(SQLiteDatabase db){


        String CREATE_TABLE_RECIPES;
        CREATE_TABLE_RECIPES = "CREATE TABLE " + TABLE_RECIPES
                + "(" + COLUMN_ID + " ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RECIPENAME + " TEXT);";

        db.execSQL(CREATE_TABLE_RECIPES);
        //Log.d(TAG,"hii noa");
        //ADD ALLLL TABLES
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXIST " + TABLE_RECIPES);
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
        List <Recipes> recip_all = new ArrayList <Recipes>();
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
                recip_all.add(re);
            } while (c.moveToNext());
        }
        db.close();
        return recip_all;
    }

    //print the table
    public String databaseToString(){

        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query  = "SELECT * FROM " + TABLE_RECIPES + " WHERE 1";

        Cursor c = db.rawQuery(query, null);//location
        c.moveToFirst();//move to first row in result

        while(!c.isAfterLast()){//while its not the end
            if(c.getString(c.getColumnIndex("recipename"))!=null){
                dbString += c.getString(c.getColumnIndex("recipename"));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;
    }
}
