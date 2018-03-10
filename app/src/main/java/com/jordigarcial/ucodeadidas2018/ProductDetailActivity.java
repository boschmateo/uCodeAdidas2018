package com.jordigarcial.ucodeadidas2018;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView tv_product_id;
    private TextView tv_product_name;
    private TextView tv_product_desc;
    private TextView tv_product_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        findLayoutViews(this);

        Product product;
        if (activityWasLaunchedByNFC()){
            String productId = getTagString();
            product = getProductWithId(productId); // TODO: RU
        }
        else product = getProductFromIntent();

        populateViewsWithProduct(product);

    }

    /**
     * Class starter
     * @param context Context
     * @param product Product to display details
     */
    public static void start(Context context, Product product) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra("product", product);
        context.startActivity(intent);
    }

    /**
     * Helper method.
     * Finds all necessary ids for this class.
     * @param act Activity instance
     */
    private void findLayoutViews(Activity act) {
        tv_product_id = act.findViewById(R.id.tv_product_id);
        tv_product_name = act.findViewById(R.id.tv_product_name);
        tv_product_desc = act.findViewById(R.id.tv_product_desc);
        tv_product_price = act.findViewById(R.id.tv_product_price);
    }

    public static void printToast(Context ctx, String msg) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(ctx, msg, duration);
        toast.show();
    }

    /**
     * Helper method.
     * Checks whether this activity was launched by the discovery of a NFC tag
     * @return whether this activity was launched by the discovery of a NFC tag
     */
    private boolean activityWasLaunchedByNFC() {
        return (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction()));
    }


    /**
     * Helper method.
     * Returns the string contained in the discovered NFC tag
     * @return the string contained in the discovered NFC tag
     */
    private String getTagString() {
        Parcelable[] rawMessages = getIntent().getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        NdefMessage message = (NdefMessage) rawMessages[0];
        NdefRecord[] records = message.getRecords();
        String msg = new String(records[0].getPayload());
        msg = msg.substring(3);

        return msg;
    }

    /**
     * Helper method.
     * Returns the product to display in this activity.
     * @return the product to display in this activity.
     */
    private Product getProductFromIntent() {
        return (Product) getIntent().getSerializableExtra("product");
    }

    /**
     * Helper method.
     * Populates all views of the layout with the corresponding product info.
     * @param product The product with which populate the layout.
     */
    private void populateViewsWithProduct(Product product) {
        tv_product_id.setText(product.getId());
        tv_product_name.setText(product.getName());
        tv_product_desc.setText(product.getDescription());
        tv_product_price.setText(String.format("%f", product.getPrice()));
    }

}
