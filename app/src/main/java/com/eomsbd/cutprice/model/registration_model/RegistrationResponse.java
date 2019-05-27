package com.eomsbd.cutprice.model.registration_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationResponse {

    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
