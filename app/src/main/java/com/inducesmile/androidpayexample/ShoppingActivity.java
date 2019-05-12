package com.inducesmile.androidpayexample;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.inducesmile.androidpayexample.Fragment.AccountFragment;
import com.inducesmile.androidpayexample.Fragment.CartFragment;
import com.inducesmile.androidpayexample.Fragment.CategoryFragment;
import com.inducesmile.androidpayexample.Fragment.HomeFragment;
import com.inducesmile.androidpayexample.Fragment.VideoFragment;
import com.inducesmile.androidpayexample.adapters.ShopRecyclerViewAdapter;
import com.inducesmile.androidpayexample.helpers.BottomNavigationBehavior;
import com.inducesmile.androidpayexample.helpers.SpacesItemDecoration;
import com.inducesmile.androidpayexample.model.products_model.Datum;
import com.inducesmile.androidpayexample.model.products_model.Products;
import com.inducesmile.androidpayexample.web_api.IClientServer;
import com.inducesmile.androidpayexample.web_api.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingActivity extends AppCompatActivity {

    private static final String TAG = ShoppingActivity.class.getSimpleName();


    IClientServer iClientServer;
    ProgressDialog progressDialog;

    private RecyclerView shoppingRecyclerView;

    private ActionBar toolbar;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        progressDialog = new ProgressDialog(ShoppingActivity.this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        coordinatorLayout=findViewById(R.id.container);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        iClientServer = RetrofitService.getRetrofitInstance().create(IClientServer.class);
        getProductsFromApi();
        toolbar = getSupportActionBar();
        ////-0---

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        // load the store fragment by default
        toolbar.setTitle("Shop");
       //loadFragment(new HomeFragment());
    }
///

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment=null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("Home");
                    fragment = new HomeFragment();
               break;
                case R.id.navigation_category:
                    toolbar.setTitle("Category");
                    fragment = new CategoryFragment();

                   break;
                case R.id.navigation_cart:
                    toolbar.setTitle("Cart");
                    fragment = new CartFragment();
                  break;

                case R.id.navigation_video:
                    toolbar.setTitle("Video");
                    fragment = new VideoFragment();
                  break;

                case R.id.navigation_account:
                    toolbar.setTitle("Account");
                    fragment = new AccountFragment();
                    break;
            }

            return loadFragment(fragment);
        }
    };

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    private boolean loadFragment(Fragment fragment) {
        // load fragment
        if (fragment !=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
            coordinatorLayout.setVisibility(View.GONE);

            return true;
        }
        return false;

    }
    ////
    public void getProductsFromApi() {
        String id = "Cutprice@987";
        final Call<Products> productsCall = iClientServer.getALlProducts(id);

        productsCall.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                Products products = response.body();
                progressDialog.dismiss();
                loadDataList(products.getData());
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Toast.makeText(ShoppingActivity.this, "Unable to load users " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //

    @Override
    public void onBackPressed() {

        tellFragments();
        super.onBackPressed();
    }

    private void tellFragments() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment f : fragments) {
            if (f != null && f instanceof HomeFragment) {
                ((HomeFragment) f).onBackPressed();
            } else if (f != null && f instanceof CategoryFragment) {
                ((CategoryFragment) f).onBackPressed();
            }else if (f != null && f instanceof CartFragment) {
                ((CartFragment) f).onBackPressed();
            } else if (f != null && f instanceof VideoFragment) {
                (( VideoFragment) f).onBackPressed();
            } else if (f != null && f instanceof AccountFragment) {
                ((AccountFragment) f).onBackPressed();
            }
        }
    }

//Display the retrieved data as a list//

    private void loadDataList(List<Datum> usersList) {

//Get a reference to the RecyclerView//

        shoppingRecyclerView = findViewById(R.id.product_list);

        ShopRecyclerViewAdapter shopAdapter = new ShopRecyclerViewAdapter(ShoppingActivity.this, usersList);


//Use a LinearLayoutManager with default vertical orientation//

        GridLayoutManager mGrid = new GridLayoutManager(ShoppingActivity.this, 3);
        shoppingRecyclerView.setLayoutManager(mGrid);
        shoppingRecyclerView.setHasFixedSize(true);
        shoppingRecyclerView.addItemDecoration(new SpacesItemDecoration(3, 8, false));


//Set the Adapter to the RecyclerView//
        shoppingRecyclerView.setAdapter(shopAdapter);
        shopAdapter.notifyDataSetChanged();
    }

}
