package com.example.ecommerce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FinalOrderResponse {

    @SerializedName("final_order_pk")
    @Expose
    private String finalOrderPk;
    @SerializedName("order_number")
    @Expose
    private String orderNumber;
    @SerializedName("grand_amount")
    @Expose
    private String grandAmount;
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("customer_number")
    @Expose
    private String customerNumber;
    @SerializedName("billing_address")
    @Expose
    private String billingAddress;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("order_time")
    @Expose
    private String orderTime;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;

    public String getFinalOrderPk() {
        return finalOrderPk;
    }

    public void setFinalOrderPk(String finalOrderPk) {
        this.finalOrderPk = finalOrderPk;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getGrandAmount() {
        return grandAmount;
    }

    public void setGrandAmount(String grandAmount) {
        this.grandAmount = grandAmount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
