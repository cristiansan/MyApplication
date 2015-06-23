package com.lod.horaceayala.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class Main2Activity extends AppCompatActivity {

    private static final String NAV_ITEM_ID = "navItemId";
    private static final long DRAWER_CLOSE_DELAY_MS = 300;

    private DrawerLayout mDrawerLayout;
    private int mNavItemId;
    private MainFragment mFirstFragment = new MainFragment();
    private FixtureFragment mFixtureFragment = new FixtureFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        toolbar.setNavigationIcon(android.R.drawable.ic_menu_more);

        // load saved navigation state if present
        if (null == savedInstanceState) {
            mNavItemId = R.id.drawer_item_1;
        } else {
            mNavItemId = savedInstanceState.getInt(NAV_ITEM_ID);
            mNavItemId = R.id.drawer_item_1;
        }

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, android.R.string.copy, android.R.string.cancel) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
                syncState();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                syncState();
            }
        };
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.syncState();


        // listen for navigation events
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem menuItem) {
                // allow some time after closing the drawer before performing real navigation
                // so the user can see what is happening
                mDrawerLayout.closeDrawer(GravityCompat.START);
                Handler mDrawerActionHandler = new Handler();
                mDrawerActionHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        navigate(menuItem.getItemId());
                    }
                }, DRAWER_CLOSE_DELAY_MS);
                return true;
            }
        });

        navigate(mNavItemId);
    }

    private void navigate(int mNavItemId) {
        FragmentTransaction ft = null;
        switch (mNavItemId) {
            case R.id.drawer_item_1:
//                getSupportFragmentManager().beginTransaction().remove(mFixtureFragment).commit();
//                getSupportFragmentManager().beginTransaction().addToBackStack(null).commit();

                ft = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                ft.replace(R.id.content, mFirstFragment, "fragment");
                ft.commit();
                break;
            case R.id.drawer_item_2:
//                getSupportFragmentManager().beginTransaction().remove(mFirstFragment).commit();
//                getSupportFragmentManager().beginTransaction().setCustomAnimations(
//                        R.anim.card_flip_right_in, R.anim.card_flip_right_out,
//                        R.anim.card_flip_left_in, R.anim.card_flip_left_out).replace(R.id.content, mFixtureFragment).addToBackStack(null).commit();

                ft = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                ft.replace(R.id.content, mFixtureFragment, "fragment");
                ft.commit();
                break;
            default:
                // ignore
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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
}
