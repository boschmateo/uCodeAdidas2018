package Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.jordigarcial.ucodeadidas2018.Product;
import com.jordigarcial.ucodeadidas2018.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergi on 10/03/2018.
 */

public class ProductListAdapter extends  RecyclerView.Adapter<ProductListAdapter.PersonViewHolder> {

    List<Product> products;

    public ProductListAdapter(List<Product> products){
        this.products=products;
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.name.setText(products.get(i).getName());
        personViewHolder.price.setText(products.get(i).getPrice()+"€");
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView name;
        TextView price;

        PersonViewHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.product_cv);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
        }
    }

}
