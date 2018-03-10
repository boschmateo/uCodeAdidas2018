package com.jordigarcial.ucodeadidas2018;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by roger on 10/03/2018.
 */

public class Product implements Serializable{

    private String id;
    private String description;
    private String name;
    private float price;
    private Map<Integer, Integer> size;

    public Product(DataSnapshot dataSnapshot){
        size = new HashMap<>();
        id = dataSnapshot.getKey();
        description = (String)dataSnapshot.child("description").getValue();
        name = (String)dataSnapshot.child("name").getValue();
        price = (float)dataSnapshot.child("price").getValue();
        for (DataSnapshot item : dataSnapshot.child("size").getChildren()){
            size.put(Integer.parseInt(item.getKey()), (Integer) item.getValue());
        }
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public Map<Integer, Integer> getSize() {
        return size;
    }
}
