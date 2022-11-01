package com.example.ecommerce;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyViewHolder> {

    Context context;
    List<Orderlistresponce> finalOrderDetailResponseList =new ArrayList<>();

    public MyOrderAdapter(Context context, List<Orderlistresponce> orderResponseList) {
        this.context=context;
        this.finalOrderDetailResponseList =orderResponseList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_view,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.orderno.setText(finalOrderDetailResponseList.get(position).getOrderNumber());
        holder.payment.setText(finalOrderDetailResponseList.get(position).getGrandAmount());

        holder.dateofdelivery.setText(finalOrderDetailResponseList.get(position).getOrderDate());

        holder.trackstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                TrackStatusOrder trackStatusOrder=new TrackStatusOrder();
                Bundle args = new Bundle();
                Log.e("orderpk",finalOrderDetailResponseList.get(position).getFinalOrderPk());
                args.putString("order_id", finalOrderDetailResponseList.get(position).getFinalOrderPk());
                trackStatusOrder.setArguments(args);
                ((MainActivity)context).loadFragment(trackStatusOrder,true);
            }
        });
        holder.orderstatus.setText(finalOrderDetailResponseList.get(position).getOrderStatus());

//        holder.invoice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CreatePdf createPdf=new CreatePdf();
//                createPdf.createPdf(context,finalOrderDetailResponseList);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return finalOrderDetailResponseList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView productname,mrp,orderno,payment,dateofdelivery,invoice,orderstatus,trackstatus;

        public MyViewHolder(View itemview) {
            super(itemview);

            productname=itemview.findViewById(R.id.product_name);
            mrp=itemview.findViewById(R.id.MRP);
            orderno=itemview.findViewById(R.id.orderno);
            payment=itemview.findViewById(R.id.amountorder);
            dateofdelivery=itemview.findViewById(R.id.dateofdelivery);
            //invoice=itemview.findViewById(R.id.invoice);
            orderstatus=itemview.findViewById(R.id.orderstatus);
            trackstatus=itemview.findViewById(R.id.trackstatus);
        }
    }
}
