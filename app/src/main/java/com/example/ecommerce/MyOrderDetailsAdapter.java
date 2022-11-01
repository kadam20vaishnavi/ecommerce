package com.example.ecommerce;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyOrderDetailsAdapter extends RecyclerView.Adapter<MyOrderDetailsAdapter.MyViewHolder> {

    Context context;
    List<FinalOrderDetailResponse> finalOrderResponseList =new ArrayList<>();

    public MyOrderDetailsAdapter(Context context, List<FinalOrderDetailResponse> orderResponseList) {
        this.context=context;
        this.finalOrderResponseList =orderResponseList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.orderlist_detailsview,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.orderno.setText(finalOrderResponseList.get(position).getOrderNumber());
        holder.price.setText(finalOrderResponseList.get(position).getProductPrice());
        holder.productname.setText(finalOrderResponseList.get(position).getProductName());
        holder.deliverydate.setText(finalOrderResponseList.get(position).getDeliveryDate());
       // holder.orderstatus.setText(finalOrderResponseList.get(position).getFinalOrderStatus());

        try{
            String path="https://webuslabs.com/WebusGrocery/Admin/"+finalOrderResponseList.get(position).getProductPhoto();
            Log.e("Final_path",path);
            Picasso.with(context)
                    .load("https://webuslabs.com/WebusGrocery/Admin/"+finalOrderResponseList.get(position).getProductPhoto())
                    .into(holder.productimage);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return finalOrderResponseList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView orderno,price,productname,deliverydate,orderstatus;
        ImageView productimage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            orderno=itemView.findViewById(R.id.orderdetailno);
            price=itemView.findViewById(R.id.productprice);
            productname=itemView.findViewById(R.id.productdetailname);
            deliverydate=itemView.findViewById(R.id.dateofdelivery);
            orderstatus=itemView.findViewById(R.id.orderstatus);
            productimage=itemView.findViewById(R.id.productimage);
        }
    }
}
