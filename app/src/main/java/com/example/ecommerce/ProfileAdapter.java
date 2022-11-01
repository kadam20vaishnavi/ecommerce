package com.example.ecommerce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    Context context;
    List<ProfileResponse> profileResponseList = new ArrayList<>();

    public ProfileAdapter(Context context, List<ProfileResponse> profileResponseList) {
        this.context = context;
        this.profileResponseList = profileResponseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_view, viewGroup, false);
        return new ProfileAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(""+profileResponseList.get(0).getcName());
        holder.mobile.setText(""+profileResponseList.get(0).getcMobile());
        holder.address.setText(""+profileResponseList.get(0).getcAddress());
        holder.email.setText(""+profileResponseList.get(0).getcEmail());
    }


    @Override
    public int getItemCount() {
        return profileResponseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,mobile,address,email;

        public ViewHolder(View itemView) {
            super(itemView);
                name=itemView.findViewById(R.id.namep);
                mobile=itemView.findViewById(R.id.mobilep);
                address=itemView.findViewById(R.id.addressp);
                email=itemView.findViewById(R.id.emailp);
        }
    }
}
