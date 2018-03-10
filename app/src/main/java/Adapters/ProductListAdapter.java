package Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.jordigarcial.ucodeadidas2018.Product;
import com.jordigarcial.ucodeadidas2018.R;

import java.util.ArrayList;

/**
 * Created by sergi on 10/03/2018.
 */

public class ProductListAdapter extends ArrayAdapter<Product> {

    ArrayList<Product> products;
    TextView name;
    TextView price;

    public ProductListAdapter(Context context, ArrayList<Product> product) {
        super(context,0,product);
        this.products = product;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Product product = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product,parent,false);
        }

        //Get the two textfields information
        name = (TextView) convertView.findViewById(R.id.name);
        price = (TextView) convertView.findViewById(R.id.price);
        name.setText(product.getName());
        price.setText(product.getPrice()+"");
        
        return convertView;
    }

}
