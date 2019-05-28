package com.eomsbd.cutprice.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.eomsbd.cutprice.R;
import com.eomsbd.cutprice.model.registration_model.RegistrationResponse;
import com.eomsbd.cutprice.model.registration_model.UserRegistration;
import com.eomsbd.cutprice.web_api.IClientServer;
import com.eomsbd.cutprice.web_api.RetrofitService;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText regName;
    private EditText regAddress;
    private EditText regEmail;
    private EditText regPhoneNumber;
    private EditText regPassword;
    private EditText regConfirmPassword;

    IClientServer iClientServer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        regName = findViewById(R.id.regNameId);
        regAddress = findViewById(R.id.regAddressId);
        regEmail = findViewById(R.id.regEmailId);
        regPhoneNumber = findViewById(R.id.regPhoneId);
        regPassword = findViewById(R.id.regPhoneId);
        regConfirmPassword = findViewById(R.id.regConfirmPassId);

        findViewById(R.id.registerBtnId).setOnClickListener(this);

    }

    //Validation Code
    public boolean isEmpty(EditText text){
        CharSequence t=text.getText().toString();
        return TextUtils.isEmpty( t );
    }
    public boolean checkValidity() {
        View focusView = null;
        boolean cancel = false;
        if (isEmpty(regName)) {
            // focusView=userName;
            cancel = true;
            regName.setError("Enter a valid name");
        }
        if (isEmpty(regEmail)) {
            // focusView = pass;
            cancel = true;
            regEmail.setError("Enter a valid email Email");
        }
        if (isEmpty(regPhoneNumber)) {
            // focusView = pass;
            cancel = true;
            regPhoneNumber.setError("Enter a valid Phone Number");
        }
        if (isEmpty(regAddress)) {
            // focusView = pass;
            cancel = true;
            regAddress.setError("Enter a product Address");
        }
        if (isEmpty(regPassword)) {
            // focusView = pass;
            cancel = true;
           regPassword.setError("Enter a valid password");
        }
        if (isEmpty(regConfirmPassword)) {
            // focusView = pass;
            cancel = true;
            regConfirmPassword.setError("Enter a valid address");
        }
        return cancel;
    }

    //validation End
    //Retrofit Code
    private void getUserRegistration(){

        iClientServer = RetrofitService.getRetrofitInstance().create(IClientServer.class);
        String apiKey = "Cutprice@987";
        String name = regName.getText().toString().trim();
        String address=regAddress.getText().toString().trim();
        String email=regEmail.getText().toString().trim();
        String number=regPhoneNumber.getText().toString().trim();
        String password=regPassword.getText().toString().trim();
        String confirmPassword=regConfirmPassword.getText().toString().trim();

        final UserRegistration userRegistration = new UserRegistration();

        userRegistration.setApiKey(apiKey);
        userRegistration.setName(name);
        userRegistration.setAddress(address);
        userRegistration.setEmail(email);
        userRegistration.setPhone(number);
        userRegistration.setPassword(password);


        Call<RegistrationResponse>call=iClientServer.registrationInfo(userRegistration);
        call.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {

                RegistrationResponse registrationResponse = response.body();

                if (response.isSuccessful() && registrationResponse !=null ){
                    Toasty.success(RegistrationActivity.this,""+registrationResponse.getSuccess(),Toasty.LENGTH_LONG).show();


                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (checkValidity()){
        }else {
            getUserRegistration();
        }

    }
}
