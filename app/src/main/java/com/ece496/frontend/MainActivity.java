package com.ece496.frontend;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ece496.database.Event;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MonthViewFragment f_calendar;
    private EventDialogFragment f_event;

    private Toolbar toolbar;

    private FloatingActionButton b_add_event;

    private DrawerLayout l_drawer;

    private NavigationView v_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        f_event = new EventDialogFragment();

        // floating button: add event
        b_add_event = findViewById(R.id.fab);
        b_add_event.setOnClickListener(view -> {
            f_event.show(getSupportFragmentManager(), "Event Dialog");
        });

        // drawer
        l_drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                l_drawer,
                toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        l_drawer.addDrawerListener(toggle);
        toggle.syncState();

        // navigation view
        v_navigation = findViewById(R.id.nav_view);
        v_navigation.setNavigationItemSelectedListener(this);





        // calendar fragment
        f_calendar = new MonthViewFragment();

        this.getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.flContent, f_calendar)
            .commit();

    }

    @Override
    public void onBackPressed() {
        if (l_drawer.isDrawerOpen(GravityCompat.START)) {
            l_drawer.closeDrawer(GravityCompat.START);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment=null;

        if (id == R.id.nav_calendar) {
            // Handle the camera action
            fragment = f_calendar;
        } else if (id == R.id.nav_setting) {
            fragment = f_event;
        }

        // Insert the fragment by replacing any existing fragment
        this.getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.flContent, fragment)
            .commit();


        l_drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
