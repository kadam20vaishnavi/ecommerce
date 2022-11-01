package com.example.ecommerce;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends Fragment {

    List<ProfileResponse> profileResponseList;
    TextView name,mobile,address,email,myorder,mycarts;
    String useremail, userPassword;
    RecyclerView list;
    Button logout;
    String userId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getContext().getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);

        userId = prefs.getString("userId", "");
        useremail = prefs.getString("email", "");
        userPassword = prefs.getString("password", "");
    }

    @Override
    public void onStart() {
        super.onStart();
        getProfile();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.namep);
        mobile = view.findViewById(R.id.mobilep);
        address = view.findViewById(R.id.addressp);
        email = view.findViewById(R.id.emailp);
        myorder = view.findViewById(R.id.orders);
        mycarts = view.findViewById(R.id.carts);
        logout = view.findViewById(R.id.logout);

        mycarts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCart mycart=new MyCart();
                ((MainActivity)getContext()).loadFragment(mycart,true);
               // loadFragment(new MyCart(),true);
            }
        });
        myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOrder mycart=new MyOrder();
                ((MainActivity)getContext()).loadFragment(mycart,true);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getContext().getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=prefs.edit();
                editor.clear();
                editor.apply();

                Intent intent=new Intent(getContext(),Login.class);
                startActivity(intent);

            }
        });
        return view;
    }

    private void getProfile() {

        try {
            Call<AllProduct> call = Api.getApi().getProfile(""+userId);
            call.enqueue(new Callback<AllProduct>() {
                @Override
                public void onResponse(Call<AllProduct> call, Response<AllProduct> response) {
                    if(response.isSuccessful()) {
                        profileResponseList = response.body().getMyProfileResponse();
                        if (profileResponseList.size() > 0) {

                            name.setText(""+profileResponseList.get(0).getcName());
                            mobile.setText(""+profileResponseList.get(0).getcMobile());
                            email.setText(""+profileResponseList.get(0).getcEmail());
                            address.setText(""+profileResponseList.get(0).getcAddress());

                        } else {

                        }
                    }else{

                    }
                        }
                @Override
                public void onFailure(Call<AllProduct> call, Throwable t) {
                    Log.e("bannerError", "" + t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}