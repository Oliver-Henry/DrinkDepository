package com.example.olive.drinkdepository;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.olive.drinkdepository.drink_details.DrinkDetailsFragment;
import com.example.olive.drinkdepository.drink_list.DrinkListFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static FragmentManager fragmentManager;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchView= (SearchView) findViewById(R.id.SearchView);
        searchView.setVisibility(View.INVISIBLE);

        fragmentManager = getSupportFragmentManager();
        if(savedInstanceState == null){
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, new HomeFragment())
                    .commit();
        }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        String page;
        DrinkListFragment drinkListFragment = new DrinkListFragment();

        if (id == R.id.nav_home) {
            searchView.setVisibility(View.INVISIBLE);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        } else if (id == R.id.nav_search) {
            searchView.setVisibility(View.VISIBLE);
        } else if (id == R.id.nav_ordinary_drinks) {
            searchView.setVisibility(View.INVISIBLE);
            page = "O";
            Bundle bundle = new Bundle();
            bundle.putString("page", page);
            drinkListFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, drinkListFragment)
                    .commit();
        } else if (id == R.id.nav_cocktails) {
            searchView.setVisibility(View.INVISIBLE);
            page = "C";
            Bundle bundle = new Bundle();
            bundle.putString("page", page);
            drinkListFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, drinkListFragment)
                    .commit();

        } else if (id == R.id.nav_homemade_liqueurs) {
            searchView.setVisibility(View.INVISIBLE);
            page = "H";
            Bundle bundle = new Bundle();
            bundle.putString("page", page);
            drinkListFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, drinkListFragment)
                    .commit();

        } else if (id == R.id.nav_punch_party_drinks) {
            searchView.setVisibility(View.INVISIBLE);
            page = "P";
            Bundle bundle = new Bundle();
            bundle.putString("page", page);
            drinkListFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, drinkListFragment)
                    .commit();

        } else if (id == R.id.nav_search_drk_ingr) {

        } else if (id == R.id.nav_rndm_drink) {

        } else if (id == R.id.nav_map) {
//            fragmentManager.beginTransaction()
//                    .replace(R.id.fragment_container, 'MAP FRAGMENT')
//                    .addToBackStack(null)
//                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void showDrinkDetails(int id, int position){
        DrinkDetailsFragment drinkDetailsFragment = new DrinkDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putInt("position", position);
        drinkDetailsFragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, drinkDetailsFragment)
                .addToBackStack(null)
                .commit();
    }
}
