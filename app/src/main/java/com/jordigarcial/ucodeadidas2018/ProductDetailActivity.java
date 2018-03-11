package com.jordigarcial.ucodeadidas2018;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.tech.Ndef;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Map.Entry;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView tv_product_id;
    private TextView tv_product_name;
    private TextView tv_product_desc;
    private TextView tv_product_price;
    private ImageView tv_product_image;
    private TextView tv_product_size;

    private DatabaseReference ref;

    private PendingIntent pendingIntent;
    private IntentFilter[] intentFiltersArray;
    private String[][] techListsArray;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        printToast(this, "New intent");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        findLayoutViews(this);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference("products");

        /////////////////////////////
        // ???
        pendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        try {
            ndef.addDataType("text/plain");    /* Handles plain text dispatches. */
        }
        catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }

        intentFiltersArray = new IntentFilter[] {ndef, };
        techListsArray = new String[][] { new String[] { Ndef.class.getName() } };
        /////////////////////////////
    }

    @Override
    protected void onPause() {
        super.onPause();
        NfcAdapter.getDefaultAdapter(this).disableForegroundDispatch(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NfcAdapter.getDefaultAdapter(this).enableForegroundDispatch(this, pendingIntent, intentFiltersArray, null);

        if (activityWasLaunchedByNFC()){
            String productId = getTagString();
            getProductWithId(productId, ref);
        }
        else {
            Product product = getProductFromIntent();
            populateViewsWithProduct(product);
        }
    }

    private void getProductWithId(String productId, DatabaseReference ref) {

        ref.child(productId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Product p = new Product(dataSnapshot);
                populateViewsWithProduct(p);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
        tv_product_image = act.findViewById(R.id.tv_product_image);
        tv_product_size = act.findViewById(R.id.tv_product_size);
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
        Resources r = getResources();
        int drawableId = r.getIdentifier(product.getId(), "drawable", "com.jordigarcial.ucodeadidas2018");

        tv_product_image.setImageResource(drawableId);
        tv_product_id.setText(product.getId());
        tv_product_name.setText(product.getName());
        tv_product_desc.setText(product.getDescription());
        tv_product_price.setText(product.getPrice()+"€");
        /*String sizes="";
        for (Map.Entry<String,String> value : product.getSize().entrySet()){
            sizes = sizes + " " +value.getKey()+ ": " +value.ge
        }
        tv_product_size.setText(product.getSize().);
        */
    }

}
