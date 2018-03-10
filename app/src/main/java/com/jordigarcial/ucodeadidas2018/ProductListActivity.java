package com.jordigarcial.ucodeadidas2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adapters.ProductListAdapter;

public class ProductListActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Product> productsList = new ArrayList<Product>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("products");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()){
                    Product product = new Product(item);
                    System.out.println("Product added: "+product.getName());
                    productsList.add(product);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView=findViewById(R.id.listView);
        ProductListAdapter adapter = ProductListAdapter.create(ProductListActivity.this, R.id.listView, productsList);
        listView.setAdapter(adapter);

    }
}
