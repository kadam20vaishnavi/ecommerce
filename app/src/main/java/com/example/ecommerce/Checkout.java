package com.example.ecommerce;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Checkout extends Fragment {

    View view;
    TextView amount,paymenttitle;
    int tot=0;
    String total;
    TextInputEditText name, mobile, address, email,upiid;
    Button conform;
    RadioGroup payment;
    RadioButton option;
    String payment_option,id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences info= getActivity().getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
        id=info.getString("userId","");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_checkout, container, false);

        amount = view.findViewById(R.id.amount);
        name = view.findViewById(R.id.name);
        mobile = view.findViewById(R.id.mobile);
        address = view.findViewById(R.id.address);
        email = view.findViewById(R.id.email);
        conform = view.findViewById(R.id.conform);
       payment=view.findViewById(R.id.payment);
        upiid=view.findViewById(R.id.upiId);
        paymenttitle=view.findViewById(R.id.paymenttitle);

        Bundle bundle=getArguments();
        total=bundle.getString("total_value","");
         Log.e("amount", String.valueOf(tot));
        amount.setText(total);
//
        payment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = payment.findViewById(checkedId);
                int index = payment.indexOfChild(radioButton);
                switch (index) {
                    case 0: // first button
                        tot=1;
                        break;
                    case 1: // secondbutton
                        tot=2;
                        break;
                }
            }
        });

        conform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().length()==0){
                    name.setError("name not entered");
                    name.requestFocus();
                }else if(mobile.getText().toString().length()==0){
                    mobile.setError("mobile not entered");
                    mobile.requestFocus();
                }else if(address.getText().toString().length()==0) {
                    address.setError("address not entered");
                    address.requestFocus();
                }else if(tot==0){
                    paymenttitle.setError("select Payment Option");
                    paymenttitle.requestFocus();
                }else if(tot==2){
                    finalOrder();
                }else if(tot==1){
                    Upi_payment upi_payment=new Upi_payment();
                    ((MainActivity)getContext()).loadFragment(upi_payment,false);
                }
            }
        });
        return view;
    }

//    public void onRadioButtonClicked(View v) {
//        // Is the button now checked?
//        boolean checked = ((RadioButton) v).isChecked();
//        // Check which radio button was clicked
//        switch(v.getId()) {
//            case R.id.G_pay:
//                if (checked)
//                   tot=1;
//                    break;
//            case R.id.cod:
//                if (checked)
//                   tot=2;
//                    break;
//        }
//    }
    public void finalOrder() {

        Call<orderResponce> call = Api.getApi().myOrder(""+id,""+name.getText().toString(),""+address.getText().toString(),""+amount.getText().toString(),""+payment_option,"50");
        call.enqueue(new Callback<orderResponce>() {
            @Override
            public void onResponse(Call<orderResponce> call, Response<orderResponce> response) {

                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equalsIgnoreCase("true")) {

                        Log.e("message",response.body().getMessage());

                        PlaceOrderSuccess placeOrderSuccess=new PlaceOrderSuccess();
                        Bundle bundle=new Bundle();
                        bundle.putString("order num",response.body().getOrderNumber());
                        bundle.putString(("order_id"), String.valueOf(response.body().getFinalOrderFk()));
                        bundle.putString("message",response.body().getMessage());
                        placeOrderSuccess.setArguments(bundle);
                        ((MainActivity)getContext()).loadFragment(placeOrderSuccess,true);

                        Toast.makeText(getActivity(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(getActivity(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<orderResponce> call, Throwable t) {
                Log.e("userListError", "" + t.getMessage());

            }
        });
    }
}