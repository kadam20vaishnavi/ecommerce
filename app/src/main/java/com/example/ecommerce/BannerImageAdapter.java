package com.example.ecommerce;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BannerImageAdapter extends PagerAdapter {

    private final List<BannerResponse> bannerResponseList;
    private final LayoutInflater inflater;
    private final Context context;

    public BannerImageAdapter(Context context, List<BannerResponse> bannerResponseList) {

        this.context = context;
        this.bannerResponseList = bannerResponseList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        try {
            if (bannerResponseList.size() == 0) {
                return 0;
            } else {
                return bannerResponseList.size();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {

        View itemView = inflater.inflate(R.layout.custom_pager, view, false);
        // assert imageLayout != null;
        ImageView imageView = itemView.findViewById(R.id.imageView);
        LinearLayout linearLayout = itemView.findViewById(R.id.linearLayout);

        try {

            Picasso.with(context)
                    .load("https://webuslabs.com/WebusGrocery/Admin/" + bannerResponseList.get(position).getBannerImage())
                    .fit()
                    .into(imageView);
            view.addView(itemView);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return itemView;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}

