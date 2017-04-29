package com.example.shirl1.mykitchenland;

import android.content.ContentUris;
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
    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "MANAGER";

    int MyId;


    //table1
    private static final String TABLE_RECIPES = "Recipes";
    //columns1:
    private static final String COLUMN_ID = "recipeid";
    private static final String COLUMN_RECIPE_NAME = "recipe_name";
    private static final String COLUMN_RECIPE_INSTRUCTIONS = "recipe_instructions";

    //table2
    private static final String TABLE_INGREDIENT = "Ingredient";
    //columns2:
    private static final String COLUMN_RECIPE_AMOUNT = "recipe_amount";
    private static final String COLUMN_RECIPE_INGREDIENT = "recipe_ingredient";

    //shopping list table
    private static final String TABLE_SHOPLIST = "Shopping_List";
    //shopping list columns
    private static final String COLUMN_LITS_ID = "list_id";
    private static final String COLUMN_LIST_NAME= "list_name";

    //Items table
    private static final String TABLE_ITEMS = "Items";
    //item table columns
    private static final String COLUMN_ID_LIST = "id_list";
    private static final String COLUMN_ITEM_NAME= "item_name";
    private static final String COLUMN_ITEM_AMOUNT= "item_amount";




    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db){

        String CREATE_TABLE_RECIPE= "CREATE TABLE " + TABLE_RECIPES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_RECIPE_INSTRUCTIONS + " TEXT, "
                + COLUMN_RECIPE_NAME + " TEXT )";

        String CREATE_TABLE_INGREDIENT= "CREATE TABLE " + TABLE_INGREDIENT + "("
                + COLUMN_ID + " INTEGER, "
                + COLUMN_RECIPE_AMOUNT + " INTEGER, "
                + COLUMN_RECIPE_INGREDIENT + " TEXT )";

        String CREATE_TABLE_SHOPLIST= "CREATE TABLE " + TABLE_SHOPLIST + "("
                + COLUMN_LITS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_LIST_NAME + " TEXT ) ";

        String CREATE_TABLE_ITEMS= "CREATE TABLE " + TABLE_ITEMS + "("
                + COLUMN_ID_LIST + " INTEGER, "
                + COLUMN_ITEM_NAME + " TEXT , "
                + COLUMN_ITEM_AMOUNT + " TEXT )";


        db.execSQL(CREATE_TABLE_RECIPE);
        db.execSQL(CREATE_TABLE_INGREDIENT);
        db.execSQL(CREATE_TABLE_ITEMS);
        db.execSQL(CREATE_TABLE_SHOPLIST);

        //ADD ALLLL TABLES
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPLIST);


        //DROP ALLLL TABLES
        onCreate(db);
    }

    //**********************here we start the funcs for ALL tables********************

    //add new row to the table
    public void addRecipe(Recipes recipe){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_RECIPE_NAME, recipe.get_recipename());
        values.put(COLUMN_RECIPE_INSTRUCTIONS, recipe.get_recipeinstructions());
        db.insert(TABLE_RECIPES, null, values);

        //get id
        final String MY_QUERY = "SELECT MAX(recipeid) FROM " + TABLE_RECIPES;
        Cursor cur = db.rawQuery(MY_QUERY, null);
        cur.moveToFirst();
        MyId = cur.getInt(0);
        cur.close();

        db.close();
    }

    public void addIngredient(Ingredient ingredient)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, MyId);
        values.put(COLUMN_RECIPE_AMOUNT, ingredient.get_amount());
        values.put(COLUMN_RECIPE_INGREDIENT, ingredient.get_ingredient());

        db.insert(TABLE_INGREDIENT, null, values);
        db.close();
    }



    public void clearTable()   {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECIPES,null,null);
        db.delete(TABLE_INGREDIENT,null,null);

    }




/*
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
        re.set_recipename(c.getString(c.getColumnIndex(COLUMN_RECIPE_NAME)));
        db.close();
        return re;
    }


    //delete from table
    public void deleteRecipe(String recipename){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_RECIPES + " WHERE" + COLUMN_RECIPE_NAME + "=\"" + recipename + "\";");
        db.close();
    }*/



    //get table of recipes
    public List <Recipes> getAllRecipes() {

        List <Recipes> all_recipes = new ArrayList <Recipes>();
        String selectQuery = "SELECT * FROM " + TABLE_RECIPES;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Recipes re = new Recipes();
                re.set_id(c.getInt((c.getColumnIndex(COLUMN_ID))));
                re.set_recipename(c.getString(c.getColumnIndex(COLUMN_RECIPE_NAME)));
                re.set_recipeinstructions(c.getString(c.getColumnIndex(COLUMN_RECIPE_INSTRUCTIONS)));

                all_recipes.add(re);
            } while (c.moveToNext());
        }

        return all_recipes;
    }

    public Cursor getRecipeForList(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_RECIPES, null);
        return c;
    }


    public List <Ingredient> getAllIngredient() {

        List <Ingredient> all_ingredient = new ArrayList <Ingredient>();
        String selectQuery = "SELECT * FROM " + TABLE_INGREDIENT;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Ingredient ingre = new Ingredient();
                ingre.set_id(c.getInt((c.getColumnIndex(COLUMN_ID))));
                ingre.set_amount(c.getString(c.getColumnIndex(COLUMN_RECIPE_AMOUNT)));
                ingre.set_ingredient(c.getString(c.getColumnIndex(COLUMN_RECIPE_INGREDIENT)));


                // adding to tags list
                all_ingredient.add(ingre);
            } while (c.moveToNext());
        }
        return all_ingredient;
    }


    //Add new shippinglsit
    public void addShopList(Shopping_list shoplist)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LIST_NAME, shoplist.get_listname());
        db.insert(TABLE_SHOPLIST, null, values);
        final String MY_QUERY = "SELECT MAX(list_id) FROM " + TABLE_SHOPLIST;
        Cursor cur = db.rawQuery(MY_QUERY, null);
        cur.moveToFirst();
        MyId = cur.getInt(0);
        cur.close();
        db.close();
    }

    public Cursor getListOfShopList()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_SHOPLIST, null);
        c.moveToFirst();
        return c;
    }

    public Cursor getListOfItems()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String get="SELECT * FROM " + TABLE_ITEMS;
        Cursor c = db.rawQuery(get,null);
        c.moveToFirst();
            return c;
    }


    public void addItems(List<Item> items)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        final String MY_QUERY = "SELECT MAX(list_id) FROM " + TABLE_SHOPLIST;
        Cursor cur = db.rawQuery(MY_QUERY, null);
        cur.moveToFirst();
        MyId = cur.getInt(0);
        for (Item item : items)
        {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID_LIST,MyId);
            values.put(COLUMN_ITEM_NAME, item.getItemName());
            values.put(COLUMN_ITEM_AMOUNT, item.getAmount());
            db.insert(TABLE_ITEMS, null, values);
        }
        db.close();
    }

    public void clearShopListTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ITEMS,null,null);
        db.delete(TABLE_SHOPLIST,null,null);
        db.close();
    }
}
