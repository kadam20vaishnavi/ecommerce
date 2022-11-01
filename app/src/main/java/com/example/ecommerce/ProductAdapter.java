package com.example.ecommerce;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

        Context context;
        List<ProductResponse> productResponseList = new ArrayList<>();
        String id;

    public ProductAdapter(Context context, List productResponseList) {
            this.context = context;
            this.productResponseList = productResponseList;
        }

        public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_view, viewGroup, false);
            SharedPreferences info= context.getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
            id=info.getString("userId","");
            return new ProductAdapter.ViewHolder(itemView);
        }


    @Override
        public void onBindViewHolder(@NonNull ViewHolder myViewHolder, @SuppressLint("RecyclerView") int position) {

            try {

                try{
                    String path="http://ct/"+productResponseList.get(position).getProductPhoto();
                    Picasso.with(context)
                            .load("https://webuslabs.com/WebusGrocery/Admin/uploads/product/"+productResponseList.get(position).getProductPhoto())
                            .into(myViewHolder.product_img);
                    Log.e("imagepath",path);
                }catch(Exception e){
                    e.printStackTrace();
                }
                myViewHolder.product_title.setText(""+productResponseList.get(position).getProductName());
                myViewHolder.product_mrp.setText(""+productResponseList.get(position).getProductMrp());
                myViewHolder.product_sellprice.setText(""+productResponseList.get(position).getProductSellPrice());
                myViewHolder.quatity.setText(""+productResponseList.get(position).getQty());

                myViewHolder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(myViewHolder.quatity.getText().toString().length()>0){

                            Float qty= Float.parseFloat(myViewHolder.quatity.getText().toString())+1;
                            myViewHolder.quatity.setText(""+qty);
                            //delete

                            Call<CartResponse> call=Api.getApi().addToCart(""+id, ""+productResponseList.get(position).getProductId(), ""+myViewHolder.quatity.getText().toString());
                            call.enqueue(new Callback<CartResponse>() {
                                @Override
                                public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                                    if(response.isSuccessful()){
                                        if(response.body().getSuccess().equalsIgnoreCase("true"))
                                        {
                                            Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                            notifyDataSetChanged();
                                        }
                                        else{
                                            Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                        }
                                    }

                                }

                                @Override
                                public void onFailure(Call<CartResponse> call, Throwable t) {

                                }
                            });

                        }else{
                            Toast.makeText(context,"No product into cart",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                myViewHolder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(myViewHolder.quatity.getText().toString().length()>0){

                            if(myViewHolder.quatity.getText().toString().length()>0) {

                                if(Float.parseFloat(myViewHolder.quatity.getText().toString())==1){
                                    myViewHolder.quatity.setText("0");
                                    //delete
                                    Call<Loginresponce> call=Api.getApi().deleteCart(""+id, productResponseList.get(position).getProductId());
                                    call.enqueue(new Callback<Loginresponce>() {
                                        @Override
                                        public void onResponse(Call<Loginresponce> call, Response<Loginresponce> response) {
                                            if(response.isSuccessful()){
                                                if(response.body().getSuccess().equalsIgnoreCase("true"))
                                                {
                                                    Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                    notifyDataSetChanged();
                                                }
                                                else{
                                                    Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                        }

                                        @Override
                                        public void onFailure(Call<Loginresponce> call, Throwable t) {

                                        }
                                    });
                                }
                                else{

                                Float qty = Float.parseFloat(myViewHolder.quatity.getText().toString()) - 1;
                                myViewHolder.quatity.setText("" + qty);
                                //update
                                    Call<Loginresponce> call=Api.getApi().updateCart(""+id,productResponseList.get(position).getProductId(),""+myViewHolder.quatity.getText().toString());
                                    call.enqueue(new Callback<Loginresponce>() {
                                        @Override
                                        public void onResponse(Call<Loginresponce> call, Response<Loginresponce> response) {
                                            if(response.isSuccessful()){

                                                if(response.body().getSuccess().equalsIgnoreCase("true"))
                                                {
                                                    Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                    notifyDataSetChanged();
                                                }
                                                else{
                                                    Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Loginresponce> call, Throwable t) {

                                        }
                                    });
                                }

                            }
                            else{

                                Toast.makeText(context,"No product into cart",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(context,"No product into cart",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public int getItemCount() {
            return productResponseList.size();
        }

    public void filterList(ArrayList<ProductResponse> filterdlist) {
        productResponseList=filterdlist;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView product_img;
            TextView product_title,product_sellprice,product_mrp;
            ImageView add,delete;
            TextView quatity;

            public ViewHolder(@NonNull View itemView) {

                super(itemView);
                product_img=itemView.findViewById(R.id.product_img);
                product_title=itemView.findViewById(R.id.product_name);
                product_sellprice=itemView.findViewById(R.id.sell_price);
                product_mrp=itemView.findViewById(R.id.MRP);
                add=itemView.findViewById(R.id.item_plus);
                delete=itemView.findViewById(R.id.item_minus);
                quatity=itemView.findViewById(R.id.count);

            }
        }

    }
