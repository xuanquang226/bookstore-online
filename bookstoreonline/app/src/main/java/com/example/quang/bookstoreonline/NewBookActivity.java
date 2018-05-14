package com.example.quang.bookstoreonline;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quang.bookstoreonline.Adapters.FeatureBookAdapter;
import com.example.quang.bookstoreonline.Adapters.NewBookAdapter;
import com.example.quang.bookstoreonline.Adapters.ViewOrdersAdapter;
import com.example.quang.bookstoreonline.Models.CardViewModel;
import com.example.quang.bookstoreonline.Models.FeatureBookModel;
import com.example.quang.bookstoreonline.Models.NewBookModel;
import com.example.quang.bookstoreonline.Models.ViewOrdersModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class NewBookActivity extends AppCompatActivity {
    ArrayList<NewBookModel> newBook = new ArrayList<NewBookModel>();
    NewBookAdapter adapter;
    ListView listView;

    JSONObject jsonObject;

    public static String loadImage;

    public final String SEND_KEY = "send";
    public final String ID = "id";
    public final String TITLE = "title";
    public final String CONTENT = "content";
    public final String PRICE = "price";
    public final String IMAGE = "image";

    Bundle bundle;

    String urlGetListBook = "http://kaffeines.xyz/public/api/getlistbook";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_book_layout);

        bundle = new Bundle();
        Spinner spinner = (Spinner) findViewById(R.id.spinnerA);
        ArrayList<String> filter  = new ArrayList<String>();
        filter.add("Price");
        filter.add("Author");
        ArrayAdapter<String> adapterB = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,filter);
        spinner.setAdapter(adapterB);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("New Books");
        actionBar.setDisplayHomeAsUpEnabled(true);

        GetData(urlGetListBook);



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

    public void GetData(String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(NewBookActivity.this);
        loadImage = "http://kaffeines.xyz/public/upload/image/book-items/";

        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    jsonObject = new JSONObject(response);

                    if (jsonObject.getInt("resultCode") == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject objBook = data.getJSONObject(i);
                            Log.d("Ten: ", loadImage + objBook.getString("p_img"));
                            newBook.add(new NewBookModel(
                                    objBook.getInt("p_id"),
                                    objBook.getString("p_name"),
                                    objBook.getString("p_price"),
                                    loadImage + objBook.getString("p_img"),
                                    objBook.getString("p_desc")
                            ));
                        }

                        listView = (ListView) findViewById(R.id.listViewNewBookA);

                        adapter = new NewBookAdapter(NewBookActivity.this, R.layout.listview_newbook_custom, newBook);
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                bundle = new Bundle();
                                Intent intent = new Intent(NewBookActivity.this, ProductDetailActivity.class);

                                Integer txtID = 0;
                                txtID = newBook.get(i).getIdBookC();

                                String txtTitle = "";
                                txtTitle = newBook.get(i).getNameBookC();

                                String txtDesc = "";
                                txtDesc = newBook.get(i).getDecsA();

                                String txtPrice = "";
                                txtPrice = newBook.get(i).getPriceC();
                                Log.d("Price: ", txtPrice);

                                String image = "";
                                image = newBook.get(i).getImgBookC();

                                bundle.getInt(ID, txtID);
                                bundle.putString(TITLE, txtTitle);
                                bundle.putString(CONTENT, txtDesc);
                                bundle.putString(PRICE, txtPrice);
                                bundle.putString(IMAGE, image);
                                intent.putExtra(SEND_KEY, bundle);
                                startActivity(intent);
                            }
                        });


                        adapter.notifyDataSetChanged();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
