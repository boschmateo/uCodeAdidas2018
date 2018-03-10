package com.jordigarcial.ucodeadidas2018;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Get product
        Product product = (Product) getIntent().getSerializableExtra("product");


        // Get Views
        TextView tv_product_id = (TextView) findViewById(R.id.tv_product_id);
        TextView tv_product_name = (TextView) findViewById(R.id.tv_product_name);
        TextView tv_product_desc = (TextView) findViewById(R.id.tv_product_desc);
        TextView tv_product_price = (TextView) findViewById(R.id.tv_product_price);
        TextView tv_product_size = (TextView) findViewById(R.id.tv_product_size);

        // Populate views with product info
        tv_product_id.setText(product.getId());
        tv_product_name.setText(product.getName());
        tv_product_desc.setText(product.getDescription());
        tv_product_price.setText(String.format("%f", product.getPrice()));
        // TODO: size

    }

    public static void start(Context context, Product product) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra("product", product);
        context.startActivity(intent);
    }



}
