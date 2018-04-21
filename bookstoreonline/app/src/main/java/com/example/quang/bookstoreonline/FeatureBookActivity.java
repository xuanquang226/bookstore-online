package com.example.quang.bookstoreonline;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.quang.bookstoreonline.Adapters.FeatureBookAdapter;
import com.example.quang.bookstoreonline.Adapters.NewBookAdapter;
import com.example.quang.bookstoreonline.Models.FeatureBookModel;
import com.example.quang.bookstoreonline.Models.NewBookModel;

import java.util.ArrayList;

public class FeatureBookActivity extends AppCompatActivity {
    ArrayList<FeatureBookModel> featureBook = new ArrayList<FeatureBookModel>();
    FeatureBookAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_book_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Feature Books");
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.listViewFBook);
        featureBook.add(new FeatureBookModel("Đắc nhân tâm", "150.000", "First New", R.drawable.dacnhantam));
        featureBook.add(new FeatureBookModel("Quà tặng diệu kỳ", "150.000", "First New", R.drawable.quatang2));
        featureBook.add(new FeatureBookModel("Bí mật của may mắn", "80.000", "First New", R.drawable.bimat));

        adapter = new FeatureBookAdapter(this, R.layout.listview_featurebook_custom, featureBook);
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
