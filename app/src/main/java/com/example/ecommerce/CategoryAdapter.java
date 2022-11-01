package com.example.ecommerce;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    List<CategoryResponse> categoryResponseList = new ArrayList<>();
    private int[] array= {
            R.drawable.apple,R.drawable.flur
    };

    public CategoryAdapter(Context context, List categoryResponseList) {
        this.context = context;
        this.categoryResponseList = categoryResponseList;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_view, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder myViewHolder, @SuppressLint("RecyclerView") int position)
    {
        try {
            myViewHolder.cat_title.setText(""+categoryResponseList.get(position).getCatName());
            try{
                Log.e("path",""+categoryResponseList.get(position).getCatImage());
                String path="https://webuslabs.com/WebusGrocery/Admin/"+categoryResponseList.get(position).getCatImage();
                Picasso.with(context)
                        .load("https://webuslabs.com/WebusGrocery/Admin/"+categoryResponseList.get(position).getCatImage())
                         .into(myViewHolder.cat_img);

            }catch(Exception e){
                e.printStackTrace();
            }
            myViewHolder.cat_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cat_product cat_product=new Cat_product();
                    Bundle bundle=new Bundle();
                    bundle.putString("catId",categoryResponseList.get(position).getCatId());
                    bundle.putString("catname",categoryResponseList.get(position).getCatName());
                    cat_product.setArguments(bundle);
                    ((MainActivity)context).loadFragment(cat_product,true);
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return categoryResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cat_img;
        TextView cat_title;
        CardView cat_layout;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            cat_img=itemView.findViewById(R.id.categoryImg);
            cat_title=itemView.findViewById(R.id.categoryTitle);
            cat_layout=itemView.findViewById(R.id.category_layout);

        }
    }
}