package com.example.quang.bookstoreonline;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.quang.bookstoreonline.Adapters.RecyclerViewAdapter;
import com.example.quang.bookstoreonline.Models.CardViewModel;

import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;

    private Vector<CardViewModel> data = new Vector<CardViewModel>();
    RecyclerView recyclerView;
    private int position = 0;

    private void scrollbyTime(){
        final android.os.Handler handler = new android.os.Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                position++;
                if(position>=data.size()){
                    position = 0;
                }
                recyclerView.smoothScrollToPosition(position);
                handler.postDelayed(this,3000);
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

        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawLayout);
        mToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        data.add(new CardViewModel(R.drawable.dacnhantam,"Đắc nhân tâm"));
        data.add(new CardViewModel(R.drawable.quatang2,"Quà tặng diệu kỳ"));
        data.add(new CardViewModel(R.drawable.bimat,"Bí mật của may mắn"));

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(getActivity(),800);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(data);
        recyclerView.setAdapter(adapter);
        scrollbyTime();

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
