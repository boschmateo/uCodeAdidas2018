package com.jordigarcial.ucodeadidas2018.ui;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.TextView;

import com.jordigarcial.ucodeadidas2018.R;
import com.jordigarcial.ucodeadidas2018.model.Product;

import java.util.Map;

/**
 * View Dialog Class.
 * Implements a ViewDialog that is shown to display local information about a product.
 *
 * @author Roger Bosch, Jordi García L, Jeroni Molina, Sergi Quevedo
 */
public class ViewDialog {

    Product product;

    public ViewDialog(Product product){
        this.product=product;
    }

    public void showDialog(Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.product_found_dialog);

        TextView sizes = (TextView) dialog.findViewById(R.id.all_sizes);
        TextView location = (TextView) dialog.findViewById(R.id.location);
        String sizesString="";
        for (Map.Entry<String,String> value : product.getSize().entrySet()){
            sizesString = sizesString + " " +value.getKey()+ ": "+value.getValue()+"u.   ";
        }
        sizes.setText(sizesString);
        location.setText(product.getLocation());

        dialog.show();

    }
}