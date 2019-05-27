
package com.eomsbd.cutprice.model.login_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("client_name")
    @Expose
    private String clientName;
    @SerializedName("client_address")
    @Expose
    private String clientAddress;
    @SerializedName("client_email")
    @Expose
    private String clientEmail;
    @SerializedName("client_courier")
    @Expose
    private String clientCourier;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("number_of_orders")
    @Expose
    private String numberOfOrders;
    @SerializedName("successfull_orders")
    @Expose
    private String successfullOrders;
    @SerializedName("failed_orders")
    @Expose
    private String failedOrders;
    @SerializedName("pass_reset")
    @Expose
    private Object passReset;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientCourier() {
        return clientCourier;
    }

    public void setClientCourier(String clientCourier) {
        this.clientCourier = clientCourier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(String numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public String getSuccessfullOrders() {
        return successfullOrders;
    }

    public void setSuccessfullOrders(String successfullOrders) {
        this.successfullOrders = successfullOrders;
    }

    public String getFailedOrders() {
        return failedOrders;
    }

    public void setFailedOrders(String failedOrders) {
        this.failedOrders = failedOrders;
    }

    public Object getPassReset() {
        return passReset;
    }

    public void setPassReset(Object passReset) {
        this.passReset = passReset;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
