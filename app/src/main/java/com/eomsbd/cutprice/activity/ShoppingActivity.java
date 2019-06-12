package com.eomsbd.cutprice.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eomsbd.cutprice.R;
import com.eomsbd.cutprice.adapters.ShopRecyclerViewAdapter;
import com.eomsbd.cutprice.adapters.ShopRecyclerViewAdapter1;
import com.eomsbd.cutprice.fragment.AccountFragment;
import com.eomsbd.cutprice.fragment.CartFragment;
import com.eomsbd.cutprice.fragment.CategoryFragment;
import com.eomsbd.cutprice.fragment.FacebookFragment;
import com.eomsbd.cutprice.fragment.VideoFragment;
import com.eomsbd.cutprice.helpers.BottomNavigationBehavior;
import com.eomsbd.cutprice.helpers.Session;
import com.eomsbd.cutprice.helpers.SpacesItemDecoration;
import com.eomsbd.cutprice.helpers.User;
import com.eomsbd.cutprice.model.products_model.Datum;
import com.eomsbd.cutprice.model.products_model.Products;
import com.eomsbd.cutprice.tabLayoutFragment.Tab11Fragment;
import com.eomsbd.cutprice.tabLayoutFragment.Tab1Fragment;
import com.eomsbd.cutprice.tabLayoutFragment.Tab2Fragment;
import com.eomsbd.cutprice.tabLayoutFragment.Tab3Fragment;
import com.eomsbd.cutprice.tabLayoutFragment.Tab4Fragment;
import com.eomsbd.cutprice.tabLayoutFragment.Tab5Fragment;
import com.eomsbd.cutprice.tabLayoutFragment.Tab6Fragment;
import com.eomsbd.cutprice.tabLayoutFragment.Tab7Fragment;
import com.eomsbd.cutprice.tabLayoutFragment.Tab8Fragment;
import com.eomsbd.cutprice.tabLayoutFragment.Tab9Fragment;
import com.eomsbd.cutprice.web_api.IClientServer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShoppingActivity extends AppCompatActivity {

    private static final String TAG = ShoppingActivity.class.getSimpleName();
    public static final String API_KEY = "Cutprice@987";
    public static final int INDEX = 1;
    IClientServer iClientServer;
    ProgressDialog progressDialog;
    public static int index = 1;
    private RecyclerView shoppingRecyclerView, shoppingRecyclerView1;
    int cacheSize = 10 * 1024 * 1024; // 10 MiB
    private ActionBar toolbar;
    FrameLayout coordinatorLayout;

    LinearLayout linearLayout;
    AlertDialog.Builder alertDialogBuilder;

    // Session Manager Class
    Session session;

    //TablayOut

    TabLayout tabLayout;
    TabItem tabItem1;
    TabItem tabItem2;
    TabItem tabItem3;
    private ArrayList<Datum> data;

    ShopRecyclerViewAdapter shopAdapter;
    ShopRecyclerViewAdapter1 shopAdapter1;
    PageAdapter pageAdapter;
    ViewPager viewPager;


    boolean isScrolling = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        //progressbar Code
      /*  session = new Session(getApplicationContext());
        session.checkLogin();*/
        AppBarLayout appBarLayout = findViewById(R.id.info);
        linearLayout = findViewById(R.id.LinearLayout1);
      /*  tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));*/
        //tabLayout = findViewById(R.id.tab_layout);

     /*   tabLayout.setLayoutParams(new LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 50));
     //   appBarLayout.addView(tabLayout);


        pageAdapter = new PageAdapter(getSupportFragmentManager());
       // pageAdapter = new PageAdapter(getFragmentManager());
     //   viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(pageAdapter);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

*/
        /* tabItem1 = findViewById(R.id.tabItem1);

        tabItem2 = findViewById(R.id.tabItem2);
        tabItem3 = findViewById(R.id.tabItem3);*/
        data = new ArrayList<>();

        /*tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
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
        });*/

        coordinatorLayout = findViewById(R.id.frame_container);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        progressDialog = new ProgressDialog(ShoppingActivity.this);
        progressDialog.setMessage("please wait for a minute.....");
        progressDialog.setCancelable(false);
        progressDialog.show();
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
                case R.id.navigation_home:
                    startActivity(new Intent(ShoppingActivity.this, ShoppingActivity.class));
                    break;

                case R.id.navigation_video:
                    toolbar.setTitle("Video");
                    fragment = new VideoFragment();
                    linearLayout.setVisibility(View.GONE);
                    break;

                case R.id.navigation_profile:
                    toolbar.setTitle("Profile");
                    fragment = new AccountFragment();
                    linearLayout.setVisibility(View.GONE);
                    break;
            }

            return loadFragment(fragment);
        }
    };

    public Activity getActivity() {
        Context context = this;
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;

    }

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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    ////  Get Data From Api
    public void getProductsFromApi1() {
        try {
            if (API_KEY.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain API Key firstly from themoviedb.org", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                return;
            }
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build();

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("http://cutpricebd.com/oms/api/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit = builder.build();
            iClientServer = retrofit.create(IClientServer.class);

            // final Call<Products> productsCall = iClientServer.getALlProducts(API_KEY, 0, 100);
            final Call<Products> productsCall1 = iClientServer.getALlProducts1(API_KEY, 100, 500);

            productsCall1.enqueue(new Callback<Products>() {
                @Override
                public void onResponse(Call<Products> call, final Response<Products> response) {
                    Products products = response.body();
                    loadDataList1(products.getData());

                }

                @Override
                public void onFailure(Call<Products> call, Throwable t) {

                }
            });

        } catch (Exception e) {

        }

    }


    public void getProductsFromApi() {
        try {
            if (API_KEY.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please obtain API Key firstly from themoviedb.org", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                return;
            }
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build();

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("http://cutpricebd.com/oms/api/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit = builder.build();
            iClientServer = retrofit.create(IClientServer.class);

            final Call<Products> productsCall = iClientServer.getALlProducts(API_KEY, 0, 1000);
            // final Call<Products> productsCall1 = iClientServer.getALlProducts1(API_KEY, 100);

            productsCall.enqueue(new Callback<Products>() {
                @Override
                public void onResponse(Call<Products> call, final Response<Products> response) {
                    Products products = response.body();

                    progressDialog.dismiss();
                    loadDataList(products.getData());

                }

                @Override
                public void onFailure(Call<Products> call, Throwable t) {
                    alertDialogBuilder = new AlertDialog.Builder(ShoppingActivity.this);

                    //For Setting Title

                    //for setting message
                    //for setting Icon
                    alertDialogBuilder.setIcon(getResources().getDrawable(R.drawable.wifi));
                    alertDialogBuilder.setMessage(R.string.message_text2);

                    alertDialogBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //exit
                            progressDialog.dismiss();
                        }
                    });
                    alertDialogBuilder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            });
        } catch (Exception e) {

        }

    }

    private void loadDataList1(List<Datum> data) {
        //Get a reference to the RecyclerView//
        shoppingRecyclerView1 = findViewById(R.id.product_list);

        shopAdapter1 = new ShopRecyclerViewAdapter1(ShoppingActivity.this, data);


//Use a LinearLayoutManager with default vertical orientation//

        //  GridLayoutManager mGrid = new GridLayoutManager(ShoppingActivity.this, 2);
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            shoppingRecyclerView1.setLayoutManager(new GridLayoutManager(this, 2));
            shoppingRecyclerView1.addItemDecoration(new SpacesItemDecoration(2, 12, false));

        } else {
            shoppingRecyclerView1.setLayoutManager(new GridLayoutManager(this, 4));
            shoppingRecyclerView1.addItemDecoration(new SpacesItemDecoration(4, 6, false));
        }

        //shoppingRecyclerView.setLayoutManager(mGrid);
        shoppingRecyclerView1.setHasFixedSize(true);

        //Give animation
        shoppingRecyclerView1.setItemAnimator(new DefaultItemAnimator());
