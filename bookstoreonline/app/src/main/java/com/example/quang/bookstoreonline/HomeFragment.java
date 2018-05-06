package com.example.quang.bookstoreonline;


import android.content.Intent;
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
import android.widget.ListView;
import android.widget.Toast;

import com.example.quang.bookstoreonline.Adapters.NewBookAdapter;
import com.example.quang.bookstoreonline.Adapters.RecyclerViewAdapter;
import com.example.quang.bookstoreonline.Models.CardViewModel;
import com.example.quang.bookstoreonline.Models.NewBookModel;

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

        setHasOptionsMenu(true);
        //aaa
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Home");


        //RecyclerView
        data.add(new CardViewModel(R.drawable.dacnhantam, "Đắc nhân tâm"));
        data.add(new CardViewModel(R.drawable.quatang2, "Quà tặng diệu kỳ"));
        data.add(new CardViewModel(R.drawable.bimat, "Bí mật của may mắn"));

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(getActivity(), 800);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(data);
        recyclerView.setAdapter(adapter);
        scrollbyTime();

        //ListView
        listView = (ListView) view.findViewById(R.id.listViewNewBook);
        newBookModels.clear();
        newBookModels.add(new NewBookModel("Đắc nhân tâm", "100.000VND", "First new", R.drawable.dacnhantam));
        newBookModels.add(new NewBookModel("Bí mật của may mắn", "50.000VND", "First new", R.drawable.bimat));
        newBookModels.add(new NewBookModel("Quà tặng diệu kỳ", "100.000VND", "First new", R.drawable.quatang2));
        adapterA = new NewBookAdapter((AppCompatActivity) getActivity(), R.layout.listview_newbook_custom, newBookModels);
        listView.setAdapter(adapterA);


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
