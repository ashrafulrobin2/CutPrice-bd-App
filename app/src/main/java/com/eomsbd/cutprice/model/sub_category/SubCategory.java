
package com.eomsbd.cutprice.model.sub_category;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategory {

    @SerializedName("data")
    @Expose
    private List<Datum2> data = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Datum2> getData() {
        return data;
    }

    public void setData(List<Datum2> data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
