package com.eomsbd.cutprice.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eomsbd.cutprice.OnBackPressed;
import com.eomsbd.cutprice.R;


import com.eomsbd.cutprice.activity.ShoppingActivity;

import es.dmoral.toasty.Toasty;

import static android.content.Context.MODE_PRIVATE;
import static com.eomsbd.cutprice.activity.ProductActivity.mypreference;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements OnBackPressed {

TextView client_name,client_id,client_email,client_order,client_address,client_phone;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_account, container, false);

        client_name=view.findViewById(R.id.profile_nameId);
        client_id=view.findViewById(R.id.profile_id);
        client_address=view.findViewById(R.id.profile_addressId);
        client_email=view.findViewById(R.id.profile_gmailId);
        client_order=view.findViewById(R.id.profile_orderId);




        SharedPreferences sharedPreferences = getContext().getSharedPreferences( mypreference, MODE_PRIVATE );
        String clientId = sharedPreferences.getString( "client_Id", "" );
        String clientName = sharedPreferences.getString( "client_name", "" );


        String clientAddress=sharedPreferences.getString("client_address","");
        String clientEmail=sharedPreferences.getString("client_email","");
        String numberofOrders=sharedPreferences.getString("number_of_orders","");


        client_name.setText(" Name : "+" "+ clientName);
        client_id.setText("Client Id : "+" "+clientId);
        client_address.setText("Address : "+clientAddress);
        client_email.setText("Email : "+clientEmail);

        if (numberofOrders==null){
            client_order.setText("Number of Orders : "+" 0 ");
        }else {
            client_order.setText("Number of Orders : "+ numberofOrders);
        }

        //   Toasty.success(getContext(),"Client id = "+clientId+" client name = "+clientName,Toasty.LENGTH_LONG).show();
        return view;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getActivity(), ShoppingActivity.class));
    }
}
