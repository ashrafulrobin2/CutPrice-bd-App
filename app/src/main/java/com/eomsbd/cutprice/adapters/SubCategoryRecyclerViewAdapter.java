package com.eomsbd.cutprice.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eomsbd.cutprice.ProductActivity;
import com.eomsbd.cutprice.R;
import com.eomsbd.cutprice.fragment.SubCategoryFragment;
import com.eomsbd.cutprice.model.products_model.Datum;
import com.eomsbd.cutprice.model.sub_category.Datum2;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class SubCategoryRecyclerViewAdapter extends RecyclerView.Adapter<SubCategoryRecyclerViewHolder> {
    private Context context;
    private List<Datum2> data1;

    public SubCategoryRecyclerViewAdapter(Context context, List<Datum2> data1) {
        this.context = context;
        this.data1 = data1;
    }

    @NonNull
    @Override
    public SubCategoryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.subcategory_list, viewGroup, false);
        SubCategoryRecyclerViewHolder productHolder = new SubCategoryRecyclerViewHolder(layoutView);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryRecyclerViewHolder holder, int position) {
        String path = "http://cutpricebd.com/oms/product_image/thumbs/";
        final Datum2 singleProduct = data1.get(position);

        if (singleProduct == null) {

           /* Fragment fragment = new SubCategoryFragment();
            FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame_container, fragment);
            ft.commit();*/
            Toasty.success(context, "Success", Toasty.LENGTH_LONG).show();

        } else {

            if ((path + singleProduct.getImg1()).length() <= 60) {
                Picasso.get().load(path + singleProduct.getImg1()).into(holder.imageView);
            } else {
                Picasso.get().load(singleProduct.getImg1()).into(holder.imageView);
            }

            holder.textView.setText(singleProduct.getProductName());

        }


        holder.imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent productIntent = new Intent(context, ProductActivity.class);

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                String stringObjectRepresentation = gson.toJson(singleProduct);

                productIntent.putExtra("PRODUCT", stringObjectRepresentation);
                context.startActivity(productIntent);
            }
        });
    }

    @Override
    public int getItemCount() {

        // return (null == data1 ? data1.size() : 10);
        return data1.size();
    }
}
