package Adapters;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jordigarcial.ucodeadidas2018.Product;
import com.jordigarcial.ucodeadidas2018.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergi on 10/03/2018.
 */

public class ProductListAdapter extends  RecyclerView.Adapter<ProductListAdapter.PersonViewHolder>{

    List<Product> products;
    Context context;

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
        PersonViewHolder pvh = new PersonViewHolder(v);
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

        personViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                System.out.println("LONG CLICK!");
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("favourites");
                ref.child(products.get(i).getId()).setValue(products.get(i).toJson());
                //ref.child(products.get(i).getId()).push(products.get(i));
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

        PersonViewHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.product_cv);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            image = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }

}
