package com.example.ecommerce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class  AllProduct {

    @SerializedName("categoryResponse")
    @Expose
    private List<CategoryResponse> categoryResponseList=null;
    @SerializedName("product")
    @Expose
    private List<ProductResponse> productResponseList=null;
    @SerializedName("bannerResponse")
    @Expose
    private List<BannerResponse> bannerResponseList=null;
    @SerializedName("cartResponse")
    @Expose
    private List<MyCartResponse> myCartResponseList=null;
    @SerializedName("profileResponse")
    @Expose
    private List<ProfileResponse> myProfileResponse=null;
    @SerializedName("orderResponse")
    @Expose
    private List<Orderlistresponce> myOrderlistResponce=null;
    @SerializedName("orderDetailsResponse")
    @Expose
    private List<FinalOrderDetailResponse> myOrderDetailResponse=null;

    public List<FinalOrderDetailResponse> getMyOrderDetailResponse() {
        return myOrderDetailResponse;
    }
    public void setMyOrderDetailResponse(List<FinalOrderDetailResponse> myOrderDetailResponse) {
        this.myOrderDetailResponse = myOrderDetailResponse;
    }

    public void setMyOrderlistResponce(List<Orderlistresponce> myOrderlistResponce) {
        this.myOrderlistResponce = myOrderlistResponce;
    }

    public List<Orderlistresponce> getMyOrderlistResponce() {
        return myOrderlistResponce;
    }

    public void setMylistOrderResponce(List<Orderlistresponce> myFinalOrderResponce) {
        this.myOrderlistResponce = myFinalOrderResponce;
    }

    public List<ProfileResponse> getMyProfileResponse() {
        return myProfileResponse;
    }

    public void setMyProfileResponse(List<ProfileResponse> myProfileResponse) {
        this.myProfileResponse = myProfileResponse;
    }

    public List<MyCartResponse> getMyCartResponseList() {
        return myCartResponseList;
    }

    public void setMyCartResponseList(List<MyCartResponse> myCartResponseList) {
        this.myCartResponseList = myCartResponseList;
    }

    public List<CategoryResponse> getCategoryResponseList() {
        return categoryResponseList;
    }

    public void setCategoryResponseList(List<CategoryResponse> categoryResponseList) {
        this.categoryResponseList = categoryResponseList;
    }

    public List<ProductResponse> getProductResponseList() {
        return productResponseList;
    }

    public void setProductResponseList(List<ProductResponse> productResponseList) {
        this.productResponseList = productResponseList;
    }

    public List<BannerResponse> getBannerResponseList() {
        return bannerResponseList;
    }

    public void setBannerResponseList(List<BannerResponse> bannerResponseList) {
        this.bannerResponseList = bannerResponseList;
    }

}


