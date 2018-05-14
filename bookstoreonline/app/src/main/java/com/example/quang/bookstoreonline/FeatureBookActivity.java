package com.example.quang.bookstoreonline;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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
import com.example.quang.bookstoreonline.Models.FeatureBookModel;
import com.example.quang.bookstoreonline.Models.NewBookModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class FeatureBookActivity extends AppCompatActivity {
    ArrayList<FeatureBookModel> featureBook = new ArrayList<FeatureBookModel>();
    FeatureBookAdapter adapter;
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
        setContentView(R.layout.feature_book_layout);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Feature Books");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerB);
        ArrayList<String> filter = new ArrayList<String>();
        filter.add("Price");
        filter.add("Author");
        ArrayAdapter<String> adapterB = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, filter);
        spinner.setAdapter(adapterB);

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
        final RequestQueue requestQueue = Volley.newRequestQueue(FeatureBookActivity.this);
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
                            featureBook.add(new FeatureBookModel(
                                    objBook.getInt("p_id"),
                                    objBook.getString("p_name"),
                                    objBook.getString("p_price"),
                                    loadImage + objBook.getString("p_img"),
                                    objBook.getString("p_desc")
                            ));
                        }

                        listView = (ListView) findViewById(R.id.listViewFBook);

                        adapter = new FeatureBookAdapter(FeatureBookActivity.this, R.layout.listview_featurebook_custom, featureBook);
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                bundle = new Bundle();
                                Intent intent = new Intent(FeatureBookActivity.this, ProductDetailActivity.class);

                                Integer txtID = 0;
                                txtID = featureBook.get(i).getIdBookD();

                                String txtTitle = "";
                                txtTitle = featureBook.get(i).getNameBookD();

                                String txtDesc = "";
                                txtDesc = featureBook.get(i).getDescD();

                                String txtPrice = "";
                                txtPrice = featureBook.get(i).getPriceD();

                                String image = "";
                                image = featureBook.get(i).getImgBookD();

                                bundle.putInt(ID, txtID);
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


//try {
//        jsonObject = new JSONObject(String.valueOf(response));
//        if(jsonObject.getInt("resultCode") == 0) {
//        JSONArray data = jsonObject.getJSONArray("data");
//        Log.d("json: " , jsonObject.getString("message") + "");
//
//        for(int i = 0; i < data.length(); i++) {
//        JSONObject objBook = data.getJSONObject(i);
//        featureBook.add(new FeatureBookModel(
//        objBook.getString("p_name"),
//        objBook.getString("p_price"),
//        loadImage + jsonObject.getString("p_img"),
//        jsonObject.getString("p_desc")
//        ));
//        }
//        }
//
//        } catch (JSONException e) {
//        e.printStackTrace();
//        }
//
//        listView = (ListView) findViewById(R.id.listViewFBook);
//
//        adapter = new FeatureBookAdapter(FeatureBookActivity.this, R.layout.listview_featurebook_custom, featureBook);
//        listView.setAdapter(adapter);
//
//
//        adapter.notifyDataSetChanged();