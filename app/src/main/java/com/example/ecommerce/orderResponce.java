package com.example.ecommerce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class orderResponce {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("order_number")
    @Expose
    private String orderNumber;
    @SerializedName("final_order_fk")
    @Expose
    private Integer finalOrderFk;
    @SerializedName("message")
    @Expose
    private String message;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getFinalOrderFk() {
        return finalOrderFk;
    }

    public void setFinalOrderFk(Integer finalOrderFk) {
        this.finalOrderFk = finalOrderFk;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
