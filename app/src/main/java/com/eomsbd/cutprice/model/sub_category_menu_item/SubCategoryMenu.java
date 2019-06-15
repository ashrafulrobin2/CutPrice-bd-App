
package com.eomsbd.cutprice.model.sub_category_menu_item;

import java.util.List;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategoryMenu {

    @SerializedName("data")
    @Expose
    private List<Datum3> data = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Datum3> getData() {
        return data;
    }

    public void setData(List<Datum3> data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
