package com.example.quang.bookstoreonline;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.example.quang.bookstoreonline.Adapters.FeatureBookAdapter;
import com.example.quang.bookstoreonline.Adapters.NewBookAdapter;
import com.example.quang.bookstoreonline.Adapters.RecyclerViewAdapter;
import com.example.quang.bookstoreonline.Adapters.SaleAdapter;
import com.example.quang.bookstoreonline.Models.CardViewModel;
import com.example.quang.bookstoreonline.Models.FeatureBookModel;
import com.example.quang.bookstoreonline.Models.NewBookModel;
import com.example.quang.bookstoreonline.Models.SaleModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private Vector<CardViewModel> data = new Vector<CardViewModel>();
    RecyclerView recyclerView;
    private int position = 0;

    private ArrayList<NewBookModel> newBookModels = new ArrayList<NewBookModel>();
    private NewBookAdapter adapterA;
    private ListView listView;

    JSONObject jsonObject;

    public static String loadImage;
    public final String ID = "id";
    public final String SEND_KEY = "send";
    public final String TITLE = "title";
    public final String CONTENT = "content";
    public final String PRICE = "price";
    public final String IMAGE = "image";


    String urlGetListBook = "http://kaffeines.xyz/public/api/getlistbook";

    Bundle bundle;

    private void scrollbyTime() {
        final android.os.Handler handler = new android.os.Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                position++;
                if (position >= data.size()) {
                    position = 0;
                }
                recyclerView.smoothScrollToPosition(position);
                handler.postDelayed(this, 3000);
            }
        });
    }


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;
        view = inflater.inflate(R.layout.fragment_home_layout, container, false);

        GetData(urlGetListBook);
        setHasOptionsMenu(true);
        //aaa
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Home");


        //RecyclerView

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(getActivity(), 800);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);


        //ListView
        listView = (ListView) view.findViewById(R.id.listViewNewBook);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });


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
                    ArrayList<NewBookModel> listFound = new ArrayList<NewBookModel>();
                    for (NewBookModel item : newBookModels) {
                        if (item.getNameBookC().toLowerCase(Locale.getDefault()).contains(newText)) {
                            listFound.add(item);
                        }
                    }
                    adapterA = new NewBookAdapter((AppCompatActivity) getActivity(), R.layout.listview_newbook_custom, listFound);
                    listView.setAdapter(adapterA);

                } else {
                    adapterA = new NewBookAdapter((AppCompatActivity) getActivity(), R.layout.listview_newbook_custom, newBookModels);
                    listView.setAdapter(adapterA);
                }

                adapterA.notifyDataSetChanged();
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
                        JSONArray datas = jsonObject.getJSONArray("data");
                        for (int i = 0; i < datas.length(); i++) {
                            JSONObject objBook = datas.getJSONObject(i);
                            newBookModels.add(new NewBookModel(
                                    objBook.getInt("p_id"),
                                    objBook.getString("p_name"),
                                    objBook.getString("p_price"),
                                    loadImage + objBook.getString("p_img"),
                                    objBook.getString("p_desc")
                            ));

                            data.add(new CardViewModel(
                                    loadImage + objBook.getString("p_img"),
                                    objBook.getString("p_name")
                            ));
                        }

                        RecyclerViewAdapter adapter = new RecyclerViewAdapter(data);
                        recyclerView.setAdapter(adapter);
                        scrollbyTime();

                        adapterA = new NewBookAdapter((AppCompatActivity) getActivity(), R.layout.listview_newbook_custom, newBookModels);
                        listView.setAdapter(adapterA);


                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                bundle = new Bundle();
                                Intent intent = new Intent(getContext(), ProductDetailActivity.class);

                                Integer txtID = 0;
                                txtID = newBookModels.get(i).getIdBookC();

                                String txtTitle = "";
                                txtTitle = newBookModels.get(i).getNameBookC();

                                String txtDesc = "";
                                txtDesc = newBookModels.get(i).getDecsA();

                                String txtPrice = "";
                                txtPrice = newBookModels.get(i).getPriceC();

                                String image = "";
                                image = newBookModels.get(i).getImgBookC();

                                bundle.putInt(ID, txtID);
                                bundle.putString(TITLE, txtTitle);
                                bundle.putString(CONTENT, txtDesc);
                                bundle.putString(PRICE, txtPrice);
                                bundle.putString(IMAGE, image);
                                intent.putExtra(SEND_KEY, bundle);
                                startActivity(intent);
                            }
                        });
                        adapterA.notifyDataSetChanged();

                }

            } catch(
            JSONException e)

            {
                e.printStackTrace();
            }
        }
    },new Response.ErrorListener()

    {
        @Override
        public void onErrorResponse (VolleyError error){

    }
    });
        requestQueue.add(jsonArrayRequest);
}
}


//    final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//        bundle = new Bundle();
//
//                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null,
//                new Response.Listener<JSONArray>() {
//@Override
//public void onResponse(JSONArray response) {
//
//        loadImage = "http://anhtan3396.pe.hu/Web/public/upload/image/book-items/";
//        for(int i = 0; i < response.length(); i++) {
//        try {
//        jsonObject = response.getJSONObject(i);
//        newBookModels.add(new NewBookModel(
//        jsonObject.getString("p_name"),
//        jsonObject.getString("p_price"),
//        loadImage + jsonObject.getString("p_img"),
//        jsonObject.getString("p_desc")
//        ));
//
//        data.add(new CardViewModel(
//        loadImage + jsonObject.getString("p_img"),
//        jsonObject.getString("p_name")
//        ));
//
////                                images = new ArrayList<String>();
////                                loadImage = jsonObject.getString("p_img") + "";
////
////                                images.add(loadImage);
//
//
//        } catch (JSONException e) {
//        e.printStackTrace();
//        }
//        }
//
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(data);
//        recyclerView.setAdapter(adapter);
//        scrollbyTime();
//
//        adapterA = new NewBookAdapter((AppCompatActivity) getActivity(), R.layout.listview_newbook_custom, newBookModels);
//
//        listView.setAdapter(adapterA);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//@Override
//public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Intent intent = new Intent(getContext(), ProductDetailActivity.class);
//
//
//        String txtTitle = "";
//        txtTitle = newBookModels.get(i).getNameBookC();
//
//        String txtDesc = "";
//        txtDesc = newBookModels.get(i).getDecsA();
//
//        String txtPrice = "";
//        txtPrice = newBookModels.get(i).getPriceC();
//
//        String image = "";
//        image = newBookModels.get(i).getImgBookC();
//
//        bundle.putString(TITLE, txtTitle);
//        bundle.putString(CONTENT, txtDesc);
//        bundle.putString(PRICE, txtPrice);
//        bundle.putString(IMAGE, image);
//        intent.putExtra(SEND_KEY, bundle);
//        startActivity(intent);
//        }
//        });
//        adapterA.notifyDataSetChanged();
//        }
//
//
//
//        }, new Response.ErrorListener() {
//@Override
//public void onErrorResponse(VolleyError error) {
//        Toast.makeText(getActivity(), "Lỗi " + error, Toast.LENGTH_LONG).show();
//
//        Log.d("error: " , error + "");
//
//
//        }
//        });
//        requestQueue.add(jsonArrayRequest);