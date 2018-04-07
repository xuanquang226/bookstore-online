package com.example.quang.bookstoreonline;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.quang.bookstoreonline.Adapters.NewBookAdapter;
import com.example.quang.bookstoreonline.Models.NewBookModel;

import java.util.ArrayList;

public class NewBookActivity extends AppCompatActivity {
    ArrayList<NewBookModel> newBookModels = new ArrayList<NewBookModel>();
    NewBookAdapter newBookAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_book_layout);

        ListView listView = (ListView) findViewById(R.id.listViewNewBookA);
        newBookModels.add(new NewBookModel("Đắc nhân tâm", "100.000VND", "First new", R.drawable.dacnhantam, R.drawable.giohang));
        newBookModels.add(new NewBookModel("Bí mật của may mắn", "50.000VND", "First new", R.drawable.bimat, R.drawable.giohang));
        newBookModels.add(new NewBookModel("Quà tặng diệu kỳ", "100.000VND", "First new", R.drawable.quatang2, R.drawable.giohang));
        newBookAdapter = new NewBookAdapter(this, R.layout.listview_newbook_custom, newBookModels);
        listView.setAdapter(newBookAdapter);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("NewBook");
    }
}
