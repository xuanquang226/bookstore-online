package com.example.quang.bookstoreonline;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quang.bookstoreonline.Adapters.CartAdapter;
import com.example.quang.bookstoreonline.Models.CartModel;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    ArrayList<CartModel> cartList = new ArrayList<CartModel>();
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_layout);

        ListView listView = (ListView) findViewById(R.id.listViewCart);

        cartList.add(new CartModel("Đắc nhân tâm",200, "First new", 1, R.drawable.dacnhantam));
        cartList.add(new CartModel("Bí mật của may mắn", 200, "First new", 1, R.drawable.bimat));
        cartList.add(new CartModel("Quà tặng diệu kỳ", 300, "First new", 1, R.drawable.quatang2));
        adapter = new CartAdapter(CartActivity.this, R.layout.listview_cart_custom, cartList);
        listView.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cart");
        actionBar.setDisplayHomeAsUpEnabled(true);
        Button btnConfirm = (Button) findViewById(R.id.btnConfirmA);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, ConfirmationActivity.class));
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
}
