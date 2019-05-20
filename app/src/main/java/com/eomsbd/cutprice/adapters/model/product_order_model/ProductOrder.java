
package com.eomsbd.cutprice.adapters.model.product_order_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductOrder {

    @SerializedName("api_key")
    @Expose
    private String apiKey;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("product_selling_price")
    @Expose
    private String productSellingPrice;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductSellingPrice() {
        return productSellingPrice;
    }

    public void setProductSellingPrice(String productSellingPrice) {
        this.productSellingPrice = productSellingPrice;
    }

}
