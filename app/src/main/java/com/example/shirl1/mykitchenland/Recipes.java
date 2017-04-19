package com.example.shirl1.mykitchenland;

public class Recipes {

    int id;
    String recipename;
    String recipeinstructions;

    public Recipes() {

    }

    public Recipes(String recipename, String recipeinstructions) {
        this.recipename = recipename;
        this.recipeinstructions = recipeinstructions;
    }

    public Recipes(int id, String recipename, String recipeinstructions) {
        this.id = id;
        this.recipename = recipename;
        this.recipeinstructions = recipeinstructions;
    }

    public void set_id(int id){
        this.id = id;
    }

    public void set_recipename(String recipename){
        this.recipename = recipename;
    }

    public void set_recipeinstructions(String recipeinstructions){
        this.recipeinstructions = recipeinstructions;
    }

    public int get_id(){
        return this.id;
    }

    public String get_recipename(){
        return this.recipename;
    }

    public String get_recipeinstructions(){
        return this.recipeinstructions;
    }

}
