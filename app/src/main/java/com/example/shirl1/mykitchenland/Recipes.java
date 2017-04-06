package com.example.shirl1.mykitchenland;



public class Recipes {

    private int id;
    private String recipename;

    public Recipes() {
        super();
    }

    public Recipes(String recipename) {
        super();
        this.recipename = recipename;
    }

    public Recipes(int id, String recipename) {
        super();
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
        return id;
    }

    public String get_recipename(){
        return recipename;
    }

}
