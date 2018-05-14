package com.example.quang.bookstoreonline;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.quang.bookstoreonline.Adapters.ViewOrdersAdapter;
import com.example.quang.bookstoreonline.Models.ViewOrdersModel;

import java.util.ArrayList;

public class ViewOrdersActivity extends AppCompatActivity {
    ArrayList<ViewOrdersModel> viewOrders = new ArrayList<ViewOrdersModel>();
    ViewOrdersAdapter adapter;
    ListView listView;

    public final String SEND_KEY = "send";
    public final String TITLE = "title";
    public final String PRICE = "price";
    public final String IMAGE = "image";
    public final String CLICKED = "clicked";

    String title = "";
    String price = "";
    String img = "";
    int clicked = 0;

    Bundle bundle;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_orders_layout);
        bundle = new Bundle();

        getData();



        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("View Orders");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70b243")));
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.listViewVOrders);
        viewOrders.add(new ViewOrdersModel(title, price, img));

        adapter = new ViewOrdersAdapter(this, R.layout.listview_view_orders_custom, viewOrders);
        listView.setAdapter(adapter);
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
        title = bundle.getString(TITLE);
        price = bundle.getString(PRICE);
        img = bundle.getString(IMAGE);

        clicked = bundle.getInt(CLICKED);

    }
}
