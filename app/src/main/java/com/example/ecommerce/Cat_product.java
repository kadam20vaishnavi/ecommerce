package com.example.ecommerce;

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

public class Cat_product extends Fragment {

    View view;
    List<ProductResponse> productList=new ArrayList<>();
    RecyclerView CatList;
    String catid,catname;
    TextView categoryname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cat_product, container, false);
        CatList = view.findViewById(R.id.catProduct);
        categoryname=view.findViewById(R.id.categoryname);
        Bundle bundle=getArguments();
        catid=bundle.getString("catId");
        catname=bundle.getString("catname");

        categoryname.setText(""+catname);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity)getActivity()).lockUnlockDrawer(1);
        getCatProduct();
    }

    private void getCatProduct()
    {
        Call<AllProduct> call = Api.getApi().getCategoryProduct("1",""+catid);
        call.enqueue(new Callback<AllProduct>() {
            @Override
            public void onResponse(Call<AllProduct> call, Response<AllProduct> response) {

                if (response.isSuccessful()) {

                    productList = response.body().getProductResponseList();
                    Log.e("ProductList", ""+productList.size());
                    if (productList.size()>0){

                        ProductAdapter adapter = new ProductAdapter(getActivity(), productList);
                        // Product.setLayoutManager(new GridLayoutManager(getActivity(),2));
                        CatList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        CatList.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AllProduct> call, Throwable t) {
                Log.e("userListError", ""+t.getMessage());
            }
        });
    }
}