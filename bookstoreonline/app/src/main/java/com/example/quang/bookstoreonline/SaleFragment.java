package com.example.quang.bookstoreonline;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.quang.bookstoreonline.Adapters.SaleAdapter;
import com.example.quang.bookstoreonline.Models.SaleModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SaleFragment extends Fragment {
    SaleAdapter adapter;
    ArrayList<SaleModel> saleModels = new ArrayList<SaleModel>();
    ListView listView;

    public SaleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_sale_layout, container, false);

        setHasOptionsMenu(true);
        ActionBar actionBar = ((AppCompatActivity) getActivity()). getSupportActionBar();
        actionBar.setTitle("Promotion");

        listView = (ListView) view.findViewById(R.id.listViewSale);
        saleModels.clear();
        saleModels.add(new SaleModel("Đắc nhân tâm", "80.000", "First new", R.drawable.dacnhantam, "100.000", "20%"));
        saleModels.add(new SaleModel("Quà tặng diệu kỳ", "120.000", "First new", R.drawable.quatang2, "150.000", "20%"));
        saleModels.add(new SaleModel("Bí mật của may mắn", "64.000", "First new", R.drawable.bimat, "80.000", "20%"));

        adapter = new SaleAdapter((AppCompatActivity) getActivity(), R.layout.listview_sale_custom, saleModels);
        listView.setAdapter(adapter);
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


                return true;

            }

        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}
