package com.lod.horaceayala.myapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String NAV_ITEM_ID = "navItemId";
    private static final long DRAWER_CLOSE_DELAY_MS = 300;

    private MainFragment mFirstFragment = new MainFragment();
    private FixtureFragment mFixtureFragment = new FixtureFragment();

    private ViewGroup rootContainer;
    private Scene scene1;
    private Scene scene2;
    private int mNavItemId;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.this.setTitle("");

        rootContainer = (ViewGroup) findViewById(R.id.rootContainer);

        scene1 = Scene.getSceneForLayout(rootContainer, R.layout.activity_main2, this);
        scene2 = Scene.getSceneForLayout(rootContainer, R.layout.open_note, this);

        scene1.enter();
        scene1.setEnterAction(new Runnable() {
            @Override
            public void run() {
//                navigate(mNavItemId);
            }
        });

        Main(savedInstanceState);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/freshman.ttf");

        Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.toolbar_title);
        mTitle.setTypeface(myTypeface);
    }

    private void Main(Bundle savedInstanceState) {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                ft = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                ft.replace(R.id.content, mFirstFragment, "fragment");
                ft.commit();
                break;
            case R.id.drawer_item_2:
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

    public void btnSceneTest(View v) {
        TransitionManager.go(scene2);

        getSupportFragmentManager().beginTransaction().remove(mFirstFragment).commit();

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Title");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.go(scene1);

                Main(null);
            }
        });

        WebView webview = (WebView)findViewById(R.id.article);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebChromeClient(new WebChromeClient());
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
//        webview.loadData("<h1 style=\"color:blue\">This is a Blue Heading</h1><iframe src=\"https://vine.co/v/bnrtW52x1uJ/card?mute=1\"\n" +
//                "width=\"100%\" height=\"100%\" frameborder=\"0\"></iframe><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1>", "text/html", "UTF-8");
        webview.loadUrl("http://www.90min.com/es/hybrid/posts/2278928-escandalo-y-corrupcion-los-audios-que-involucran-a-grondona-en-el-arreglo-de-partidos?app=1&ref=Android&app_name=90min&utm_source=app&utm_medium=feed");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
