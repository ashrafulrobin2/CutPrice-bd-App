package com.eomsbd.cutprice.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.eomsbd.cutprice.R;
import com.eomsbd.cutprice.model.login_model.LoginResponse;
import com.eomsbd.cutprice.model.login_model.UserLogin;
import com.eomsbd.cutprice.web_api.IClientServer;
import com.eomsbd.cutprice.web_api.RetrofitService;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextpassword;

    private String client_id;
    private String client_name;
    private String client_address;
    private String client_email;
    private String client_courier;
    private String number_of_orders;


    IClientServer iClientServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.email_Id);
        editTextpassword = findViewById(R.id.passwordId);

        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.textViewRegister).setOnClickListener(this);


    }

    private void getUserLogin() { //baseUrl+stringUrl
        iClientServer = RetrofitService.getRetrofitInstance().create(IClientServer.class);
        String apiKey = "Cutprice@987";
        String email = editTextEmail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        final UserLogin userLogin = new UserLogin();

        userLogin.setApiKey(apiKey);
        userLogin.setEmail(email);
        userLogin.setPassword(password);

        Call<LoginResponse> call = iClientServer.loginInfo(userLogin);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if (response.isSuccessful() && loginResponse != null) {

                   client_id=loginResponse.getData().getClientId().toString();

                    Toasty.success(LoginActivity.this, "profile "+client_id, Toasty.LENGTH_LONG).show();
                } else {
                    Toasty.info(LoginActivity.this, "login Error", Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toasty.error(LoginActivity.this, "Response Error" + t.getMessage(), Toasty.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonLogin:
                getUserLogin();
                break;
            case R.id.textViewRegister:
                break;
}
    }
}
