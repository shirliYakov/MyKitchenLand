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
    private static final int DATABASE_VERSION = 13;
    private static final String DATABASE_NAME = "MANAGER";

    int MyId;
    int MyId_m;

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

    private static final String TABLE_RECIPES_MANAGER = "Recipes_manager";
    private static final String TABLE_INGREDIENT_MANAGER = "Ingredient_manager";

    //table3
    private static final String TABLE_USERS = "users";
    //columns2:
    private static final String COLUMN_NAME = "user_name";
    private static final String COLUMN_LAST_NAME = "user_lastname";
    private static final String COLUMN_EMAIL = "user_email";
    private static final String COLUMN_PASSWORD = "user_password";





    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){

        String CREATE_TABLE_RECIPE= "CREATE TABLE " + TABLE_RECIPES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_RECIPE_NAME + " TEXT unique , "
                + COLUMN_RECIPE_INSTRUCTIONS + " TEXT )";

        String CREATE_TABLE_INGREDIENT= "CREATE TABLE " + TABLE_INGREDIENT + "("
                + COLUMN_ID + " INTEGER, "
                + COLUMN_RECIPE_AMOUNT + " INTEGER, "
                + COLUMN_RECIPE_INGREDIENT + " TEXT )";

        String CREATE_TABLE_RECIPE_MANAGER= "CREATE TABLE " + TABLE_RECIPES_MANAGER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_RECIPE_NAME + " TEXT, "
                + COLUMN_RECIPE_INSTRUCTIONS + " TEXT )";

        String CREATE_TABLE_INGREDIENT_MANAGER= "CREATE TABLE " + TABLE_INGREDIENT_MANAGER + "("
                + COLUMN_ID + " INTEGER, "
                + COLUMN_RECIPE_AMOUNT + " INTEGER, "
                + COLUMN_RECIPE_INGREDIENT + " TEXT )";

        String CREATE_TABLE_USERS= "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_EMAIL + " TEXT PRIMARY KEY , "
                + COLUMN_PASSWORD + " TEXT , "
                + COLUMN_NAME + " TEXT , "
                + COLUMN_LAST_NAME + " TEXT )";

        db.execSQL(CREATE_TABLE_RECIPE);
        db.execSQL(CREATE_TABLE_INGREDIENT);
        db.execSQL(CREATE_TABLE_RECIPE_MANAGER);
        db.execSQL(CREATE_TABLE_INGREDIENT_MANAGER);
        db.execSQL(CREATE_TABLE_USERS);


    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES_MANAGER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENT_MANAGER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);


        onCreate(db);
    }

    //**********************here we start the funcs for ALL tables********************

    public void clearTable()   {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECIPES,null,null);
        db.delete(TABLE_INGREDIENT,null,null);

    }

    public void clearTableManager()   {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECIPES_MANAGER,null,null);
        db.delete(TABLE_INGREDIENT_MANAGER,null,null);

    }

    public void clearTableUsers()   {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS,null,null);

    }


    public void addUser(USERS user){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_LAST_NAME, user.getLastname());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());

        db.insert(TABLE_USERS, null, values);

        db.close();
    }

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

    public void addRecipe_manager(Recipes recipe){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_RECIPE_NAME, recipe.get_recipename());
        values.put(COLUMN_RECIPE_INSTRUCTIONS, recipe.get_recipeinstructions());
        db.insert(TABLE_RECIPES_MANAGER, null, values);

        //get id
        final String MY_QUERY = "SELECT MAX(recipeid) FROM " + TABLE_RECIPES_MANAGER;
        Cursor cur = db.rawQuery(MY_QUERY, null);
        cur.moveToFirst();
        MyId_m = cur.getInt(0);
        cur.close();

        db.close();
    }

    public void addIngredient(Ingredient ingredient){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, MyId);
        values.put(COLUMN_RECIPE_AMOUNT, ingredient.get_amount());
        values.put(COLUMN_RECIPE_INGREDIENT, ingredient.get_ingredient());

        db.insert(TABLE_INGREDIENT, null, values);
        db.close();
    }

    public void addIngredient_manager(Ingredient ingredient){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, MyId_m);
        values.put(COLUMN_RECIPE_AMOUNT, ingredient.get_amount());
        values.put(COLUMN_RECIPE_INGREDIENT, ingredient.get_ingredient());

        db.insert(TABLE_INGREDIENT_MANAGER, null, values);
        db.close();
    }

    public USERS getUserByEmail(String email) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_USERS + " WHERE "
                + COLUMN_EMAIL + " = '" + email + "'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        if(c.getCount() <= 0){
            c.close();
            return null;
        }

        if (c != null)
            c.moveToFirst();

        USERS u = new USERS();
        u.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
        u.setLastname(c.getString(c.getColumnIndex(COLUMN_LAST_NAME)));
        u.setEmail(c.getString(c.getColumnIndex(COLUMN_EMAIL)));
        u.setPassword(c.getString(c.getColumnIndex(COLUMN_PASSWORD)));
        db.close();

        return u;
    }




    public Recipes getRecipeByName(String re_name) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_RECIPES + " WHERE "
                + COLUMN_RECIPE_NAME + " = '" + re_name + "'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        if(c.getCount() <= 0){
            c.close();
            return null;
        }

        if (c != null)
            c.moveToFirst();

        Recipes re = new Recipes();
        re.set_id(c.getInt(c.getColumnIndex(COLUMN_ID)));
        re.set_recipename(c.getString(c.getColumnIndex(COLUMN_RECIPE_NAME)));
        re.set_recipeinstructions(c.getString(c.getColumnIndex(COLUMN_RECIPE_INSTRUCTIONS)));
        db.close();

        return re;
    }

    public Recipes getRecipeByName_manager(String re_name) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_RECIPES_MANAGER + " WHERE "
                + COLUMN_RECIPE_NAME + " = '" + re_name + "'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        if(c.getCount() <= 0){
            c.close();
            return null;
        }

        if (c != null)
            c.moveToFirst();

        Recipes re = new Recipes();
        re.set_id(c.getInt(c.getColumnIndex(COLUMN_ID)));
        re.set_recipename(c.getString(c.getColumnIndex(COLUMN_RECIPE_NAME)));
        re.set_recipeinstructions(c.getString(c.getColumnIndex(COLUMN_RECIPE_INSTRUCTIONS)));
        db.close();

        return re;
    }


    //delete from table
    public void deleteRecipe(String recipename){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_RECIPES + " WHERE " + COLUMN_RECIPE_NAME + " = '" + recipename + "'");
        db.close();
    }

    public String getLastUser(){

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_USERS ;
        String log ="";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        if(c.getCount() <= 0){
            c.close();
            return null;
        }

        if (c != null)
            c.moveToLast();

        USERS u = new USERS();
        u.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
        u.setLastname(c.getString(c.getColumnIndex(COLUMN_LAST_NAME)));
        log = log + u.getName() + " " + u.getLastname();
        db.close();

        return log;

    }

    public void changePosition(USERS user){

        String name = user.getName();

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_USERS + " WHERE " + COLUMN_NAME + " = '" + name + "'");
        addUser(user);
        db.close();
    }




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

    public List <USERS> getAllusers() {

        List <USERS> all_users = new ArrayList <USERS>();
        String selectQuery = "SELECT * FROM " + TABLE_USERS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                USERS u = new USERS();
                u.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
                u.setLastname(c.getString(c.getColumnIndex(COLUMN_LAST_NAME)));
                u.setEmail(c.getString(c.getColumnIndex(COLUMN_EMAIL)));
                u.setPassword(c.getString(c.getColumnIndex(COLUMN_PASSWORD)));
                db.close();

                all_users.add(u);
            } while (c.moveToNext());
        }

        return all_users;
    }


    public Cursor getRecipeForList(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_RECIPES, null);
        return c;
    }

    public Cursor getRecipeForList_manager(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_RECIPES_MANAGER, null);
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

    public List <Ingredient> getIngredientById(int r_id) {

        List <Ingredient> all_ingredient = new ArrayList <Ingredient>();
        String selectQuery = "SELECT  * FROM " + TABLE_INGREDIENT + " WHERE "
                + COLUMN_ID + " = " + r_id;

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

    public List <Ingredient> getIngredientById_manager(int r_id) {

        List <Ingredient> all_ingredient = new ArrayList <Ingredient>();
        String selectQuery = "SELECT  * FROM " + TABLE_INGREDIENT_MANAGER + " WHERE "
                + COLUMN_ID + " = " + r_id;

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
    }*/
