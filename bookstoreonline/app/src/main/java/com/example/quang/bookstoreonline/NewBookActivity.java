package com.example.quang.bookstoreonline;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.quang.bookstoreonline.Adapters.NewBookAdapter;
import com.example.quang.bookstoreonline.Adapters.ViewOrdersAdapter;
import com.example.quang.bookstoreonline.Models.NewBookModel;
import com.example.quang.bookstoreonline.Models.ViewOrdersModel;

import java.util.ArrayList;

public class NewBookActivity extends AppCompatActivity {
    ArrayList<NewBookModel> newBook = new ArrayList<NewBookModel>();
    NewBookAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_book_layout);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("New Books");
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.listViewNewBookA);
        newBook.add(new NewBookModel("Đắc nhân tâm", "150.000", "First New", R.drawable.dacnhantam));
        newBook.add(new NewBookModel("Quà tặng diệu kỳ", "150.000", "First New", R.drawable.quatang2));
        newBook.add(new NewBookModel("Bí mật của may mắn", "80.000", "First New", R.drawable.bimat));

        adapter = new NewBookAdapter(this, R.layout.listview_newbook_custom, newBook);
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
