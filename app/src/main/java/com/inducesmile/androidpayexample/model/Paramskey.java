package com.inducesmile.androidpayexample.model;

public class Paramskey {
    private String api_key = "";


    public Paramskey(String api_key) {
        this.api_key = api_key;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }


    @Override
    public String toString() {
        return "Paramskey{" +
                "api_key='" + api_key + '\'' +
                '}';
    }
}
