package Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jordigarcial.ucodeadidas2018.R;

/**
 * Created by sergi on 10/03/2018.
 */

public class ProductListAdapter {

    protected Activity activity;
    public View getView(int position, View convertView, ViewGroup parent) {
        
        if(convertView == null) {
            LayoutInflater li = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.product,parent,false);
        }
        
        return convertView;
    }

    public DataSnapshot hola() {

    }
}
