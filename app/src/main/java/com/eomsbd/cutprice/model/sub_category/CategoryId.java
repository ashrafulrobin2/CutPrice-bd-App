
package com.eomsbd.cutprice.model.sub_category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryId {

    @SerializedName("cat_id")
    @Expose
    private String catId;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

}
