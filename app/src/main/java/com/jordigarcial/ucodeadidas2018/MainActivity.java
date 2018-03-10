package com.jordigarcial.ucodeadidas2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void ProductDetail(View view) {
        Intent productDetailActivity = new Intent(this, ProductDetailActivity.class);
        startActivity(productDetailActivity);

    }

    public void ProductList(View view) {
        Intent productListActivity = new Intent(this, ProductListActivity.class);
        startActivity(productListActivity);
    }

    public void Wishlist(View view) {
        Intent wishlistActivity = new Intent(this, WishlistActivity.class);
        startActivity(wishlistActivity);
    }

}
