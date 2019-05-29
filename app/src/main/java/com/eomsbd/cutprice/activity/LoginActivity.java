package com.eomsbd.cutprice.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.eomsbd.cutprice.R;
import com.eomsbd.cutprice.fragment.AccountFragment;
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


    public static final String mypreference = "mypref";
    SharedPreferences sharedPreferences;

    private String client_id;
    private String client_name;
    private String client_address;
    private String client_email;
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

    //Validation Code
    public boolean isEmpty(EditText text){
        CharSequence t=text.getText().toString();
        return TextUtils.isEmpty( t );
    }
    public boolean checkValidity() {
        View focusView = null;
        boolean cancel = false;
        if (isEmpty(editTextEmail)) {
            // focusView=userName;
            cancel = true;
           editTextEmail.setError("Enter a valid name");
        }
        if (isEmpty(editTextpassword)) {
            // focusView = pass;
            cancel = true;
            editTextpassword.setError("Enter a valid email Email");
        }

        return cancel;
    }


    /////Validation end//
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
                    client_name=loginResponse.getData().getClientName().toString();


                    client_address=loginResponse.getData().getClientAddress().toString();
                    client_email=loginResponse.getData().getClientEmail().toString();
                    number_of_orders=loginResponse.getData().getNumberOfOrders().toString();


                    sharedPreferences = LoginActivity.this.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("client_Id", client_id);
                    editor.putString("client_name", client_name);


                    editor.putString("client_address",client_address);
                    editor.putString("client_email",client_email);
                   editor.putString("number_of_orders",number_of_orders);


                    editor.apply();
                    editor.commit();


                }

                else {
                    Toasty.info(LoginActivity.this, "login Error", Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toasty.error(LoginActivity.this, "Response Error" + t.getMessage(), Toasty.LENGTH_LONG).show();
            }
        });
    }

    //Button
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.buttonLogin:
                checkValidity();
                Intent intent = new Intent(LoginActivity.this,ShoppingActivity.class);
                startActivity(intent);
                getUserLogin();
                break;
            case R.id.textViewRegister:
                Intent intent1 =new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent1);
                break;
}
    }
}
