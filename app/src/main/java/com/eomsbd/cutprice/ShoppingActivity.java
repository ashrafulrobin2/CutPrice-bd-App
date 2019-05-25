package com.eomsbd.cutprice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eomsbd.cutprice.adapters.ShopRecyclerViewAdapter;
import com.eomsbd.cutprice.fragment.AccountFragment;
import com.eomsbd.cutprice.fragment.CartFragment;
import com.eomsbd.cutprice.fragment.CategoryFragment;
import com.eomsbd.cutprice.fragment.FacebookFragment;
import com.eomsbd.cutprice.fragment.VideoFragment;
import com.eomsbd.cutprice.helpers.BottomNavigationBehavior;
import com.eomsbd.cutprice.helpers.SpacesItemDecoration;
import com.eomsbd.cutprice.model.products_model.Datum;
import com.eomsbd.cutprice.model.products_model.Products;
import com.eomsbd.cutprice.web_api.IClientServer;
import com.eomsbd.cutprice.web_api.RetrofitService;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingActivity extends AppCompatActivity  {

    private static final String TAG = ShoppingActivity.class.getSimpleName();


    IClientServer iClientServer;
    ProgressDialog progressDialog;
    public static int index = 1;

    private RecyclerView shoppingRecyclerView;

    private ActionBar toolbar;
    FrameLayout coordinatorLayout;

    LinearLayout linearLayout;

    //TablayOut

    TabLayout tabLayout;
    TabItem tabItem1;
    TabItem tabItem2;
    TabItem tabItem3;
    private ArrayList<Datum> data;

    ShopRecyclerViewAdapter shopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        //progressbar Code
        progressDialog = new ProgressDialog(ShoppingActivity.this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();

        linearLayout=findViewById(R.id.LinearLayout1);
    /*    tabLayout = findViewById(R.id.tab_layout);
        tabItem1 = findViewById(R.id.tabItem1);
        tabItem2 = findViewById(R.id.tabItem2);
        tabItem3 = findViewById(R.id.tabItem3);*/
        data = new ArrayList<>();

       /* tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                if (tab.getPosition() == 0) {
                    index = 1;
                    Tab1Fragment fragment = new Tab1Fragment();
                    transaction.replace(R.id.frame_container, fragment).commit();
                } else if (tab.getPosition() == 1) {
                    index = 2;
                    Tab2Fragment fragment = new Tab2Fragment();
                    transaction.replace(R.id.frame_container, fragment).commit();
                } else if (tab.getPosition() == 2) {
                    index = 3;
                    Tab3Fragment fragment = new Tab3Fragment();
                    transaction.replace(R.id.frame_container, fragment).commit();
                } else if (tab.getPosition() == 3) {
                    index = 4;
                    Tab4Fragment fragment = new Tab4Fragment();
                    transaction.replace(R.id.frame_container, fragment).commit();
                } else if (tab.getPosition() == 4) {
                    index = 5;
                    Tab5Fragment fragment = new Tab5Fragment();
                    transaction.replace(R.id.frame_container, fragment).commit();
                } else if (tab.getPosition() == 5) {
                    index = 6;
                    Tab6Fragment fragment = new Tab6Fragment();
                    transaction.replace(R.id.frame_container, fragment).commit();
                } else if (tab.getPosition() == 6) {
                    index = 7;
                    Tab7Fragment fragment = new Tab7Fragment();
                    transaction.replace(R.id.frame_container, fragment).commit();
                } else if (tab.getPosition() == 7) {
                    index = 8;
                    Tab8Fragment fragment = new Tab8Fragment();
                    transaction.replace(R.id.frame_container, fragment).commit();
                } else if (tab.getPosition() == 8) {
                    index = 9;
                    Tab9Fragment fragment = new Tab9Fragment();
                    transaction.replace(R.id.frame_container, fragment).commit();
                } else if (tab.getPosition() == 9) {
                    index = 10;
                    Tab10Fragment fragment = new Tab10Fragment();
                    transaction.replace(R.id.frame_container, fragment).commit();
                } else if (tab.getPosition() == 10) {
                    index = 11;
                    Tab11Fragment fragment = new Tab11Fragment();
                    transaction.replace(R.id.frame_container, fragment).commit();
                } else {
                    Toast.makeText(ShoppingActivity.this, "no Data available", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


*/
        coordinatorLayout = findViewById(R.id.frame_container);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        iClientServer = RetrofitService.getRetrofitInstance().create(IClientServer.class);
        getProductsFromApi();
        toolbar = getSupportActionBar();
        ////-0---

        //Bottom Navigation //

          BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        // load the store fragment by default
        //toolbar.setTitle("Shop");
        // loadFragment(new HomeFragment());
    }
///

   private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_facebook:
                    toolbar.setTitle("Facebook");
                    fragment = new FacebookFragment();
                    linearLayout.setVisibility(View.GONE);
                    break;
                case R.id.navigation_category:
                    toolbar.setTitle("Category");
                    fragment = new CategoryFragment();
                    linearLayout.setVisibility(View.GONE);
                    break;
                /*case R.id.navigation_cart:
                    toolbar.setTitle("Cart");
                    fragment = new CartFragment();
                    linearLayout.setVisibility(View.GONE);
                    break;*/

                case R.id.navigation_video:
                    toolbar.setTitle("Video");
                    fragment = new VideoFragment();
                   linearLayout.setVisibility(View.GONE);
                    break;

                case R.id.navigation_account:
                    toolbar.setTitle("Account");
                    fragment = new AccountFragment();
                    linearLayout.setVisibility(View.GONE);
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
        /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();*/
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    ////  Get Data From Api
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

    //
    private void tellFragments() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment f : fragments) {
            if (f != null && f instanceof FacebookFragment) {
                ((FacebookFragment) f).onBackPressed();
            } else if (f != null && f instanceof CategoryFragment) {
                ((CategoryFragment) f).onBackPressed();
            } else if (f != null && f instanceof CartFragment) {
                ((CartFragment) f).onBackPressed();
            } else if (f != null && f instanceof VideoFragment) {
                ((VideoFragment) f).onBackPressed();
            } else if (f != null && f instanceof AccountFragment) {
                ((AccountFragment) f).onBackPressed();
            }
        }
    }

