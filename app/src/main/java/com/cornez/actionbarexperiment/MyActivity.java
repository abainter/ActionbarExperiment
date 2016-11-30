package com.cornez.actionbarexperiment;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MyActivity extends Activity {

    private static final String TAB_KEY_INDEX = "tab_key";
    private Fragment appetizerFragment;
    private Fragment entreeFragment;
    private Fragment dessertFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //SET THE ACTIONBAR
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);

        //CREATE THE TABS AND BIND THEM TO THE ACTIONBAR
        ActionBar.Tab appetizerTab = actionBar.newTab().setText(getString(R.string.ui_tabname_appetizer));
        ActionBar.Tab entreeTab = actionBar.newTab().setText(getString(R.string.ui_tabname_entree));
        ActionBar.Tab dessertTab = actionBar.newTab().setText(getString(R.string.ui_tabname_dessert));

        //CREATE EACH FRAGMENT AND BIND THEM TO THE ACTIONBAR
        appetizerFragment = new AppetizerFragment();
        entreeFragment = new EntreeFragment();
        dessertFragment = new DessertFragment();

        //SET THE LISTENER EVENTS FOR EACH OF THE ACTIONBAR TABS
        appetizerTab.setTabListener(new MyTabsListener(appetizerFragment, getApplicationContext()));
        entreeTab.setTabListener(new MyTabsListener(entreeFragment, getApplicationContext()));
        dessertTab.setTabListener(new MyTabsListener(dessertFragment, getApplicationContext()));

        //ADD EACH OF THE TABS TO THE ACTIONBAR
        actionBar.addTab(appetizerTab);
        actionBar.addTab(entreeTab);
        actionBar.addTab(dessertTab);

        //RESTORE NAVIGATION
        if(savedInstanceState != null){
            actionBar.setSelectedNavigationItem(savedInstanceState.getInt(TAB_KEY_INDEX,0));
        }
    }

    class MyTabsListener implements ActionBar.TabListener {
        public Fragment fragment;

        public MyTabsListener(Fragment f, Context context) {
            fragment = f;
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.replace(R.id.fragment_container, fragment);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.remove(fragment);
        }
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu){
            //Inflate the menu;
            getMenuInflater().inflate(R.menu.my, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            //HANDLE ACTION BAR ITEM CLICKS HERE. THE ACTION BAR WILL
            //AUTOMATICALLY HANDLE CLICKS ON THE HOME/UP BUTTON, SO LONG
            //AS YOU SPECIFY A PARENT ACTIVITY IN ANDROIDMANIFEST.XML.
            int id = item.getItemId();
            if (id == R.id.action_settings) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

}
