package com.inducesmile.androidpayexample;

import android.annotation.SuppressLint;
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
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.inducesmile.androidpayexample.Interface.NavigationManager;
import com.inducesmile.androidpayexample.adapters.CustomExpandableListAdapter;
import com.inducesmile.androidpayexample.adapters.ShopRecyclerViewAdapter;
import com.inducesmile.androidpayexample.fragment.AccountFragment;
import com.inducesmile.androidpayexample.fragment.CartFragment;
import com.inducesmile.androidpayexample.fragment.CategoryFragment;
import com.inducesmile.androidpayexample.fragment.HomeFragment;
import com.inducesmile.androidpayexample.fragment.VideoFragment;
import com.inducesmile.androidpayexample.helpers.BottomNavigationBehavior;
import com.inducesmile.androidpayexample.helpers.FragmentNavigationManager;
import com.inducesmile.androidpayexample.helpers.SpacesItemDecoration;
import com.inducesmile.androidpayexample.model.products_model.Datum;
import com.inducesmile.androidpayexample.model.products_model.Products;
import com.inducesmile.androidpayexample.web_api.IClientServer;
import com.inducesmile.androidpayexample.web_api.RetrofitService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingActivity extends AppCompatActivity {

    private static final String TAG = ShoppingActivity.class.getSimpleName();


    IClientServer iClientServer;
    ProgressDialog progressDialog;
    public static int index = 1;

    private RecyclerView shoppingRecyclerView;

    private ActionBar toolbar;
    FrameLayout coordinatorLayout;
    CustomExpandableListAdapter adapter;
    // PageAdapter pageAdapter;
    TabItem tabItem1;
    ShopRecyclerViewAdapter shopAdapter;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private String mActivtiyTile;
    private String[] items;
    private ExpandableListView expandableListView;
    private List<String> lstTitle;


    //TablayOut

    TabLayout tabLayout;
    private Map<String, List<String>> lstChild;
    TabItem tabItem2;
    TabItem tabItem3;
    private NavigationManager navigationManager;
    private ArrayList<Datum> data;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
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

  /*  @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
*/
    /*@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }*/

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        // mDrawerLayout = findViewById(R.id.colayout);
        mActivtiyTile = getTitle().toString();
        navigationManager = FragmentNavigationManager.getInstance(this);

        View listviewHeader = getLayoutInflater().inflate(R.layout.nav_header, null, false);
//        expandableListView.addHeaderView(listviewHeader);


        //progressbar Code
        progressDialog = new ProgressDialog(ShoppingActivity.this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
       /* tabLayout = findViewById(R.id.tab_layout);
        tabItem1 = findViewById(R.id.tabItem1);
        tabItem2 = findViewById(R.id.tabItem2);
        tabItem3 = findViewById(R.id.tabItem3);
        data = new ArrayList<>();
        initItems();
        getData();
        addDrawerItem();
        setUpDrawer();
        if (savedInstanceState == null)
            selectFirstItemAsDefault();
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Cutprice");*/


        coordinatorLayout = findViewById(R.id.frame_container);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        iClientServer = RetrofitService.getRetrofitInstance().create(IClientServer.class);
        getProductsFromApi();
        toolbar = getSupportActionBar();
        ////-0---

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        // load the store fragment by default
        //toolbar.setTitle("Shop");
        // loadFragment(new HomeFragment());
    }

    private void selectFirstItemAsDefault() {
        if (navigationManager != null) {
            String firstItem = lstTitle.get(0);
            navigationManager.showFragment(firstItem);
            getSupportActionBar().setTitle(firstItem);
        }
    }

    private void setUpDrawer() {
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Cutprice");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(mActivtiyTile);
                invalidateOptionsMenu();
            }
        };
        drawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(drawerToggle);
    }

    private void addDrawerItem() {
        adapter = new CustomExpandableListAdapter(this, lstTitle, lstChild);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                getSupportActionBar().setTitle(lstTitle.get(groupPosition));
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                getSupportActionBar().setTitle("CutPriceBD");
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String seloectItem = (lstChild.get(lstTitle.get(groupPosition))).get(childPosition);

                getSupportActionBar().setTitle(seloectItem);
                if (items[0].equals(lstTitle.get(groupPosition)))
                    navigationManager.showFragment(seloectItem);
                else
                    throw new IllegalArgumentException("Not Supported fragment");
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

    }

    private void getData() {
        List<String> title = Arrays.asList("Android Programming", "Xamarin Programming", "IOS programming");
        List<String> childItem = Arrays.asList("Beginner", "Intermediate", "Advanced", "Professional");
        lstChild = new TreeMap<>();
        lstChild.put(title.get(0), childItem);
        lstChild.put(title.get(1), childItem);
        lstChild.put(title.get(2), childItem);
        lstTitle = new ArrayList<>(lstChild.keySet());
    }
///

    private void initItems() {
        items = new String[]{
                "Android Programming", "Xamarin Programming", "IOS programming"};
    }

    private boolean loadFragment(Fragment fragment) {
        // load fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
            coordinatorLayout.setVisibility(View.GONE);

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
            if (f != null && f instanceof HomeFragment) {
                ((HomeFragment) f).onBackPressed();
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
        final GridLayoutManager mGrid = new GridLayoutManager(ShoppingActivity.this, 2);
        shoppingRecyclerView.setLayoutManager(mGrid);
        shoppingRecyclerView.setHasFixedSize(true);
        shoppingRecyclerView.addItemDecoration(new SpacesItemDecoration(2, 12, false));


        shoppingRecyclerView.getRecycledViewPool().setMaxRecycledViews(0, 10);
        shoppingRecyclerView.setItemViewCacheSize(10);
        shoppingRecyclerView.setHasFixedSize(true);
//Set the Adapter to the RecyclerView//
        shoppingRecyclerView.setAdapter(shopAdapter);
        shopAdapter.notifyDataSetChanged();


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        // getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(ShoppingActivity.this, SettingsPrefActivity.class));
            return true;
        }
       /* if (drawerToggle.onOptionsItemSelected(item))
            return true;*/
        return super.onOptionsItemSelected(item);
    }

        /*public void tabInit() {
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


}
