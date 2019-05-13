package com.inducesmile.androidpayexample.model.product_order_model;

public class ParamProductOrder {
    private String api_key = "";
    private String productId;
    private String quantity;
    private String name = "";
    private String email = "";
    private String address = "";
    private String phone = "";
    private String comment = "";


    public ParamProductOrder(String api_key, String productId, String quantity, String name, String email, String address, String phone, String comment) {
        this.api_key = api_key;
        this.productId = productId;
        this.quantity = quantity;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.comment = comment;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ParamUserRegistration{" +
                "api_key='" + api_key + '\'' +
                ", name='" + name + '\'' +
                ", productId='" + productId + '\'' +
                ", quantity='" + quantity + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + comment + '\'' +
                '}';
    }
}
