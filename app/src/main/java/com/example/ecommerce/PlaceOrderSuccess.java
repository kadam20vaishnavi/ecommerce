package com.example.ecommerce;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class PlaceOrderSuccess extends Fragment {

    TextView orderno;
    Button track;
    String order_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_place_order_success, container, false);

        String on=getArguments().getString("order num");
        order_id=getArguments().getString("order_id");
        Log.e("placeorder",on);
        Log.e("posplaceoid",order_id);
        orderno=v.findViewById(R.id.orderno);
        track=v.findViewById(R.id.trackorder);
        orderno.setText(on);

        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TrackStatusOrder myOrder=new TrackStatusOrder();
                Bundle bundle=new Bundle();
                bundle.putString("order_num",orderno.getText().toString());
                bundle.putString("order_id",order_id);
                myOrder.setArguments(bundle);
                Log.e("sorderno",on);
                Log.e("sorderid",order_id);

                ((MainActivity)getContext()).loadFragment(myOrder,false);
            }
        });
        return v;
    }
}