//Display the retrieved data as a list//

    private void loadDataList(List<Datum> usersList) {

//Get a reference to the RecyclerView//

        shoppingRecyclerView = findViewById(R.id.product_list);

        shopAdapter = new ShopRecyclerViewAdapter(ShoppingActivity.this, usersList);


//Use a LinearLayoutManager with default vertical orientation//

        GridLayoutManager mGrid = new GridLayoutManager(ShoppingActivity.this, 2);
        shoppingRecyclerView.setLayoutManager(mGrid);
        shoppingRecyclerView.setHasFixedSize(true);
        shoppingRecyclerView.addItemDecoration(new SpacesItemDecoration(2, 12, false));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(shoppingRecyclerView.getContext(),DividerItemDecoration.VERTICAL);
        shoppingRecyclerView.addItemDecoration(dividerItemDecoration);
        //Give animation
        shoppingRecyclerView.setItemAnimator(new DefaultItemAnimator());
//Set the Adapter to the RecyclerView//
        shoppingRecyclerView.setAdapter(shopAdapter);
        shopAdapter.notifyDataSetChanged();





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;    }


        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_settings:
                    startActivity(new Intent(this, SettingsPrefActivity.class));
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        //respond to menu item selection

    }


    /* public void tabInit() {
        tabLayout = findViewById(R.id.tab_layout);
    }*/

   /* public class PageAdapter extends FragmentPagerAdapter {

        private int numOfTabs;

        PageAdapter(FragmentManager fm, int numOfTabs) {
            super(fm);
            this.numOfTabs = numOfTabs;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Tab1Fragment();
                case 1:
                    return new Tab2Fragment();
                case 2:
                    return new Tab3Fragment();

                case 3:
                    return new Tab4Fragment();

                case 4:
                    return new Tab5Fragment();

                case 5:
                    return new Tab6Fragment();

                case 6:
                    return new Tab7Fragment();

                case 7:
                    return new Tab8Fragment();

                case 8:
                    return new Tab9Fragment();

                case 10:
                    return new Tab11Fragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return numOfTabs;
        }
    }
*/




}
