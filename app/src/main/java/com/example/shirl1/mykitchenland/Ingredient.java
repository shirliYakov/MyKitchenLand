package com.example.shirl1.mykitchenland;

public class Ingredient {

    int id;
    String amount;
    String ingredient;

    public Ingredient() {

    }

    public Ingredient(String amount, String ingredient) {
        this.amount = amount;
        this.ingredient = ingredient;
    }

    public Ingredient(int id, String amount, String ingredient) {
        this.id = id;
        this.amount = amount;
        this.ingredient = ingredient;
    }

    public void set_id(int id){
        this.id = id;
    }

    public void set_amount(String amount){
        this.amount = amount;
    }

    public void set_ingredient(String ingredient){
        this.ingredient = ingredient;
    }

    public int get_id(){
        return this.id;
    }

    public String get_amount(){
        return this.amount;
    }

    public String get_ingredient(){
        return this.ingredient;
    }


}
