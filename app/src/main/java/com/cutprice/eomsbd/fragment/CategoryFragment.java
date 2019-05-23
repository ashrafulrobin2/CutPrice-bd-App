package com.cutprice.eomsbd.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cutprice.eomsbd.OnBackPressed;
import com.cutprice.eomsbd.R;
import com.cutprice.eomsbd.ShoppingActivity;
import com.cutprice.eomsbd.model.category_model.Category;
import com.cutprice.eomsbd.model.category_model.Data;
import com.cutprice.eomsbd.web_api.IClientServer;
import com.cutprice.eomsbd.web_api.RetrofitService;

import java.util.Collections;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements OnBackPressed {

    RecyclerView recyclerView;
    IClientServer iClientServer;
    SharedPreferences sharedPreference;
    public static final String mypreference = "mypref";

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences( mypreference, MODE_PRIVATE );
        String cat0 = sharedPreferences.getString( "cat0", "" );
        String cat1 = sharedPreferences.getString( "cat1", "" );
        Toasty.success(getContext(),""+cat0,Toasty.LENGTH_LONG).show();


        iClientServer = RetrofitService.getRetrofitInstance().create(IClientServer.class);
        getCategoryData();
        return view;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getActivity(), ShoppingActivity.class));
    }


    public void getCategoryData() {


        Call<Category> categoryCall = iClientServer.getSubmenu();
        categoryCall.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                Category category = response.body();

                getCategoryDataFromApi(category.getData());


            }


            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                Toasty.error(getContext(), "Errrooooor" + t.getMessage(), Toasty.LENGTH_LONG).show();
            }
        });

    }

    private void getCategoryDataFromApi(List<Data> data) {


        sharedPreference = getContext().getSharedPreferences(mypreference, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreference.edit();
        int size=data.size();

        for (int i=0;i<size;i++){
            if (i==0){
                int siz=data.get(i).getSubmenu().size();
                for (int j=0;j<siz;j++){
                   String value=data.get(i).getSubmenu().get(j).getCategoriesName().toString();
                        editor.putString("cat0", value);
                   //Toasty.success(getContext(),""+value,Toasty.LENGTH_LONG).show();
                }
            }else if (i==1){
                int siz=data.get(i).getSubmenu().size();
                for (int j=0;j<siz;j++){
                    String value=data.get(i).getSubmenu().get(j).getCategoriesName().toString();
                    editor.putString("cat1",value);
                   // Toasty.success(getContext(),""+value,Toasty.LENGTH_LONG).show();
                }

            }else if (i==2){
                int siz=data.get(i).getSubmenu().size();
                for (int j=0;j<siz;j++){
                    String value=data.get(i).getSubmenu().get(j).getCategoriesName().toString();
                    editor.putString("cat2",value);
                   // Toasty.success(getContext(),""+value,Toasty.LENGTH_LONG).show();
                }
            }else if (i==3){
                int siz=data.get(i).getSubmenu().size();
                for (int j=0;j<siz;j++){
                    String value=data.get(i).getSubmenu().get(j).getCategoriesName().toString();
                    editor.putString("cat3",value);
                    //Toasty.success(getContext(),""+value,Toasty.LENGTH_LONG).show();
                }
            }else if (i==4){
                int siz=data.get(i).getSubmenu().size();
                for (int j=0;j<siz;j++){
                    String value=data.get(i).getSubmenu().get(j).getCategoriesName().toString();
                    editor.putString("cat4",value);
                    //Toasty.success(getContext(),""+value,Toasty.LENGTH_LONG).show();
                }
            }else if (i==5){

            }else if (i==6){
                int siz=data.get(i).getSubmenu().size();
                for (int j=0;j<siz;j++){
                    String value=data.get(i).getSubmenu().get(j).getCategoriesName().toString();
                    editor.putString("cat5",value);
                   // Toasty.success(getContext(),""+value,Toasty.LENGTH_LONG).show();
                }
            }else if (i==7){

            }else if (i==8){

            }
        }
        editor.apply();
        editor.commit();
    }
}
