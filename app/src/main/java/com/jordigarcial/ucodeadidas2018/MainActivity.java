package com.jordigarcial.ucodeadidas2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void ProductDetail(View view) {
        Product product = new Product.ProductBuilder()
                .setId("0")
                .setName("Yeezy Boost")
                .setDesc("Kanye's greatest yeezys yet!!")
                .setPrice(100.0f)
                .build();

        ProductDetailActivity.start(this, product);
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
