package com.example.ecommerce;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("register.php")
    Call<Registerresponce> create(@Field("name") String name,
                                  @Field("mobile") String mobile,
                                  @Field("address") String address,
                                  @Field("email") String email,
                                  @Field("password") String password);

    @GET("getUserList.php")
    Call<AllListResponse> getUserList();

    @FormUrlEncoded
    @POST("Login.php")
    Call<Loginresponce> login(@Field("email") String email,
                              @Field("password") String password);

    @GET("getCategory.php")
    Call<AllProduct> getCategory();

    @GET("getCategoryProductList.php")
    Call<AllProduct> getCategoryProduct(@Query("userId") String userId,
                                        @Query("categoryId") String categoryId);

    @GET("productList.php")
    Call<AllProduct> getRandomProduct(@Query("userId") String userId);

    @GET("getBanner.php")
    Call<AllProduct> getBanner();

    @FormUrlEncoded
    @POST("addToCart.php")
    Call<CartResponse> addToCart(@Field("userId")String userId,
                                 @Field("productId")String productId,
                                 @Field("quantity")String quantity);

    @FormUrlEncoded
    @POST("deleteCart.php")
    Call<Loginresponce> deleteCart(@Field("userId")String userId,
                                   @Field("productId")String productId);

    @FormUrlEncoded
    @POST("updateCart.php")
    Call<Loginresponce> updateCart(@Field("userId")String userId,
                                   @Field("productId")String productId,
                                   @Field("quantity")String quantity);

    @GET("viewCart.php")
    Call<AllProduct> myCart(@Query("userId") String userId);

    @FormUrlEncoded
    @POST("FinalOrder.php")
    Call<orderResponce> myOrder(@Field("userId")String userId,
                                @Field("customerName")String customerName,
                                @Field("billingAddress")String billingAddress,
                                @Field("subAmount")String subAmount,
                                @Field("razorPayPaymentID")String razorPayPaymentID,
                                @Field("deliveryCharge")String deliveryCharge );

    @GET("getProfile.php")
    Call<AllProduct> getProfile(@Query("userId")String userId);

    @GET("getOrderDetails.php")
    Call<AllProduct> myOrderDetail(@Query("orderId")String orderId);

    @GET("getOrderList.php")
    Call<AllProduct> myorderList(@Query("userId") String userId);
}

