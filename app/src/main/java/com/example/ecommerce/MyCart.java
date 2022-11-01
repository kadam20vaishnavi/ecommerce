package com.example.ecommerce;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCart extends Fragment {

    View view;
    RecyclerView cartProduct;
    TextView checkout;
    String id;
    List<MyCartResponse> myCartResponseList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        cartProduct = view.findViewById(R.id.cartProduct);
        checkout = view.findViewById(R.id.checkout);

        SharedPreferences info= getActivity().getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
        id=info.getString("userId","");
//        Bundle bundle=getArguments();
//        int tot = bundle.getInt("amount");
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment checkoutFragment = new Checkout();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle args = new Bundle();
                args.putString("total_value",""+myCartResponseList.get(0).getTotalAmount());
                checkoutFragment.setArguments(args);
                transaction.replace(R.id.frameLayout, checkoutFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        cartProduct();
    }

//    private void paymentpage() {
//        Call<AllProduct> call = Api.getApi().myOrder("1","","","","","");
//        call.enqueue(new Callback<AllProduct>() {
//            @Override
//            public void onResponse(Call<AllProduct> call, Response<AllProduct> response) {
//
//                if (response.isSuccessful()) {
//                    myCartResponseList = response.body().getMyCartResponseList();
//                    if (myCartResponseList.size() > 0) {
//
//                        MyCartAdapter adapter = new MyCartAdapter(getActivity(), myCartResponseList);
//                        cartProduct.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//                        cartProduct.setAdapter(adapter);
//
//                        adapter.notifyDataSetChanged();
//
//                    }else{
//
//
//                    }
//
//                }else{
//
//
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<AllProduct> call, Throwable t) {
//                Log.e("userListError", "" + t.getMessage());
//            }
//        });
//
//    }

    private void cartProduct() {

        Call<AllProduct> call = Api.getApi().myCart(""+id);
        call.enqueue(new Callback<AllProduct>() {
            @Override
            public void onResponse(Call<AllProduct> call, Response<AllProduct> response) {

                if (response.isSuccessful()) {
                    myCartResponseList = response.body().getMyCartResponseList();
                    if (myCartResponseList.size() > 0) {

                        MyCartAdapter adapter = new MyCartAdapter(getActivity(), myCartResponseList);
                        cartProduct.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        cartProduct.setAdapter(adapter);

                        adapter.notifyDataSetChanged();

                    } else {

                    }

                } else {


                }
            }


            @Override
            public void onFailure(Call<AllProduct> call, Throwable t) {
                Log.e("userListError", "" + t.getMessage());
            }
        });

    }
}