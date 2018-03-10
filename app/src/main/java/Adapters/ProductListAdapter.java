package Adapters;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.jordigarcial.ucodeadidas2018.Product;
import com.jordigarcial.ucodeadidas2018.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergi on 10/03/2018.
 */

public class ProductListAdapter extends  RecyclerView.Adapter<ProductListAdapter.PersonViewHolder> {

    List<Product> products;
    Context context;

    public ProductListAdapter(List<Product> products, Context context){
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
        final String path = "res/drawable/" + products.get(i).getId() +".jpg";
        personViewHolder.name.setText(products.get(i).getName());
        personViewHolder.price.setText(products.get(i).getPrice()+"€");
        islandref = storageRef.child();

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView name;
        TextView price;
        ImageView image;

        PersonViewHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.product_cv);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            image = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }

}
