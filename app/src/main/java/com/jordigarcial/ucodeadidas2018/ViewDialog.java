package com.jordigarcial.ucodeadidas2018;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.TextView;

import java.util.Map;

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