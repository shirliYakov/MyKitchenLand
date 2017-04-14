package com.example.shirl1.mykitchenland;



public class Recipes {

    int id;
    String recipename;

    public Recipes() {

    }

    public Recipes(String recipename) {
        this.recipename = recipename;
    }

    public Recipes(int id, String recipename) {
        this.id = id;
        this.recipename = recipename;
    }

    public void set_id(int id){
        this.id = id;
    }

    public void set_recipename(String recipename){
        this.recipename = recipename;
    }

    public int get_id(){
        return this.id;
    }

    public String get_recipename(){
        return this.recipename;
    }

}
