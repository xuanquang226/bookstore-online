package com.example.quang.bookstoreonline;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quang.bookstoreonline.Adapters.NewBookAdapter;
import com.example.quang.bookstoreonline.Adapters.SaleAdapter;
import com.example.quang.bookstoreonline.Models.NewBookModel;
import com.example.quang.bookstoreonline.Models.SaleModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class SaleFragment extends Fragment {
    SaleAdapter adapter;
    ArrayList<SaleModel> saleModels;
    ListView listView;
    ImageView imageView;
    File imageFile;
    Bitmap myBitmap;

    JSONObject jsonObject;
    String urlGetListBook = "http://kaffeines.xyz/public/api/getlistbook";

    public static ArrayList<String> images;
    public static String loadImage;

    Bundle bundle;

    public final String SEND_KEY = "send";
    public final String ID = "id";
    public final String TITLE = "title";
    public final String CONTENT = "content";
    public final String PRICE = "price";
    public final String IMAGE = "image";


    public SaleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        bundle = new Bundle();
        View view;
        view = inflater.inflate(R.layout.fragment_sale_layout, container, false);
        saleModels = new ArrayList<SaleModel>();
        saleModels.clear();

        setHasOptionsMenu(true);
        ActionBar actionBar = ((AppCompatActivity) getActivity()). getSupportActionBar();
        actionBar.setTitle("Promotion");

        listView = (ListView) view.findViewById(R.id.listViewSale);
//        saleModels.add(new SaleMsodel(1,"Đắc nhân tâm", "80.000", R.drawable.dacnhantam, 100.000, "20%"));
//        saleModels.add(new SaleModel(2,"Quà tặng diệu kỳ", "120.000", R.drawable.quatang2, 150.000, "20%"));
//        saleModels.add(new SaleModel(3, "Bí mật của may mắn", "64.000", R.drawable.bimat, 80.000, "20%"));

//        new DownLoadAsyncTaskRegist(SaleFragment.this).execute("http://anhtan3396.pe.hu/Web/public/api/getlistbook");

        GetData(urlGetListBook);

        return view;
    }

   @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // dua nut search vao action bar

        inflater.inflate(R.menu.search,menu);
        // tao 1 search view
        final android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) menu.findItem(R.id.menuSearchHome).getActionView();
        //bat su kien cho nut search

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("ABC", newText);

                newText = newText.toLowerCase(Locale.getDefault());
//
                if (newText != null && !newText.isEmpty()) {
                    ArrayList<SaleModel> listFound = new ArrayList<SaleModel>();
                    for (SaleModel item : saleModels) {
                        if (item.getNameBookA().toLowerCase(Locale.getDefault()).contains(newText)) {
                            listFound.add(item);
                        }
                    }
                    adapter = new SaleAdapter((AppCompatActivity) getActivity(), R.layout.listview_sale_custom, listFound);
                    listView.setAdapter(adapter);

                } else {
                    adapter = new SaleAdapter((AppCompatActivity) getActivity(), R.layout.listview_sale_custom, saleModels);
                    listView.setAdapter(adapter);
                }

                adapter.notifyDataSetChanged();
                if (newText.length() == 0) {
                    searchView.clearFocus();
                }

                return true;

            }

        });
        super.onCreateOptionsMenu(menu, inflater);
    }


    public void GetData(String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        loadImage = "http://kaffeines.xyz/public/upload/image/book-items/";

        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    jsonObject = new JSONObject(response);

                    if (jsonObject.getInt("resultCode") == 0) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        Log.d("data: ", data + "");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject objBook = data.getJSONObject(i);
                            saleModels.add(new SaleModel(
                                    objBook.getInt("p_id"),
                                    objBook.getString("p_name"),
                                    Integer.parseInt(objBook.getString("p_price")) * 0.8,
                                    objBook.getString("p_desc"),
                                    loadImage + objBook.getString("p_img"),
                                    objBook.getString("p_price"),
                                    "20%"
                            ));
                        }


                        adapter = new SaleAdapter((AppCompatActivity) getActivity(), R.layout.listview_sale_custom, saleModels);
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                bundle = new Bundle();
                                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);

                                Integer txtID = 0;
                                txtID = saleModels.get(i).getIdBookA();

                                String txtTitle = "";
                                txtTitle = saleModels.get(i).getNameBookA();

                                String txtDesc = "";
                                txtDesc = saleModels.get(i).getDecsA();

                                String txtPrice = "";
                                txtPrice = saleModels.get(i).getPriceA() + "";

                                String image = "";
                                image = saleModels.get(i).getImgBookA();

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

//        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//
//                        loadImage = "http://anhtan3396.pe.hu/Web/public/upload/image/book-items/";
//                        for(int i = 0; i < response.length(); i++) {
//                            try {
//                                jsonObject = response.getJSONObject(i);
//                                saleModels.add(new SaleModel(
//                                        jsonObject.getInt("p_id"),
//                                        jsonObject.getString("p_name"),
//                                        Integer.parseInt(jsonObject.getString("p_price")) * 0.8,
//                                        jsonObject.getString("p_desc"),
//                                        loadImage + jsonObject.getString("p_img"),
//                                        jsonObject.getString("p_price"),
//                                        "20%"
//                                        ));
//
////                                images = new ArrayList<String>();
////                                loadImage = jsonObject.getString("p_img") + "";
////
////                                images.add(loadImage);
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        adapter = new SaleAdapter((AppCompatActivity) getActivity(), R.layout.listview_sale_custom, saleModels);
//
//                        listView.setAdapter(adapter);
//
//                        adapter.notifyDataSetChanged();
//                    }
//
//
//
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getActivity(), "Lỗi " + error, Toast.LENGTH_LONG).show();
//
//                        Log.d("error: " , error + "");
//
//
//                    }
//                });
//        requestQueue.add(jsonArrayRequest);