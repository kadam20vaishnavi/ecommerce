package com.example.ecommerce;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment {

    View view;
    List<CategoryResponse> categoryList = new ArrayList<>();
    List<ProductResponse> randomProductList = new ArrayList<>();
    RecyclerView Categories, Random_Product;
    public static ViewPager mPager;
    List<BannerResponse> bannerResponseList = new ArrayList<>();
    static int NUM_PAGES = 0;
    static int currentPage = 0;
    static Activity activity;
    ProductAdapter adapter;
    EditText editsearchbar;

    private AppBarLayout searchbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        Categories = view.findViewById(R.id.category);
        Random_Product = view.findViewById(R.id.Random_product);
        mPager = view.findViewById(R.id.pager);

        editsearchbar = (EditText) view.findViewById(R.id.searchProduct);

        activity = getActivity();

        return view;
    }

    private void filter(String text) {
        ArrayList<ProductResponse> filterdlist=new ArrayList<>();

        if(randomProductList.size()>0) {
            for (ProductResponse item : randomProductList) {
                if (item.getProductId().toLowerCase().contains(text.toLowerCase())) {
                    filterdlist.add(item);
                }
            }
            adapter.filterList(filterdlist);
        }else{
            Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity)getActivity()).lockUnlockDrawer(0);
        getBanner();
        getCategaries();
        getRandomProduct();

    }

    private void getBanner() {

        Call<AllProduct> call = Api.getApi().getBanner();
        call.enqueue(new Callback<AllProduct>() {
            @Override
            public void onResponse(Call<AllProduct> call, Response<AllProduct> response) {

                if (response.isSuccessful()) {
                    bannerResponseList = response.body().getBannerResponseList();
                    if (bannerResponseList.size() > 0) {

                        BannerImageAdapter adapter = new BannerImageAdapter(activity, bannerResponseList);
                        mPager.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    } else {

                    }
                } else {

                }

            }

            @Override
            public void onFailure(Call<AllProduct> call, Throwable t) {
                Log.e("bannerError", "" + t.getMessage());
            }
        });
    }

    private void getRandomProduct() {
        Call<AllProduct> call = Api.getApi().getRandomProduct("1");
        call.enqueue(new Callback<AllProduct>() {
            @Override
            public void onResponse(Call<AllProduct> call, Response<AllProduct> response) {

                if (response.isSuccessful()) {

                    randomProductList = response.body().getProductResponseList();
                    if (randomProductList.size() > 0) {

                        adapter = new ProductAdapter(getActivity(), randomProductList);
                        // Product.setLayoutManager(new GridLayoutManager(getActivity(),2));
                        Random_Product.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        Random_Product.setAdapter(adapter);
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

    public void getCategaries() {

        Call<AllProduct> call = Api.getApi().getCategory();
        call.enqueue(new Callback<AllProduct>() {
            @Override
            public void onResponse(Call<AllProduct> call, Response<AllProduct> response) {

                if (response.isSuccessful()) {

                    categoryList = response.body().getCategoryResponseList();
                    if (categoryList.size() > 0) {

                        CategoryAdapter adapter = new CategoryAdapter(getActivity(), categoryList);
                        Categories.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                        // recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        Categories.setAdapter(adapter);
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
