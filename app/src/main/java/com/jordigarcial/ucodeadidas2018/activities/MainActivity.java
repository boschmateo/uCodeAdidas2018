package com.jordigarcial.ucodeadidas2018.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jordigarcial.ucodeadidas2018.model.Product;
import com.jordigarcial.ucodeadidas2018.R;

import java.util.ArrayList;

import com.jordigarcial.ucodeadidas2018.adapters.ProductListAdapter;

/**
 * Main activity.
 * Retrieves and displays a list of products from a Firebase database.
 *
 * @author Roger Bosch, Jordi García L, Jeroni Molina, Sergi Quevedo
 */
public class MainActivity extends AppCompatActivity {


    ArrayList<Product> productsList = new ArrayList<Product>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("products");

        final ProductListAdapter adapter = new ProductListAdapter(productsList, getApplicationContext());
        rv.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()){
                    Product product = new Product(item);
                    System.out.println("Product added: "+product.getName());
                    productsList.add(product);
                }
                adapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if(item.getItemId() == R.id.favourites) {
            start(this, FavouriteProductListActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }

    public static void start(Context context, Class<?> activity) {
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }

}
