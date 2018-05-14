package com.example.quang.bookstoreonline;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quang.bookstoreonline.Adapters.CartAdapter;
import com.example.quang.bookstoreonline.Models.CardViewModel;
import com.example.quang.bookstoreonline.Models.CartModel;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    ArrayList<CartModel> cartList = new ArrayList<CartModel>();
    CartAdapter adapter;

    public final String SEND_KEY = "send";
    public final String IDBOOK = "idbook";
    public final String QUANTITY = "quantity";

    public final String ID = "id";

    public final String TITLE = "title";
    public final String PRICE = "price";
    public final String IMAGE = "image";
    public final String CLICKED = "clicked";

    int     id = 0;
    String title = "";
    String price = "";
    String img = "";
    int clicked = 0;

    public static boolean isLogin = false;

    Bundle bundle;

    Intent intent;

    Intent intentCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_layout);

        final Intent[] intent = {getIntent()};

        getData();

        ListView listView = (ListView) findViewById(R.id.listViewCart);

        cartList.add(new CartModel(title, Float.parseFloat(price), 1 ,img));
        adapter = new CartAdapter(CartActivity.this, R.layout.listview_cart_custom, cartList);
        listView.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Shopping Cart");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70b243")));
        actionBar.setDisplayHomeAsUpEnabled(true);
        Button btnConfirm = (Button) findViewById(R.id.btnConfirmA);


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int quantity = 1;
                intentCart = new Intent(CartActivity.this, ConfirmationActivity.class);
                Intent intentUser = getIntent();

                bundle.putInt(IDBOOK, id);
                bundle.putInt(QUANTITY, quantity);



                intentCart.putExtra(SEND_KEY, bundle);
                startActivity(intentCart);

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getData() {
        intent = getIntent();
        bundle = intent.getBundleExtra(SEND_KEY);
        id     = bundle.getInt(ID);
        title = bundle.getString(TITLE);
        price = bundle.getString(PRICE);
        img = bundle.getString(IMAGE);

        clicked = bundle.getInt(CLICKED);

    }
}
