package com.example.ecommerce;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackStatusOrder extends Fragment {

    View view;
    List<FinalOrderDetailResponse> orderResponseList=new ArrayList<>();
    String onum,order_id;
    RecyclerView orderlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_track_status_order, container, false);

        orderlist=view.findViewById(R.id.orderdetailList);
        Bundle bundle=this.getArguments();
        Log.e("orderid",bundle.getString("order_id"));
        if(bundle.size()>0) {
            order_id = bundle.getString("order_id");
            Log.e("orderid",order_id);
            getOrderList();
        }else{
            Toast.makeText(getContext(), "Not Order Yet", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    public void getOrderList(){
        Call<AllProduct> call = Api.getApi().myOrderDetail("" + order_id);
        call.enqueue(new Callback<AllProduct>() {
            @Override
            public void onResponse(Call<AllProduct> call, Response<AllProduct> response) {

                if (response.isSuccessful()) {

                    orderResponseList=response.body().getMyOrderDetailResponse();
                    Log.e("orderList", ""+orderResponseList.size());
                    if (orderResponseList.size()>0){

                        MyOrderDetailsAdapter adapter = new MyOrderDetailsAdapter(getActivity(), orderResponseList);
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
