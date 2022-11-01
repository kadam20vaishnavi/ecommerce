package com.example.ecommerce;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.service.quickaccesswallet.WalletCard;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;

public class Upi_payment extends Fragment {

    private PaymentsClient paymentsClient;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Wallet.WalletOptions walletOptions=new Wallet.WalletOptions.Builder()
//                .setEnvironment(WalletConstants.ENVIRONMENT_TEST)
//                .build();
//
//        paymentsClient=Wallet.getPaymentsClient(getContext(),walletOptions);
//
//        IsReadyToPayRequest readyToPayRequest=IsReadyToPayRequest.fromJson(baseCo)

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upi_payment, container, false);
    }
}