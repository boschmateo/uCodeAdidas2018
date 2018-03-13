package com.jordigarcial.ucodeadidas2018.adapters;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jordigarcial.ucodeadidas2018.model.Product;
import com.jordigarcial.ucodeadidas2018.activities.ProductDetailActivity;
import com.jordigarcial.ucodeadidas2018.R;

import java.util.List;

/**
 * Adapter Class for Product List.
 *
 * @author Roger Bosch, Jordi García L, Jeroni Molina, Sergi Quevedo
 */
public class ProductListAdapter extends  RecyclerView.Adapter<ProductListAdapter.PersonViewHolder>{

    private List<Product> products;
    private Context context;

    public ProductListAdapter(List<Product> products, Context context){

        this.products=products;
        this.context=context;
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(context, v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder personViewHolder, final int i) {

        personViewHolder.name.setText(products.get(i).getName());
        personViewHolder.price.setText(products.get(i).getPrice() + "€");

        Resources r = context.getResources();
        int drawableId = r.getIdentifier(products.get(i).getId(), "drawable", "com.jordigarcial.ucodeadidas2018");
        //System.out.println("product id: "+products.get(i).getId()+" ID nou: "+drawableId+" ID vell:"+R.drawable.ac7033);
        personViewHolder.image.setImageResource(drawableId);
        personViewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ProductDetailActivity.start(context,products.get(i));
            }
        });

        personViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                System.out.println("LONG CLICK!");
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("favourites");
                ref.child(products.get(i).getId()).setValue(products.get(i).toJson());
                printToast(context, "New item add to favourites!");
                return true;
            }
        });

    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



    public static class PersonViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView name;
        TextView price;
        ImageView image;

        PersonViewHolder(final Context context, View itemView){
            super(itemView);
            cv =  itemView.findViewById(R.id.product_cv);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.imageView);
        }
    }

    public static void printToast(Context ctx, String msg) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(ctx, msg, duration);
        toast.show();
    }

}
