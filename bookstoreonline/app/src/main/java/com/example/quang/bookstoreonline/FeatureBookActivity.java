package com.example.quang.bookstoreonline;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.quang.bookstoreonline.Adapters.FeatureBookAdapter;
import com.example.quang.bookstoreonline.Adapters.NewBookAdapter;
import com.example.quang.bookstoreonline.Models.FeatureBookModel;
import com.example.quang.bookstoreonline.Models.NewBookModel;

import java.util.ArrayList;

public class FeatureBookActivity extends AppCompatActivity {
    ArrayList<FeatureBookModel> featureBookModels = new ArrayList<FeatureBookModel>();
    FeatureBookAdapter featureBookAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_book_layout);

        ListView listView = (ListView) findViewById(R.id.listViewFeatureBook);
        featureBookModels.add(new FeatureBookModel("Đắc nhân tâm", "100.000VND", "First new", R.drawable.dacnhantam, R.drawable.giohang));
        featureBookModels.add(new FeatureBookModel("Bí mật của may mắn", "50.000VND", "First new", R.drawable.bimat, R.drawable.giohang));
        featureBookModels.add(new FeatureBookModel("Quà tặng diệu kỳ", "100.000VND", "First new", R.drawable.quatang2, R.drawable.giohang));
        featureBookAdapter = new FeatureBookAdapter(this, R.layout.listview_feature_book_custom, featureBookModels);
        listView.setAdapter(featureBookAdapter);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Feature Book");
    }
}
