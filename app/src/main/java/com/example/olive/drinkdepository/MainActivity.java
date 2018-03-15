package com.example.olive.drinkdepository;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.olive.drinkdepository.drink_details.DrinkDetailsFragment;
import com.example.olive.drinkdepository.drink_list.DrinkListFragment;
import com.example.olive.drinkdepository.home.HomeFragment;
import com.example.olive.drinkdepository.ingredients_list.IngredientListFragment;
import com.example.olive.drinkdepository.search_drink.SearchDrinkFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static FragmentManager fragmentManager;
    private MenuItem  categoriesButton, ordinaryDrinksButton, cocktailsButton, homemadeLiqueursButton, punchPartyDrinksButton, shotButton, beerButton,
                      softDrinkSodaButton, milkFloatShakeButton, cocoaButton, coffeeTeaButton, otherUnknownButton;
    private boolean menuOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuOpen = false;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();
        if(savedInstanceState == null){
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, new HomeFragment())
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu =navigationView.getMenu();

        categoriesButton = menu.findItem(R.id.nav_categories);
        ordinaryDrinksButton = menu.findItem(R.id.nav_ordinary_drinks);
        cocktailsButton = menu.findItem(R.id.nav_cocktails);
        homemadeLiqueursButton = menu.findItem(R.id.nav_homemade_liqueurs);
        punchPartyDrinksButton = menu.findItem(R.id.nav_punch_party_drinks);
        shotButton = menu.findItem(R.id.nav_shot);
        beerButton = menu.findItem(R.id.nav_beer);
        softDrinkSodaButton = menu.findItem(R.id.nav_soft_drink_soda);
        milkFloatShakeButton = menu.findItem(R.id.nav_milk_float_shake);
        cocoaButton = menu.findItem(R.id.nav_cocoa);
        coffeeTeaButton = menu.findItem(R.id.nav_coffee_tea);
        otherUnknownButton = menu.findItem(R.id.nav_other_unknown);
        ordinaryDrinksButton.setVisible(false);
        cocktailsButton.setVisible(false);
        homemadeLiqueursButton.setVisible(false);
        punchPartyDrinksButton.setVisible(false);
        shotButton.setVisible(false);
        beerButton.setVisible(false);
        softDrinkSodaButton.setVisible(false);
        milkFloatShakeButton.setVisible(false);
        cocoaButton.setVisible(false);
        coffeeTeaButton.setVisible(false);
        otherUnknownButton.setVisible(false);


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


    /**
     * Reads what menu item inside the navigation menu was and then takes action depending on what
     * item was clicked e.g. if the 'Random Drink' menu item was clicked then that fragment will
     * replace the currently open fragment.
     *
     * @param item The item within the navigation menu that is clicked
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        String page;
        DrinkListFragment drinkListFragment = new DrinkListFragment();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (id == R.id.nav_home) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_search) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new SearchDrinkFragment())
                    .addToBackStack(null)
                    .commit();
            drawer.closeDrawer(GravityCompat.START);
            //recyclerView.clear

        } else if (id == R.id.nav_ordinary_drinks) {
            page = "Ordinary_drink";
            Bundle bundle = new Bundle();
            bundle.putString("page", page);
            drinkListFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, drinkListFragment)
                    .addToBackStack(null)
                    .commit();
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_cocktails) {
            page = "Cocktail";
            Bundle bundle = new Bundle();
            bundle.putString("page", page);
            drinkListFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, drinkListFragment)
                    .addToBackStack(null)
                    .commit();
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_homemade_liqueurs) {
            page = "Homemade_Liqueur";
            Bundle bundle = new Bundle();
            bundle.putString("page", page);
            drinkListFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, drinkListFragment)
                    .addToBackStack(null)
                    .commit();
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_punch_party_drinks) {
            page = "Punch_/_Party_Drink";
            Bundle bundle = new Bundle();
            bundle.putString("page", page);
            drinkListFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, drinkListFragment)
                    .addToBackStack(null)
                    .commit();
            drawer.closeDrawer(GravityCompat.START);

        }  else if (id == R.id.nav_shot) {
            page = "Shot";
            Bundle bundle = new Bundle();
            bundle.putString("page", page);
            drinkListFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, drinkListFragment)
                    .addToBackStack(null)
                    .commit();
            drawer.closeDrawer(GravityCompat.START);

        }  else if (id == R.id.nav_beer) {
            page = "Beer";
            Bundle bundle = new Bundle();
            bundle.putString("page", page);
            drinkListFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, drinkListFragment)
                    .addToBackStack(null)
                    .commit();
            drawer.closeDrawer(GravityCompat.START);

        }  else if (id == R.id.nav_soft_drink_soda) {
            page = "Soft_Drink_/_Soda";
            Bundle bundle = new Bundle();
            bundle.putString("page", page);
            drinkListFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, drinkListFragment)
                    .addToBackStack(null)
                    .commit();
            drawer.closeDrawer(GravityCompat.START);

        }  else if (id == R.id.nav_milk_float_shake) {
            page = "Milk_/_Float_/_Shake";
            Bundle bundle = new Bundle();
            bundle.putString("page", page);
            drinkListFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, drinkListFragment)
                    .addToBackStack(null)
                    .commit();
            drawer.closeDrawer(GravityCompat.START);

        }  else if (id == R.id.nav_cocoa) {
            page = "Cocoa";
            Bundle bundle = new Bundle();
            bundle.putString("page", page);
            drinkListFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, drinkListFragment)
                    .addToBackStack(null)
                    .commit();
            drawer.closeDrawer(GravityCompat.START);

        }  else if (id == R.id.nav_coffee_tea) {
            page = "Coffee_/_Tea";
            Bundle bundle = new Bundle();
            bundle.putString("page", page);
            drinkListFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, drinkListFragment)
                    .addToBackStack(null)
                    .commit();
            drawer.closeDrawer(GravityCompat.START);

        }  else if (id == R.id.nav_other_unknown) {
            page = "Other/Unknown";
            Bundle bundle = new Bundle();
            bundle.putString("page", page);
            drinkListFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, drinkListFragment)
                    .addToBackStack(null)
                    .commit();
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_search_drk_ingr) {
            page = "II";
            IngredientListFragment ingredientListFragment = new IngredientListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("page", page);
            ingredientListFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ingredientListFragment)
                    .addToBackStack(null)
                    .commit();
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_rndm_drink) {
            page = "R";
            DrinkDetailsFragment drinkDetailsFragment = new DrinkDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("page", page);
            drinkDetailsFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, drinkDetailsFragment)
                    .addToBackStack(null)
                    .commit();
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_map) {
//            MapFragment mapFragment = new MapFragment();
//            fragmentManager.beginTransaction()
//                    .replace(R.id.fragment_container, mapFragment)
//                    .addToBackStack(null)
//                    .commit();
//            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_categories){
            if(!menuOpen){
                ordinaryDrinksButton.setVisible(true);
                cocktailsButton.setVisible(true);
                homemadeLiqueursButton.setVisible(true);
                punchPartyDrinksButton.setVisible(true);
                shotButton.setVisible(true);
                beerButton.setVisible(true);
                softDrinkSodaButton.setVisible(true);
                milkFloatShakeButton.setVisible(true);
                cocoaButton.setVisible(true);
                coffeeTeaButton.setVisible(true);
                otherUnknownButton.setVisible(true);
//                menuButtons.get(menuButtons.size()).setVisible(true);
                categoriesButton.setIcon(android.R.drawable.arrow_up_float);
                menuOpen = true;
            }
            else if (menuOpen){
                ordinaryDrinksButton.setVisible(false);
                cocktailsButton.setVisible(false);
                homemadeLiqueursButton.setVisible(false);
                punchPartyDrinksButton.setVisible(false);
                shotButton.setVisible(false);
                beerButton.setVisible(false);
                softDrinkSodaButton.setVisible(false);
                milkFloatShakeButton.setVisible(false);
                cocoaButton.setVisible(false);
                coffeeTeaButton.setVisible(false);
                otherUnknownButton.setVisible(false);
//                menuButtons.get(menuButtons.size()).setVisible(false);
                categoriesButton.setIcon(android.R.drawable.arrow_down_float);
                menuOpen = false;
            }
        }

        return true;
    }

    /**
     * Gains the id and position of a drink from a recyclerView in the open fragment, which is then
     * passed on to a new fragment so that the selected drink can be viewed in the new fragment
     * with additional details
     *
     * @param id The id of the selected drink used to pull the correct info from the API
     * @param position The position in the recycler view confirm which item was selected
     */
    public static void showDrinkDetails(int id, int position){
        String page = "D";
        DrinkDetailsFragment drinkDetailsFragment = new DrinkDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("page", page);
        bundle.putInt("id", id);
        bundle.putInt("position", position);
        drinkDetailsFragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, drinkDetailsFragment)
                .addToBackStack(null)
                .commit();
    }


    public static void displayDrinksByIngredient(String name, int position){
        String page = "I";
        IngredientListFragment ingredientListFragment = new IngredientListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("page", page);
        bundle.putString("name", name);
        bundle.putInt("position", position);
        ingredientListFragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ingredientListFragment)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Retrieves the name of a category from a recyclerView clickListener, which is then, using
     * bundle passed to a new fragment to be appended on the end of an API call to retrieve data
     * from the drink category clicked.
     *
     * @param name The name of the category/page that will be opened
     * @param position
     */
    public static void displayDrinksFromCategory(String name, int position){
        DrinkListFragment drinkListFragment = new DrinkListFragment();
        String page = name;
        Bundle bundle = new Bundle();
        bundle.putString("page", page);
        drinkListFragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, drinkListFragment)
                .addToBackStack(null)
                .commit();
    }

}
