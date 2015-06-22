package com.lod.horaceayala.myapplication;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends ActionBarActivity {

    private static final String NAV_ITEM_ID = "navItemId";
    private static final long DRAWER_CLOSE_DELAY_MS = 300;

    private DrawerLayout mDrawerLayout;
    private int mNavItemId;
    private MainFragment mFirstFragment = new MainFragment();
    private FixtureFragment mFixtureFragment = new FixtureFragment();
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;


    NavigationAdapter navigationMenuAdapter;
    NavigationItem[] navigationMenuItems = {
            new NavigationItem(android.R.string.cancel, android.R.drawable.ic_delete, new MainFragment()),
            new NavigationItem(android.R.string.copy, android.R.drawable.ic_delete, new MainFragment()),
            new NavigationItem(android.R.string.cut, android.R.drawable.ic_dialog_alert, new MainFragment()),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        navigationMenuAdapter = new NavigationAdapter(this, navigationMenuItems);
        mDrawerList.setAdapter(navigationMenuAdapter);

//        addDrawerItems();
        setupDrawer();



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void addDrawerItems() {
        String[] osArray = { "Android", "iOS", "Windows", "OS X", "Linux" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Main2Activity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, android.R.string.cancel, android.R.string.copy) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
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

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class NavigationAdapter extends BaseAdapter {
        private Context context;
        private NavigationItem[] navigationItems;

        public NavigationAdapter(Context context, NavigationItem[] items) {
            this.context = context;
            navigationItems = items;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.list_item_navigation, parent, false);
            } else {
                row = convertView;
            }

            TextView tvTitle = (TextView) row.findViewById(R.id.textView);
            ImageView ivIcon = (ImageView) row.findViewById(R.id.imageView);

            tvTitle.setText(navigationItems[position].stringTitle);
            ivIcon.setImageResource(navigationItems[position].drawableIcon);
            return row;
        }
    }

    private class NavigationItem
    {
        /**
         *
         * @param stringTitle The id of the string resource of the text of the item.
         * @param drawableIcon The id of the drawable resource of icon of the item.
         * @param fragment The Fragment to be loaded upon selecting the item.
         */
        public NavigationItem(int stringTitle, int drawableIcon, Fragment fragment)
        {
            this.stringTitle = stringTitle;
            this.drawableIcon = drawableIcon;
            this.fragment = fragment;
        }

        /**
         * The id of the string resource of the text of the item.
         */
        public int stringTitle;

        /**
         * The id of the drawable resource of icon of the item.
         */
        public int drawableIcon;

        /**
         * The Fragment to be loaded upon selecting the item.
         */
        public Fragment fragment;
    }
}
