package com.jordigarcial.ucodeadidas2018;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by roger on 10/03/2018.
 */

public class Product implements Serializable {

    private String id;
    private String description;
    private String name;
    private float price;
    private Map<String, String> size;

    public Product(String id, String description, String name, float price) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;

    }

    public Product(DataSnapshot dataSnapshot){
        System.out.print(dataSnapshot.getKey());
        size = new HashMap<>();
        id = dataSnapshot.getKey();
        description = (String)dataSnapshot.child("description").getValue();
        name = (String)dataSnapshot.child("name").getValue();
        price = Float.parseFloat(""+dataSnapshot.child("price").getValue());
        for (DataSnapshot item : dataSnapshot.child("size").getChildren()){
            size.put(item.getKey(), (String) item.getValue());
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


    public static class ProductBuilder {

        private String id;
        private String description;
        private String name;
        private float price;

        public ProductBuilder() {
        }

        public ProductBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public ProductBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder setDesc(String desc) {
            this.description = desc;
            return this;
        }

        public ProductBuilder setPrice(float price) {
            this.price = price;
            return this;
        }

        public Product build() {
            return new Product(id, name, description, price);
        }
    }
}
