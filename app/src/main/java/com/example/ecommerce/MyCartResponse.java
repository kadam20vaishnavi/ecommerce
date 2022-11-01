package com.example.ecommerce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyCartResponse {

    @SerializedName("cart_id")
    @Expose
    private String cartId;
    @SerializedName("productId")
    @Expose
    private String productId;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("productQuantity")
    @Expose
    private String productQuantity;
    @SerializedName("minPurchaseQty")
    @Expose
    private String minPurchaseQty;
    @SerializedName("productUnit")
    @Expose
    private String productUnit;
    @SerializedName("productMrp")
    @Expose
    private String productMrp;
    @SerializedName("productSellPrice")
    @Expose
    private String productSellPrice;
    @SerializedName("productPhoto")
    @Expose
    private String productPhoto;
    @SerializedName("delivery_charge")
    @Expose
    private Integer deliveryCharge;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("subTotalAmount")
    @Expose
    private Integer subTotalAmount;
    @SerializedName("product_delivery_charge")
    @Expose
    private String productDeliveryCharge;
    @SerializedName("totalAmount")
    @Expose
    private Integer totalAmount;
    @SerializedName("stock_status")
    @Expose
    private String stockStatus;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getMinPurchaseQty() {
        return minPurchaseQty;
    }

    public void setMinPurchaseQty(String minPurchaseQty) {
        this.minPurchaseQty = minPurchaseQty;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getProductMrp() {
        return productMrp;
    }

    public void setProductMrp(String productMrp) {
        this.productMrp = productMrp;
    }

    public String getProductSellPrice() {
        return productSellPrice;
    }

    public void setProductSellPrice(String productSellPrice) {
        this.productSellPrice = productSellPrice;
    }

    public String getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
    }

    public Integer getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(Integer deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSubTotalAmount() {
        return subTotalAmount;
    }

    public void setSubTotalAmount(Integer subTotalAmount) {
        this.subTotalAmount = subTotalAmount;
    }

    public String getProductDeliveryCharge() {
        return productDeliveryCharge;
    }

    public void setProductDeliveryCharge(String productDeliveryCharge) {
        this.productDeliveryCharge = productDeliveryCharge;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

}
