package com.lod.horaceayala.myapplication;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SplashActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/freshman.ttf");

        final TextView mTitle = (TextView) findViewById(R.id.toolbar_title);
        mTitle.setTypeface(myTypeface);

        findViewById(R.id.progressBar).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this).toBundle());

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this, mTitle, "profile");
                // start the new activity
                startActivity(intent, options.toBundle());
            }
        }, 3000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
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
