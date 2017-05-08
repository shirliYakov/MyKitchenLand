package com.example.shirl1.mykitchenland;

public class Recipes {

    int id;
    String recipename;
    String recipeinstructions;
    byte[] image;
    String time;

    public Recipes() {

    }

    public Recipes(String recipename, String recipeinstructions) {
        this.recipename = recipename;
        this.recipeinstructions = recipeinstructions;
    }

    public Recipes(String recipename, String recipeinstructions, String time) {
        this.recipename = recipename;
        this.recipeinstructions = recipeinstructions;
        this.time = time;
    }

    public Recipes(String recipename, String recipeinstructions, byte[] image, String time) {

        this.recipename = recipename;
        this.recipeinstructions = recipeinstructions;
        this.image = image;
        this.time = time;
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

    public String get_recipeinstructions() {
        return this.recipeinstructions;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String toString() {
        return this.id + ". " + this.recipename +  this.recipeinstructions ;
    }
}
