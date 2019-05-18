
package com.inducesmile.androidpayexample.model.category_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Submenu {

    @SerializedName("categories_id")
    @Expose
    private String categoriesId;
    @SerializedName("categories_name")
    @Expose
    private String categoriesName;
    @SerializedName("en_categories_name")
    @Expose
    private String enCategoriesName;
    @SerializedName("cat_active")
    @Expose
    private String catActive;
    @SerializedName("block")
    @Expose
    private String block;
    @SerializedName("block_style")
    @Expose
    private String blockStyle;
    @SerializedName("blockposition")
    @Expose
    private String blockposition;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;

    public String getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(String categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public String getEnCategoriesName() {
        return enCategoriesName;
    }

    public void setEnCategoriesName(String enCategoriesName) {
        this.enCategoriesName = enCategoriesName;
    }

    public String getCatActive() {
        return catActive;
    }

    public void setCatActive(String catActive) {
        this.catActive = catActive;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getBlockStyle() {
        return blockStyle;
    }

    public void setBlockStyle(String blockStyle) {
        this.blockStyle = blockStyle;
    }

    public String getBlockposition() {
        return blockposition;
    }

    public void setBlockposition(String blockposition) {
        this.blockposition = blockposition;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
