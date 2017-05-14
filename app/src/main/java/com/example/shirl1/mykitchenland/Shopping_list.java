package com.example.shirl1.mykitchenland;

/**
 * Created by shirl on 24/04/2017.
 */


public class Shopping_list {

    String list_id;
    String list_name;

    public Shopping_list() {

    }

    public Shopping_list(String list_name) {
        this.list_name = list_name;
    }

    public Shopping_list(String id, String list_name) {
        this.list_id = id;
        this.list_name = list_name;
    }

 /*   public void set_list_id(int id){
        this.list_id = id;
    }*/

    public void set_listname(String list_name){
        this.list_name = list_name;
    }

    public void set_listId(String list_id){
        this.list_id = list_id;
    }


    public String get_list_id(){
        return this.list_id;
    }

    public String get_listname(){
        return this.list_name;
    }


}

