package com.example.ecommerce;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static final String BASE_URL="http://192.168.1.3/project/";
    //192.168.233.244
    private static Retrofit retrofit=null;
    private static Gson gson;

    public static ApiInterface getApi() {
        gson = new GsonBuilder()
                .setLenient()
                .create();
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        return apiInterface;
    }
}