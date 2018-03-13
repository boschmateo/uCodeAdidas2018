package com.jordigarcial.ucodeadidas2018.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jordigarcial.ucodeadidas2018.R;
import com.jordigarcial.ucodeadidas2018.model.Product;

import java.util.ArrayList;

import com.jordigarcial.ucodeadidas2018.adapters.ProductListAdapter;

/**
 * Wishlist Activity
 * It shows the subset of products that the user has set as favorite.
 *
 * @author Roger Bosch, Jordi García L, Jeroni Molina, Sergi Quevedo
 */
public class FavouriteProductListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Product> favouriteList = new ArrayList<Product>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("favourites");

        final ProductListAdapter favAdapter = new ProductListAdapter(favouriteList, getApplicationContext());
        rv.setAdapter(favAdapter);


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()){
                    Product product = new Product(item);
                    System.out.println("Product added: "+product.getName());
                    favouriteList.add(product);
                }
                favAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
