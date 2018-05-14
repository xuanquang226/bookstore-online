package com.example.quang.bookstoreonline;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private HomeFragment homeFragment;

    private SaleFragment saleFragment;
    private UserFragment userFragment;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;

    public static boolean isLogin = false;
	ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // change color actionBar
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#70b243")));


        //Fragment
        homeFragment = new HomeFragment();
        saleFragment = new SaleFragment();
        userFragment = new UserFragment();
        setFragment(homeFragment);


        //NavigationDrawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.NewBook:
                        mDrawerLayout.closeDrawer(Gravity.LEFT,true);
                        startActivity(new Intent(MainActivity.this, NewBookActivity.class));
                        return true;
                    case R.id.FBook:
                        mDrawerLayout.closeDrawer(Gravity.LEFT,true);
                        startActivity(new Intent(MainActivity.this, FeatureBookActivity.class));
                        return true;
                }
                return false;
            }
        });



        //BottomNavigation
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.botNav);
        bottomNavigationView.setBackgroundColor(getResources().getColor(R.color.colorGreenB));

        frameLayout = (FrameLayout) findViewById(R.id.flScreen);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.navigation_sale:
                        setFragment(saleFragment);
                        return true;
                    case R.id.navigation_user:
                        setFragment(userFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });



    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flScreen, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed(){
        if(mDrawerLayout.isDrawerOpen(navigationView)){
            mDrawerLayout.closeDrawer(navigationView);
        }else {
            finish();
        }
    }

}
