package com.eomsbd.cutprice.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eomsbd.cutprice.R;
import com.eomsbd.cutprice.fragment.AccountFragment;
import com.eomsbd.cutprice.fragment.UpdatePassword;
import com.eomsbd.cutprice.helpers.SessionManager;
import com.eomsbd.cutprice.model.login_model.LoginResponse;
import com.eomsbd.cutprice.model.login_model.UserLogin;
import com.eomsbd.cutprice.web_api.IClientServer;
import com.eomsbd.cutprice.web_api.RetrofitService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.eomsbd.cutprice.activity.ProductActivity.mypreference;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText editTextEmail;
    private TextInputEditText editTextpassword;


    public static final int index = 1;

    public static final String mypreference = "mypref";
    SharedPreferences sharedPreferences;


    TextView textView;


    String client_id;
    String client_name;
    String client_address;
    String client_email;
    String number_of_orders;
    String client_password;


    IClientServer iClientServer;

    // Session Manager Class
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Session Manager
        session = new SessionManager(getApplicationContext());
        editTextEmail = findViewById(R.id.email_id);
        editTextpassword = findViewById(R.id.passwordId);


        findViewById(R.id.forget_passwordId).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.Register_textView_Id).setOnClickListener(this);
        printKeyHash();

    }

    @SuppressLint("PackageManagerGetSignatures")
    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.d("KeyHash", hashKey);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    //Validation Code
    public boolean isEmpty(EditText text) {
        CharSequence t = text.getText().toString();
        return TextUtils.isEmpty(t);
    }

    public boolean checkValidity() {
        View focusView = null;
        boolean cancel = false;
        if (isEmpty(editTextEmail)) {
            // focusView=userName;
            cancel = true;
            editTextEmail.setError("Enter a valid Email ");
        }
        if (isEmpty(editTextpassword)) {
            // focusView = pass;
            cancel = true;
            editTextpassword.setError("Enter a valid Password ");
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
                    if (loginResponse.getData() == null) {
                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                        startActivity(intent);
                        Toasty.info(LoginActivity.this, "Username or Password wrong", Toasty.LENGTH_LONG).show();
                    } else {
                        session.createLoginSession("Android Hive", "anroidhive@gmail.com");

                        // Staring MainActivity
                        Intent i = new Intent(getApplicationContext(), ShoppingActivity.class);
                        startActivity(i);
                        finish();
                        Toasty.success(LoginActivity.this, "Login Successful", Toasty.LENGTH_LONG).show();
                        client_id = loginResponse.getData().getClientId();
                        client_name = loginResponse.getData().getClientName();
                        client_address = loginResponse.getData().getClientAddress();
                        client_email = loginResponse.getData().getClientEmail();
                        client_password = loginResponse.getData().getPassword();
                        number_of_orders = loginResponse.getData().getNumberOfOrders();
                        sharedPreferences = LoginActivity.this.getSharedPreferences(mypreference, Context.MODE_PRIVATE);

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("client_Id", client_id);
                        editor.putString("client_name", client_name);
                        editor.putString("client_address", client_address);
                        editor.putString("client_email", client_email);
                        editor.putString("number_of_orders", number_of_orders);
                        editor.putString("client_password", client_password);
                        editor.apply();
                        editor.commit();
                    }

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toasty.normal(LoginActivity.this, R.string.message_text2, getResources().getDrawable(R.drawable.wifi)).show();
            }
        });

    }

    //Button
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.buttonLogin:
                if (checkValidity()) {
                    } else {
                        getUserLogin();
                        setTitle("Logout");
                    }


                break;
            case R.id.Register_textView_Id:
                Intent intent1 = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent1);
                break;

            case R.id.forget_passwordId:
                showSmsDialog();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    private void showSmsDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        UpdatePassword requestDialogFragment = new UpdatePassword();
        requestDialogFragment.show(fragmentManager, "request_dialog");
    }
}
