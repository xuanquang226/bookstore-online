package com.example.quang.bookstoreonline;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_orders_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("ViewOrder");
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.listViewVOrders);
        viewOrders.add(new ViewOrdersModel("Đắc nhân tâm", "150.000", "First New", R.drawable.dacnhantam));
        viewOrders.add(new ViewOrdersModel("Quà tặng diệu kỳ", "150.000", "First New", R.drawable.quatang2));
        viewOrders.add(new ViewOrdersModel("Bí mật của may mắn", "80.000", "First New", R.drawable.bimat));

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
}
