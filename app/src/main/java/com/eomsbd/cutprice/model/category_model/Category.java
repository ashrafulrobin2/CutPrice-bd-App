
package com.eomsbd.cutprice.model.category_model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("data")
    @Expose
    private List<Datum1> data = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Datum1> getData() {
        return data;
    }

    public void setData(List<Datum1> data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
