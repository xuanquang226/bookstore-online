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
import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

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

        //Navigation drawer
        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawLayout);
        mToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        final NavigationView navigationView = (NavigationView) view.findViewById(R.id.nav_drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.NewBook:
                        setNewBookModels();
                        mDrawerLayout.closeDrawer(Gravity.LEFT,true);
                        Toast.makeText(getActivity(),"New book",Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.FBook:
                        setFeatureBook();
                        mDrawerLayout.closeDrawer(Gravity.LEFT,true);
                        Toast.makeText(getActivity(),"Feature book",Toast.LENGTH_LONG).show();
                        return true;
                }
                return true;
            }
        });

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
        newBookModels.add(new NewBookModel("Đắc nhân tâm", "100.000VND", "First new", R.drawable.dacnhantam, R.drawable.giohang));
        newBookModels.add(new NewBookModel("Bí mật của may mắn", "50.000VND", "First new", R.drawable.bimat, R.drawable.giohang));
        newBookModels.add(new NewBookModel("Quà tặng diệu kỳ", "100.000VND", "First new", R.drawable.quatang2, R.drawable.giohang));
        adapterA = new NewBookAdapter((AppCompatActivity) getActivity(), R.layout.listview_newbook_custom, newBookModels);
        listView.setAdapter(adapterA);


        return view;
    }

    public void setNewBookModels(){
        newBookModels.clear();
        newBookModels.add(new NewBookModel("A", "100.000VND", "First newww", R.drawable.dacnhantam, R.drawable.giohang));
        newBookModels.add(new NewBookModel("B", "50.000VND", "First newww", R.drawable.bimat, R.drawable.giohang));
        newBookModels.add(new NewBookModel("C", "100.000VND", "First newww", R.drawable.quatang2, R.drawable.giohang));
        adapterA = new NewBookAdapter((AppCompatActivity) getActivity(), R.layout.listview_newbook_custom, newBookModels);
        listView.setAdapter(adapterA);
        adapterA.notifyDataSetChanged();
    }

    public void setFeatureBook(){
        newBookModels.clear();
        newBookModels.add(new NewBookModel("D", "100.000VND", "First newww", R.drawable.dacnhantam, R.drawable.giohang));
        newBookModels.add(new NewBookModel("E", "50.000VND", "First newww", R.drawable.bimat, R.drawable.giohang));
        newBookModels.add(new NewBookModel("F", "100.000VND", "First newww", R.drawable.quatang2, R.drawable.giohang));
        adapterA = new NewBookAdapter((AppCompatActivity) getActivity(), R.layout.listview_newbook_custom, newBookModels);
        listView.setAdapter(adapterA);
        adapterA.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
