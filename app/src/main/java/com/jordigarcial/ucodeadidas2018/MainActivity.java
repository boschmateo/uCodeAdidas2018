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

    public void onProductDetail(View view) {
        Intent productDetailActivity = new Intent(this, ProductDetailActivity.class);
        startActivity(productDetailActivity);

    }

    public void onProductList(View view) {
        Intent productListActivity = new Intent(this, ProductListActivity.class);
        startActivity(productListActivity);
    }

    public void onWishlist(View view) {
        Intent wishlistActivity = new Intent(this, WishlistActivity.class);
        startActivity(wishlistActivity);
    }

}
