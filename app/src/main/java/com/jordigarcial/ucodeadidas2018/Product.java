package com.jordigarcial.ucodeadidas2018;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.DataSnapshot;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Jordi García Lissón, Roger Bosch, Jeroni Molina, Sergi Quevedo
 */
public class Product implements Serializable {

    private String id;
    private String description;
    private String name;
    private float price;
    private Map<String, String> size;
    private String location;

    public Product(String id, String description, String name, float price, String location) {
        this.location = location;
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;

    }

    public Product(DataSnapshot dataSnapshot) {
        System.out.print(dataSnapshot.getKey());
        size = new HashMap<>();
        id = dataSnapshot.getKey();
        location = (String) dataSnapshot.child("location").getValue();
        description = (String) dataSnapshot.child("description").getValue();
        name = (String) dataSnapshot.child("name").getValue();
        price = Float.parseFloat("" + dataSnapshot.child("price").getValue());
        for (DataSnapshot item : dataSnapshot.child("size").getChildren()) {
            size.put(item.getKey(), (String) item.getValue());
        }
    }

    public Map toJson() {
        Map mapPoints = new HashMap();
        mapPoints.put("description", description);
        mapPoints.put("name", name);
        mapPoints.put("price", price + "");
        mapPoints.put("size", size);

        return mapPoints;
    }

    public void setSize(Map<String, String> size) {
        this.size = size;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Map<String, String> getSize() {
        return size;
    }


}
