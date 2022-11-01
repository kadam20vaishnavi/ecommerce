package com.example.ecommerce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Loginresponce {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("userId")
    @Expose
    private int id;

    @SerializedName("message")
    @Expose
    private String message;


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
