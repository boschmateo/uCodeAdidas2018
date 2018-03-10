package com.jordigarcial.ucodeadidas2018;

import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Get Views
        TextView tv_product_id = (TextView) findViewById(R.id.tv_product_id);
        TextView tv_product_name = (TextView) findViewById(R.id.tv_product_name);
        TextView tv_product_desc = (TextView) findViewById(R.id.tv_product_desc);
        TextView tv_product_price = (TextView) findViewById(R.id.tv_product_price);
        TextView tv_product_size = (TextView) findViewById(R.id.tv_product_size);

        // Discover if we were launched by NFC
        Intent intent = getIntent();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())){
            printToast(this, "NFC detected!!");
            Parcelable[] rawMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage message = (NdefMessage) rawMessages[0];
            NdefRecord[] records = message.getRecords();
            String msg = new String(records[0].getPayload());


            tv_product_id.setText(msg);
        }
        else {
            printToast(this, "onCreate()...");

            // Get product
            Product product = (Product) getIntent().getSerializableExtra("product");




            // Populate views with product info
            tv_product_id.setText(product.getId());
            tv_product_name.setText(product.getName());
            tv_product_desc.setText(product.getDescription());
            tv_product_price.setText(String.format("%f", product.getPrice()));
            // TODO: size
        }



    }

    public static void start(Context context, Product product) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra("product", product);
        context.startActivity(intent);
    }

    public static void printToast(Context ctx, String msg) {
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(ctx, msg, duration);
        toast.show();
    }



}
