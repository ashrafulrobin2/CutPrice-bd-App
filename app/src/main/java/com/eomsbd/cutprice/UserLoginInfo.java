package com.eomsbd.cutprice;

import android.content.Context;
import android.content.SharedPreferences;

public class UserLoginInfo {

    SharedPreferences sharedPreferences ;
    Context context;

    public void removeUser(){  //remove sharedPreferences

        sharedPreferences.edit().clear().commit();
    }
  public String getEmail() {
        email=sharedPreferences.getString("userdata","");
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        sharedPreferences.edit().putString("userdata",email).commit();
    }

    private String email;



    public   UserLoginInfo(Context context)
    {

        this.context=context;
sharedPreferences =context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);

    }

}
