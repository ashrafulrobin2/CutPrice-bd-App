package com.inducesmile.androidpayexample;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.inducesmile.androidpayexample.model.product_order_model.ProductOrder;
import com.inducesmile.androidpayexample.model.product_order_model.UserOrder;
import com.inducesmile.androidpayexample.web_api.IClientServer;
import com.inducesmile.androidpayexample.web_api.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderNowActivity extends AppCompatActivity {

    private RelativeLayout rlayout;
    private Animation animation;

 private EditText fullname,email,phone,quantity,password,address,note;

 private Button orderNow;

 IClientServer iClientServer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_now);


      /* Toolbar toolbar = findViewById(R.id.bgHeader);
       setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rlayout = findViewById(R.id.rlayout);
        rlayout.setAnimation(animation);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                onBackPressed();
                       return true;
        }
        return super.onOptionsItemSelected(item);
        */

        fullname =(EditText) findViewById(R.id.fullname_editTextId);
        email =(EditText) findViewById(R.id.email_editTextId);
        phone =(EditText) findViewById(R.id.phone_editTextId);
        password =(EditText) findViewById(R.id.password_editTextId);
        quantity = (EditText)findViewById(R.id.product_Quantity_editTextId) ;
        address =(EditText) findViewById(R.id.address_editTextId);
       note =(EditText) findViewById(R.id.note_editTextId);
       orderNow =(Button)findViewById(R.id.submit_btn1);

       iClientServer= RetrofitService.getRetrofitInstance().create(IClientServer.class);
       orderNow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (checkValidity()){
               }else{
                   getProductFromApi();
               }



           }
       });



    }

    public boolean checkValidity() {
        View focusView = null;
        boolean cancel = false;
        String quanti=quantity.getText().toString();
        String name = fullname.getText().toString();
        String emaill =email.getText().toString();
        String addres =address.getText().toString();
        String number =phone.getText().toString();
        String comment =note.getText().toString();
        String pass =password.getText().toString();

        if (TextUtils.isEmpty(name)) {
            // focusView=userName;
            cancel = true;
            fullname.setError("Enter a valid name");
        }
        else if (TextUtils.isEmpty(emaill)) {
            // focusView = pass;

            cancel = true;
            email.setError("Enter a valid occupation");
        }

        else if (TextUtils.isEmpty(number)) {
            // focusView = pass;
            cancel = true;
            phone.setError("Enter a valid Phone Number");
        }
        else if (TextUtils.isEmpty(pass)) {
            // focusView = pass;
            cancel = true;
            password.setError("Enter a valid password");
        }

        else if (TextUtils.isEmpty(addres)) {
            // focusView = pass;
            cancel = true;
            address.setError("Enter a valid address");
        }

        else if (TextUtils.isEmpty(comment)) {
            // focusView = pass;
            cancel = true;
            note.setError("Enter a valid Note");
        }
        return cancel;
    }

  public void getProductFromApi() {
       String api_key = "Cutprice@987";
       String productId=getIntent().getExtras().getString( "productId") ;
       String productSellingPrice=getIntent().getExtras().getString( "sellingPrice") ;
       String quanti=quantity.getText().toString();
        String name = fullname.getText().toString();
         String emaill =email.getText().toString();
         String addres =address.getText().toString();
         String number =phone.getText().toString();
         String pass =password.getText().toString();
         String comment =note.getText().toString();
         ProductOrder paramProductOrder=new ProductOrder();

         paramProductOrder.setApiKey(api_key);
         paramProductOrder.setProductId(productId);
         paramProductOrder.setQuantity(quanti);
         paramProductOrder.setName(name);
         paramProductOrder.setEmail(emaill);
         paramProductOrder.setAddress(addres);
         paramProductOrder.setMobile(number);
         paramProductOrder.setComment(comment);
         paramProductOrder.setProductSellingPrice(productSellingPrice);

        Call<UserOrder>paramProductOrderCall=iClientServer.orderProduct(paramProductOrder);
        paramProductOrderCall.enqueue(new Callback<UserOrder>() {
            @Override
            public void onResponse(Call<UserOrder> call, Response<UserOrder> response) {
                if(response.isSuccessful() && response.body()!=null){
                    UserOrder order=response.body();

                    Toast.makeText(OrderNowActivity.this,"Success :"+ order.getSuccess(),Toast.LENGTH_LONG).show();
                }
                else{

                    Toast.makeText(OrderNowActivity.this,"Not Success !",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserOrder> call, Throwable t) {
                Toast.makeText(OrderNowActivity.this,"message !"+ t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}

