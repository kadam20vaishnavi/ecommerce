package com.example.ecommerce;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder>
{
    Context context;
    List<MyCartResponse> myCartResponseList = new ArrayList<>();

    public MyCartAdapter(Context context, List myCartResponseList) {
        this.context = context;
        this.myCartResponseList = myCartResponseList;
    }

    public MyCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_view, viewGroup, false);
        return new MyCartAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder myViewHolder, @SuppressLint("RecyclerView") int position) {

        try {

            if (position == myCartResponseList.size()-1) {

                myViewHolder.product_bill.setVisibility(View.VISIBLE);
                myViewHolder.sub_tot.setText(""+myCartResponseList.get(position).getSubTotalAmount());
                myViewHolder.deli_charge.setText(""+myCartResponseList.get(position).getDeliveryCharge());
                myViewHolder.total_amount.setText(""+myCartResponseList.get(position).getTotalAmount());

                Checkout cat_product=new Checkout();
                Bundle bundle=new Bundle();
                bundle.putInt("amount",myCartResponseList.get(position).getTotalAmount());
                cat_product.setArguments(bundle);
            } else
                myViewHolder.product_bill.setVisibility(View.GONE);

            myViewHolder.product_name.setText(""+myCartResponseList.get(position).getProductName());
            myViewHolder.mrp.setText(""+myCartResponseList.get(position).getProductMrp());
            myViewHolder.count.setText(""+myCartResponseList.get(position).getProductQuantity());
            myViewHolder.item_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(myViewHolder.count.getText().toString().length()>0){

                        Float qty= Float.parseFloat(myViewHolder.count.getText().toString())+1;
                        myViewHolder.count.setText(""+qty);
                        //delete
                        Call<CartResponse> call=Api.getApi().addToCart("1", ""+myCartResponseList.get(position).getProductId(), ""+myViewHolder.count.getText().toString());
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

            myViewHolder.item_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(myViewHolder.count.getText().toString().length()>0){

                        if(Float.parseFloat(myViewHolder.count.getText().toString()) > 0) {

                            if(Float.parseFloat(myViewHolder.count.getText().toString())==1){
                                myViewHolder.count.setText("0");
                                //delete
                                Call<Loginresponce> call=Api.getApi().deleteCart("1", myCartResponseList.get(position).getProductId());
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

                                Float qty = Float.parseFloat(myViewHolder.count.getText().toString()) - 1;
                                myViewHolder.count.setText("" + qty);
                                //update
                                Call<Loginresponce> call=Api.getApi().updateCart("1",myCartResponseList.get(position).getProductId(),""+myViewHolder.count.getText().toString());
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


            try{
                Picasso.with(context)
                        .load("https://webuslabs.com/WebusGrocery/Admin/uploads/product/"+myCartResponseList.get(position).getProductPhoto())
                        .into(myViewHolder.product_image);
            }catch(Exception e){
                e.printStackTrace();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return myCartResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView product_name,mrp,count;
        ImageView product_image;
        LinearLayout product_bill;
        TextView sub_tot,deli_charge,total_amount;
        ImageView item_plus,item_minus;
        TextView checkout;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
           product_name=itemView.findViewById(R.id.product_name);
           mrp=itemView.findViewById(R.id.MRP);
           count=itemView.findViewById(R.id.count);
           product_image=itemView.findViewById(R.id.product_img);
           product_bill=itemView.findViewById(R.id.product_bill);
           sub_tot=itemView.findViewById(R.id.sub_tot);
           deli_charge=itemView.findViewById(R.id.deli_charges);
           total_amount=itemView.findViewById(R.id.tot_amount);
           item_plus=itemView.findViewById(R.id.item_plus);
           item_minus=itemView.findViewById(R.id.item_minus);
            checkout=itemView.findViewById(R.id.checkout);
        }
    }

}
