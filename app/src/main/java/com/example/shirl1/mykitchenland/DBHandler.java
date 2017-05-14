package com.example.shirl1.mykitchenland;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;



public class DBHandler extends SQLiteOpenHelper {

    //for the db:
    private static final String LOG = "DBHandler";
    private static final int DATABASE_VERSION = 14;
    private static final String DATABASE_NAME = "MANAGER";

    int MyId;
    int MyId_m;
    String ListId;

    //table1
    private static final String TABLE_RECIPES = "Recipes";
    //columns1:
    private static final String COLUMN_ID = "recipeid";
    private static final String COLUMN_RECIPE_NAME = "recipe_name";
    private static final String COLUMN_RECIPE_INSTRUCTIONS = "recipe_instructions";
    private static final String COLUMN_IMAGE = "img";
    private static final String COLUMN_TIME = "preparation_time";

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

    //table4
    private static final String TABLE_WEEK = "week";
    //columns4:
    private static final String COLUMN_DAY1 = "day1";
    private static final String COLUMN_DAY2 = "day2";
    private static final String COLUMN_DAY3 = "day3";
    private static final String COLUMN_DAY4 = "day4";
    private static final String COLUMN_DAY5 = "day5";
    private static final String COLUMN_DAY6 = "day6";
    private static final String COLUMN_DAY7 = "day7";

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

