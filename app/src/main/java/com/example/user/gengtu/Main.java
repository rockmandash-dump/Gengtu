package com.example.user.gengtu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TabWidget;
import android.widget.TextView;


public class Main extends ActionBarActivity {



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.main);

            FragmentTabHost tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);



            tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);


            //1
            tabHost.addTab(tabHost.newTabSpec("Hot")
                            .setIndicator(getString(R.string.hot)),
                    HotFragment.class,
                    null);
            //2
            tabHost.addTab(tabHost.newTabSpec("Newly")
                            .setIndicator(getString(R.string.newly)),
                    NewlyFragment.class,
                    null);
            //3
            tabHost.addTab(tabHost.newTabSpec("Classify")
                            .setIndicator(getString(R.string.classify)),
                    ClassifyFragment.class,
                    null);
            //4
            tabHost.addTab(tabHost.newTabSpec("Take")
                            .setIndicator(getString(R.string.take)),
                    TakeFragment.class,
                    null);


            TabWidget tabWidget =tabHost.getTabWidget();
            for ( int i = 0; i < 4; i++ ) {
                LinearLayout tabView = (LinearLayout) tabWidget.getChildAt(i);
                TextView tabTextView = (TextView) tabView.findViewById(android.R.id.title);
                tabTextView.setTextSize(22);

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
                Setting();
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        private void Setting() {
        Intent intent = new Intent(this,Setting.class);
        startActivity(intent);
        }

    }

