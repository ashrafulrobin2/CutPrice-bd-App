package com.eomsbd.cutprice.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.eomsbd.cutprice.R;




public class Test_Activity extends AppCompatActivity {
EditText editText;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        editText=findViewById(R.id.edit_text);
        button=findViewById(R.id.button2);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEmailFromApi();
            }
        });
    }

    private void getEmailFromApi() {


    }
}
