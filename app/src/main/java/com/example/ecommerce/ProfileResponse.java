package com.example.ecommerce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileResponse {

    @SerializedName("c_id")
    @Expose
    private String cId;
    @SerializedName("c_name")
    @Expose
    private String cName;
    @SerializedName("c_address")
    @Expose
    private String cAddress;
    @SerializedName("c_mobile")
    @Expose
    private String cMobile;
    @SerializedName("c_email")
    @Expose
    private String cEmail;
    @SerializedName("shop_name")
    @Expose
    private String shopName;
    @SerializedName("c_photo")
    @Expose
    private String cPhoto;
    @SerializedName("c_pincode")
    @Expose
    private String cPincode;

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public String getcMobile() {
        return cMobile;
    }

    public void setcMobile(String cMobile) {
        this.cMobile = cMobile;
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getcPhoto() {
        return cPhoto;
    }

    public void setcPhoto(String cPhoto) {
        this.cPhoto = cPhoto;
    }

    public String getcPincode() {
        return cPincode;
    }

    public void setcPincode(String cPincode) {
        this.cPincode = cPincode;
    }
}