//Set the Adapter to the RecyclerView//
        shoppingRecyclerView1.setAdapter(shopAdapter);
        shoppingRecyclerView1.smoothScrollToPosition(0);
        shopAdapter1.notifyDataSetChanged();
    }

    //

    @Override
    public void onBackPressed() {
        tellFragments();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

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
            } /*else if (f != null && f instanceof AccountFragment) {
                ((AccountFragment) f).onBackPressed();
            }*/
        }
    }

//Display the retrieved data as a list//

    private void loadDataList(List<Datum> usersList) {
//Get a reference to the RecyclerView//
        shoppingRecyclerView = findViewById(R.id.product_list);

        shopAdapter = new ShopRecyclerViewAdapter(ShoppingActivity.this, usersList);


//Use a LinearLayoutManager with default vertical orientation//

        //  GridLayoutManager mGrid = new GridLayoutManager(ShoppingActivity.this, 2);
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            shoppingRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            shoppingRecyclerView.addItemDecoration(new SpacesItemDecoration(2, 12, false));

        } else {
            shoppingRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
            shoppingRecyclerView.addItemDecoration(new SpacesItemDecoration(4, 6, false));
        }

        //shoppingRecyclerView.setLayoutManager(mGrid);
        shoppingRecyclerView.setHasFixedSize(true);


        //Give animation
        shoppingRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //Set the Adapter to the RecyclerView//
        shoppingRecyclerView.setAdapter(shopAdapter);
        shoppingRecyclerView.smoothScrollToPosition(0);
        shopAdapter.notifyDataSetChanged();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsPrefActivity.class));
                return true;
            case R.id.action_logout:
                User.saveSharedSetting(ShoppingActivity.this, "CaptainCode", "true");
                //And when you click on Logout button, You will set the value to True AGAIN
                Intent LogOut = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(LogOut);
                finish();


            default:
                return super.onOptionsItemSelected(item);
        }
        //respond to menu item selection

    }

    public void CekSession(){

        Boolean Check = Boolean.valueOf(User.readSharedSetting(ShoppingActivity.this, "CaptainCode", "true"));

        Intent introIntent = new Intent(ShoppingActivity.this, LoginActivity.class);
        introIntent.putExtra("CaptainCode", Check);

        //The Value if you click on Login Activity and Set the value is FALSE and whe false the activity will be visible
        if (Check) {
            startActivity(introIntent);
            finish();
        } //If no the Main Activity not Do Anything
    }

    /* public void tabInit() {
        tabLayout = findViewById(R.id.tab_layout);
    }*/

    public class PageAdapter extends FragmentPagerAdapter {
        private int numOfTabs;

        public PageAdapter(FragmentManager fm) {
            super(fm);

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
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "First Tab";
                case 1:
                default:
                    return "Second Tab";
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

}