    //Inventory table
    private static final String TABLE_INVENTORY = "inventory";
    //item table columns
    private static final String COLUMN_ID_ITEM = "id_item";
    private static final String COLUMN_NAME_ITEM= "name_item";
    private static final String COLUMN_AMOUNT_ITEM= "amount_item";



    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){

        String CREATE_TABLE_RECIPE= "CREATE TABLE " + TABLE_RECIPES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_RECIPE_NAME + " TEXT unique , "
                + COLUMN_RECIPE_INSTRUCTIONS + " TEXT, "
                + COLUMN_TIME + " TEXT, "
                + COLUMN_IMAGE + " BLOB )";

        String CREATE_TABLE_INGREDIENT= "CREATE TABLE " + TABLE_INGREDIENT + "("
                + COLUMN_ID + " INTEGER, "
                + COLUMN_RECIPE_AMOUNT + " INTEGER, "
                + COLUMN_RECIPE_INGREDIENT + " TEXT )";

        String CREATE_TABLE_RECIPE_MANAGER= "CREATE TABLE " + TABLE_RECIPES_MANAGER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_RECIPE_NAME + " TEXT, "
                + COLUMN_RECIPE_INSTRUCTIONS + " TEXT,"
                + COLUMN_TIME + " TEXT, "
                + COLUMN_IMAGE + " BLOB )";

        String CREATE_TABLE_INGREDIENT_MANAGER= "CREATE TABLE " + TABLE_INGREDIENT_MANAGER + "("
                + COLUMN_ID + " INTEGER, "
                + COLUMN_RECIPE_AMOUNT + " INTEGER, "
                + COLUMN_RECIPE_INGREDIENT + " TEXT )";

        String CREATE_TABLE_USERS= "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_EMAIL + " TEXT PRIMARY KEY , "
                + COLUMN_PASSWORD + " TEXT , "
                + COLUMN_NAME + " TEXT , "
                + COLUMN_LAST_NAME + " TEXT )";

        String CREATE_TABLE_WEEK= "CREATE TABLE " + TABLE_WEEK + "("
                + COLUMN_DAY1 + " TEXT , "
                + COLUMN_DAY2 + " TEXT , "
                + COLUMN_DAY3 + " TEXT , "
                + COLUMN_DAY4 + " TEXT , "
                + COLUMN_DAY5 + " TEXT , "
                + COLUMN_DAY6 + " TEXT , "
                + COLUMN_DAY7 + " TEXT )";

        String CREATE_TABLE_SHOPLIST= "CREATE TABLE " + TABLE_SHOPLIST + "("
                + COLUMN_LITS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_LIST_NAME + " TEXT ) ";

        String CREATE_TABLE_ITEMS= "CREATE TABLE " + TABLE_ITEMS + "("
                + COLUMN_ID_LIST + " INTEGER, "
                + COLUMN_ITEM_NAME + " TEXT , "
                + COLUMN_ITEM_AMOUNT + " TEXT )";

        String CREATE_TABLE_INVENTORY= "CREATE TABLE " + TABLE_INVENTORY + "("
                + COLUMN_ID_ITEM + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME_ITEM + " TEXT , "
                + COLUMN_AMOUNT_ITEM + " TEXT )";



        db.execSQL(CREATE_TABLE_RECIPE);
        db.execSQL(CREATE_TABLE_INGREDIENT);
        db.execSQL(CREATE_TABLE_RECIPE_MANAGER);
        db.execSQL(CREATE_TABLE_INGREDIENT_MANAGER);
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_WEEK);
        db.execSQL(CREATE_TABLE_SHOPLIST);
        db.execSQL(CREATE_TABLE_ITEMS);
        db.execSQL(CREATE_TABLE_INVENTORY);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES_MANAGER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENT_MANAGER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEEK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPLIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY);

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

    public void clearTableUsers()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS,null,null);
    }

    public void clearTableWeek()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WEEK,null,null);
    }


    public void addUser(USERS user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_LAST_NAME, user.getLastname());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public void addWeek(Week week)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_DAY1, week.getDay1());
        values.put(COLUMN_DAY2, week.getDay2());
        values.put(COLUMN_DAY3, week.getDay3());
        values.put(COLUMN_DAY4, week.getDay4());
        values.put(COLUMN_DAY5, week.getDay5());
        values.put(COLUMN_DAY6, week.getDay6());
        values.put(COLUMN_DAY7, week.getDay7());

        db.insert(TABLE_WEEK, null, values);

        db.close();
    }

    //add new row to the table
    public void addRecipe(Recipes recipe){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_RECIPE_NAME, recipe.get_recipename());
        values.put(COLUMN_RECIPE_INSTRUCTIONS, recipe.get_recipeinstructions());
        values.put(COLUMN_TIME, recipe.getTime());
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
        re.setTime(c.getString(c.getColumnIndex(COLUMN_TIME)));
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
                re.setTime(c.getString(c.getColumnIndex(COLUMN_TIME)));

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

    //Add new shippinglsit
    public int addShopList(Shopping_list shoplist)
    {
        if (checkIfListNameExist(shoplist.get_listname())==0)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            String MY_QUERY = "SELECT MAX(list_id) FROM " + TABLE_SHOPLIST;
            Cursor cur = db.rawQuery(MY_QUERY, null);

            cur.moveToFirst();
            ListId = cur.getString(0);
            String id=ListId;
            ContentValues values = new ContentValues();
            //  values.put(COLUMN_LITS_ID, shoplist.get_listname());
            values.put(COLUMN_LIST_NAME, shoplist.get_listname());
            db.insert(TABLE_SHOPLIST, null, values);
            shoplist.set_listId(id);
            cur.close();
            db.close();
            return 1;}
        else
            return 0;

    }

    public Cursor getListOfShopList()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_SHOPLIST, null);
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


    public List <Shopping_list> getListOfshoppingList()
    {
        List <Shopping_list> all_lists = new ArrayList <Shopping_list>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_SHOPLIST, null);

        if (c.moveToFirst()) {
            do {
                Shopping_list shopl = new Shopping_list();
                shopl.set_listId(c.getString(c.getColumnIndex(COLUMN_LITS_ID)));
                shopl.set_listname(c.getString(c.getColumnIndex(COLUMN_LIST_NAME)));
                all_lists.add(shopl);
            } while (c.moveToNext());
        }
        return all_lists;
    }

    public List <Item> getItemsByListId(String list_id)
    {
        List <Item> items_list = new ArrayList <Item>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT  * FROM " + TABLE_ITEMS + " WHERE "
                + COLUMN_ID_LIST + " = " + list_id, null);

        if (c.moveToFirst()) {
            do {
                Item item = new Item();
                item.set_list_id(c.getInt((c.getColumnIndex(COLUMN_ID_LIST))));
                item.setItemName(c.getString(c.getColumnIndex(COLUMN_ITEM_NAME)));
                item.setAmount(c.getString(c.getColumnIndex(COLUMN_ITEM_AMOUNT)));
                items_list.add(item);
            } while (c.moveToNext());
        }
        return items_list;
    }

    public Shopping_list getShopListByID(String list_name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT  * FROM " + TABLE_SHOPLIST + " WHERE "
                + COLUMN_LIST_NAME + " = '" + list_name + "'", null);
        if (c.getCount()<1)
            return null;
        else if (c != null)
            c.moveToFirst();
        Shopping_list shop = new Shopping_list();
        shop.set_listId(c.getString(c.getColumnIndex(COLUMN_LITS_ID)));
        shop.set_listname(c.getString(c.getColumnIndex(COLUMN_LIST_NAME)));
        db.close();
        return shop;
    }



    public String getColumnLitsIdByName(String list_name)
    {
        String list_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT  * FROM " + TABLE_SHOPLIST + " WHERE "
                + COLUMN_LIST_NAME + " = '" + list_name + "'", null);
        if (c != null)
            c.moveToFirst();
        list_id=(c.getString(c.getColumnIndex(COLUMN_LITS_ID)));
        db.close();
        return list_id;
    }

    public int change_list_details_by_name(String list_name,String newName,List<Item> newItems)
    {
        String list_id=getColumnLitsIdByName(list_name);
        SQLiteDatabase db = this.getWritableDatabase();
        if ((checkIfListNameExist(newName)==0) || (list_name.equals(newName)))
        {
            ContentValues values = new ContentValues();
            values.put(COLUMN_LIST_NAME, newName);
            String[] args = new String[]{list_name};
            db.update(TABLE_SHOPLIST, values, COLUMN_LIST_NAME + "=?", args);
            deleteItemsFromTableByListId(list_id);
            addItemsByListId(newItems, list_id);
            db.close();
            return 0;
        }
        else return -1;
    }

    public void deleteItemsFromTableByListId(String list_id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String[] args = new String[]{list_id};
        db.delete(TABLE_ITEMS,"id_list=?",args);
        db.close();
    }

    public void addItemsByListId(List<Item> items,String list_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        for (Item item : items)
        {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID_LIST,list_id);
            values.put(COLUMN_ITEM_NAME, item.getItemName());
            values.put(COLUMN_ITEM_AMOUNT, item.getAmount());
            db.insert(TABLE_ITEMS, null, values);
        }
        db.close();
    }


    public void delete_shoplist(String name_of_list)
    {
        SQLiteDatabase db = getWritableDatabase();
        String list_id=getColumnLitsIdByName(name_of_list);
      //  db.execSQL("DELETE FROM " + TABLE_ITEMS + " WHERE " + COLUMN_ID_LIST + " = '" + list_id + "'");
        db.execSQL("DELETE FROM " + TABLE_SHOPLIST + " WHERE " + COLUMN_LIST_NAME + " = '" + name_of_list + "'");

        db.close();
       /* SQLiteDatabase dbb = getWritableDatabase();
        dbb.execSQL("DELETE FROM " + TABLE_SHOPLIST + " WHERE " + COLUMN_LIST_NAME + " = '" + name_of_list + "'");
*/
       deleteItemsFromTableByListId(list_id);

    }


    public void addItemsToInventory(List<Item> items)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        clearInventoryTable();
        final String MY_QUERY = "SELECT MAX(id_item) FROM " + TABLE_INVENTORY;
        Cursor cur = db.rawQuery(MY_QUERY, null);
        cur.moveToFirst();
        MyId = cur.getInt(0);
        for (Item item : items)
        {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID_ITEM,MyId);
            values.put(COLUMN_NAME_ITEM, item.getItemName());
            values.put(COLUMN_AMOUNT_ITEM, item.getAmount());

            db.insert(TABLE_INVENTORY, null, values);
            MyId =MyId+1;
        }
        cur.close();
        db.close();
    }

    public List<Item> getInventory()
    {
        List <Item> items_list = new ArrayList <Item>();
        SQLiteDatabase db = this.getReadableDatabase();
        String get="SELECT * FROM " + TABLE_INVENTORY;
        Cursor c = db.rawQuery(get,null);

        if (c.moveToFirst()) {
            do {
                Item item = new Item();
                item.set_list_id(c.getInt((c.getColumnIndex(COLUMN_ID_ITEM))));
                item.setItemName(c.getString(c.getColumnIndex(COLUMN_NAME_ITEM)));
                item.setAmount(c.getString(c.getColumnIndex(COLUMN_AMOUNT_ITEM)));
                items_list.add(item);
            } while (c.moveToNext());
        }
        return items_list;
    }

    public void clearInventoryTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INVENTORY,null,null);
    }

    public void addItemFromShoppingList(String item_name,String amount)
    {
        String list_id;
        SQLiteDatabase db = this.getReadableDatabase();
        final String MY_QUERY = "SELECT MAX(id_item) FROM " + TABLE_INVENTORY;
        Cursor cur = db.rawQuery(MY_QUERY, null);
        cur.moveToFirst();
        MyId = cur.getInt(0);
        Cursor c = db.rawQuery("SELECT  * FROM " + TABLE_INVENTORY + " WHERE "
                + COLUMN_NAME_ITEM + " = '" + item_name + "'", null);
        if (!c.moveToFirst()) //item dosent exist in inventory
        {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID_ITEM,MyId+1);
            values.put(COLUMN_NAME_ITEM,item_name);
            values.put(COLUMN_AMOUNT_ITEM,amount);
            db.insert(TABLE_INVENTORY,null,values);
        }
        else //exist
        {

            ContentValues values = new ContentValues();
            int new_amount=(c.getInt((c.getColumnIndex(COLUMN_AMOUNT_ITEM))))+new Integer(amount);
            String new_amount_str=String.valueOf(new_amount);
            values.put(COLUMN_AMOUNT_ITEM,new_amount_str);
            String[] args = new String[]{item_name};
            db.update(TABLE_INVENTORY,values,COLUMN_NAME_ITEM + "=?", args);
        }

        db.close();
    }


    public int checkIfListNameExist(String list_name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT  * FROM " + TABLE_SHOPLIST + " WHERE "
                + COLUMN_LIST_NAME + " = '" + list_name + "'", null);
        if (c.getCount()==0)
            return 0; //can call the list in this name
        else return -1; //list name is occupied
    }

    public void addItemsFromRecipe(List <Ingredient> ingredients,String list_id)
    {
        List <Item> items=new ArrayList <Item>(); //create list of items
        //convert ingredient to item
        for (Ingredient ingredient : ingredients)
        {
            Item item=new Item();
            item.setItemName(ingredient.get_ingredient());
            item.setAmount(ingredient.get_amount());
            items.add(item);
        }

        addItemsByListId(items,list_id); //add items to specific list
    }


}


