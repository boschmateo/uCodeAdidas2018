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

    private ProductListAdapter(Context context, int resourceId, ArrayList<Product> product) {
        super(context, resourceId, product);
        this.products = product;
    }

    public static ProductListAdapter create(Context context, int resourceId, ArrayList<Product> product){
        return new ProductListAdapter(context, resourceId, product);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View principleItemView = convertView;

        if (principleItemView==null){
            principleItemView = inflatePrincipaleItemView(parent);
        }
        Product product = products.get(position);

        TextView name = (TextView) principleItemView.findViewById(R.id.name);
        name.setText(product.getName());
        TextView price = (TextView) principleItemView.findViewById(R.id.price);
        price.setText(product.getPrice()+"");


        return principleItemView;
    }

    private View inflatePrincipaleItemView(ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.product, null);
    }

    private void fillProductItemView(Product product){

    }


}
