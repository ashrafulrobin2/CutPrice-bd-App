package com.eomsbd.cutprice.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.eomsbd.cutprice.adapters.helpers.MySharedPreference;
import com.eomsbd.cutprice.adapters.model.products_model.Datum;
import com.eomsbd.cutprice.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    private static final String TAG = ProductActivity.class.getSimpleName();

    public static final String mypreference = "mypref";

    private ImageView productImage;
    SharedPreferences sharedPreference;

    private Gson gson;
    private TextView productId, productDiscount, productPrice, productDescription;
    private int cartProductNumber = 0;
    private Context context = ProductActivity.this;
    private MySharedPreference sharedPreference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        sharedPreference1 = new MySharedPreference(ProductActivity.this);

        productImage = findViewById(R.id.full_product_image);
        productId = findViewById(R.id.product_size);
        productDiscount = findViewById(R.id.product_color);
        productPrice = findViewById(R.id.product_price);
        productDescription = findViewById(R.id.product_description);

        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();

      String productInStringFormat = getIntent().getExtras().getString("PRODUCT");
    final Datum datum = gson.fromJson(productInStringFormat, Datum.class);

        if (datum != null) {
            setTitle(datum.getProductName());
            String path = "http://cutpricebd.com/oms/product_image/thumbs/";
           Picasso.get().load(path + datum.getImg1()).into(productImage);


            if (datum.getProductOldPrice() == null) {
                productDiscount.setText(" " + "0 TK");

                productDiscount.setPaintFlags(productDiscount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                double oldPrice = Double.parseDouble(datum.getProductOldPrice());
                double sellingPrice = Double.parseDouble(datum.getProductSellingPrice());
                double discountPrice = oldPrice - sellingPrice;
                productDiscount.setText(" " + discountPrice);

                productDiscount.setPaintFlags(productDiscount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
            String productId1 = datum.getProductId();
            String sellingPrice = datum.getProductSellingPrice();
            sharedPreference = context.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreference.edit();
            editor.putString("productId", productId1);
            editor.putString("sellingPrice", sellingPrice);
            editor.apply();
            editor.commit();
            //   productImage.setImageResource(singleProduct.getProductImage());
            productId.setText("Product Id: " + productId1);
            productPrice.setText("Price: " + sellingPrice + " TK");
            productDescription.setText(Html.fromHtml("<strong>Product Description</strong><br/>" + datum.getProductDescription()));
        }

        Button addToCartButton = findViewById(R.id.order_Now_btn1);
        assert addToCartButton != null;
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences sharedPreferences = getSharedPreferences( mypreference, MODE_PRIVATE );
                String productId = sharedPreferences.getString( "productId", "" );
                String sellingPrice = sharedPreferences.getString( "sellingPrice", "" );
                Intent intent = new Intent( context, OrderNowActivity.class );
                intent.putExtra( "productId", productId );
                intent.putExtra( "sellingPrice", sellingPrice );
                startActivity(intent);
                //increase product count
              /*  String productsFromCart = sharedPreference1.retrieveProductFromCart();
                if(productsFromCart.equals("")){
                    List<Datum> cartProductList = new ArrayList<Datum>();
                    cartProductList.add(datum);
                    String cartValue = gson.toJson(cartProductList);
                    sharedPreference1.addProductToTheCart(cartValue);
                    cartProductNumber = cartProductList.size();
                }else{
                    String productsInCart = sharedPreference1.retrieveProductFromCart();
                    Datum[] storedProducts = gson.fromJson(productsInCart, Datum[].class);

                    List<Datum> allNewProduct = convertObjectArrayToListObject(storedProducts);
                    allNewProduct.add(datum);
                    String addAndStoreNewProduct = gson.toJson(allNewProduct);
                    sharedPreference1.addProductToTheCart(addAndStoreNewProduct);
                    cartProductNumber = allNewProduct.size();
                }
                sharedPreference1.addProductCount(cartProductNumber);
                invalidateCart();*/
            }

        });
    }

    private List<Datum> convertObjectArrayToListObject(Datum[] allProducts) {
        List<Datum> mProduct = new ArrayList<Datum>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_shop);
        int mCount = sharedPreference1.retrieveProductCount();
        menuItem.setIcon(buildCounterDrawable(mCount, R.drawable.cart));
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_shop) {
            Intent checkoutIntent = new Intent(ProductActivity.this, CheckoutActivity.class);
            startActivity(checkoutIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Drawable buildCounterDrawable(int count, int backgroundImageId) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.shopping_layout, null);
        view.setBackgroundResource(backgroundImageId);

        if (count == 0) {
            View counterTextPanel = view.findViewById(R.id.counterValuePanel);
            counterTextPanel.setVisibility(View.GONE);
        } else {
            TextView textView = view.findViewById(R.id.count);
            textView.setText("" + count);
        }

        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return new BitmapDrawable(getResources(), bitmap);
    }

    private void invalidateCart() {
        invalidateOptionsMenu();
    }

}
