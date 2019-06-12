package com.eomsbd.cutprice.fragment;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eomsbd.cutprice.R;
import com.eomsbd.cutprice.activity.LoginActivity;
import com.eomsbd.cutprice.model.update_password.SuccessResponse;
import com.eomsbd.cutprice.model.update_password.UpdateResponse;
import com.eomsbd.cutprice.web_api.IClientServer;
import com.eomsbd.cutprice.web_api.RetrofitService;

import org.jetbrains.annotations.NotNull;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdatePassword extends DialogFragment {
    private Button btnSend, btnCancel;
    private EditText _email, _password;



    IClientServer iClientServer;

    OnDataPass dataPasser;
    public UpdatePassword() {
    }


    public interface OnDataPass{
        void OnDataPass(String lat, String lon);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //Validation Code
    public boolean isEmpty(EditText text){
        CharSequence t=text.getText().toString();
        return TextUtils.isEmpty( t );
    }
    public boolean checkValidity() {
        View focusView = null;
        boolean cancel = false;

        if (isEmpty(_email)) {
            // focusView = pass;
            cancel = true;
            _email.setError("Enter a valid  Email");
        }


        if (isEmpty(_password)) {
            // focusView = pass;
            cancel = true;
            _password.setError("Enter a valid Password");
        }

        return cancel;
    }

    //validation End


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_update_password, container, false);
        btnSend = rootView.findViewById(R.id.btnSend);
        btnCancel = rootView.findViewById(R.id.btnCancel);
        _email = rootView.findViewById(R.id.update_email_Id);
        _password = rootView.findViewById(R.id.update_password_Id);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValidity()){


                }else{

                    getUpdatePasswordFormApi();
                }


            }

        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return rootView;
    }

    private void getUpdatePasswordFormApi() {
        String api="Cutprice@987";
        String s1 = _email.getText().toString();
        String s2 = _password.getText().toString();
        iClientServer= RetrofitService.getRetrofitInstance().create(IClientServer.class);

        UpdateResponse updateResponse= new UpdateResponse();
        updateResponse.setApiKey(api);
        updateResponse.setEmail(s1);
        updateResponse.setPassword(s2);

        Call<SuccessResponse>successResponseCall=iClientServer.getUpdatePassword(updateResponse);

        successResponseCall.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                SuccessResponse successResponse=response.body();

                if (response.isSuccessful() && successResponse!=null){
                    Toasty.error(getContext(),"Not Successful " + successResponse.getSuccess(),Toasty.LENGTH_LONG).show();

                }else{
                    startActivity(new Intent(getContext(), LoginActivity.class));
                    Toasty.success(getContext(),"Password Update Successfully " + successResponse.getSuccess(),Toasty.LENGTH_LONG).show();


                }
            }

            @Override
            public void onFailure(Call<SuccessResponse> call, Throwable t) {

            }
        });

    }


    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded);
        return dialog;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // dataPasser = (OnDataPass) context;
/*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }



    @Override
    public void onResume() {
        Window window = getDialog().getWindow();
        Point size = new Point();
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        window.setLayout((int) (size.x * 0.90), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        super.onResume();
    }

}
