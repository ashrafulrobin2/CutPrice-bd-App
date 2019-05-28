package com.eomsbd.cutprice.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.eomsbd.cutprice.R;
import com.eomsbd.cutprice.model.login_model.LoginResponse;
import com.eomsbd.cutprice.model.login_model.UserLogin;
import com.eomsbd.cutprice.web_api.IClientServer;
import com.eomsbd.cutprice.web_api.RetrofitService;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextpassword;


    public static final String mypreference = "mypref";
    SharedPreferences sharedPreferences;
    private static final String EMAIL = "email";

    private String client_id;
    private String client_name;
    private String client_address;
    private String client_email;
    private String number_of_orders;
    CallbackManager   callbackManager;
    LoginButton loginButton;
    IClientServer iClientServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FacebookSdk.sdkInitialize(this.getApplicationContext());

        callbackManager = CallbackManager.Factory.create();
        editTextEmail = findViewById(R.id.email_Id);
        editTextpassword = findViewById(R.id.passwordId);

        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.Register_textView_Id).setOnClickListener(this);
        printKeyHash();
        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.setReadPermissions(Arrays.asList(EMAIL));

        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                startActivity(new Intent(LoginActivity.this,ShoppingActivity.class));
            }

            @Override
            public void onCancel() {
               /* Toasty.info(LoginActivity.this,"onCancel",Toasty.LENGTH_LONG).show();*/
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d("TAG",exception.getMessage());
                Toasty.error(LoginActivity.this,"login error"+exception.getMessage(),Toasty.LENGTH_LONG).show();
            }
        });
   }

   //keyHash
    private void printKeyHash() {
        try {
            PackageInfo info=getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature:info.signatures){
                MessageDigest md=MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.d("KeyHash",hashKey);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
    //keyHash End //


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode , data);
        super.onActivityResult(requestCode, resultCode, data);
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
            case R.id.Register_textView_Id:
                Intent intent1 =new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent1);
                break;


}
    }
}
