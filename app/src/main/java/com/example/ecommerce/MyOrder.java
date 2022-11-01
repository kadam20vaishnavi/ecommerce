package com.example.ecommerce;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrder extends Fragment {

    TextView ordern,payment_mode;
    String userId, onum,order_id;
    List<Orderlistresponce> orderResponseList=new ArrayList<>();
    RecyclerView orderlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_order, container, false);

        SharedPreferences prefs = getContext().getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);

        userId = prefs.getString("userId", "");
        Bundle bundle=getActivity().getIntent().getExtras();
        if(bundle!=null) {
            onum = getArguments().getString("order_num");
            Log.e("ordernum",onum);
            order_id = getArguments().getString("order_id");
            Log.e("orderid",order_id);
        }else{
            Toast.makeText(getContext(), "Not Order Yet", Toast.LENGTH_SHORT).show();
        }

        ordern=v.findViewById(R.id.orderno);
        payment_mode=v.findViewById(R.id.payment);
        orderlist=v.findViewById(R.id.myorderlist);
        getOrderList();
        return v;
    }

    public void getOrderList(){
        Call<AllProduct> call = Api.getApi().myorderList("" + userId);
        call.enqueue(new Callback<AllProduct>() {
            @Override
            public void onResponse(Call<AllProduct> call, Response<AllProduct> response) {

                if (response.isSuccessful()) {

                    orderResponseList=response.body().getMyOrderlistResponce();
                    Log.e("orderList", ""+orderResponseList.size());
                    if (orderResponseList.size()>0){

                        MyOrderAdapter adapter = new MyOrderAdapter(getActivity(), orderResponseList);
                        // Product.setLayoutManager(new GridLayoutManager(getActivity(),2));
                        orderlist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        orderlist.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AllProduct> call, Throwable t) {
                Log.e("userListError", "" + t.getMessage());

            }
        });
    }
}