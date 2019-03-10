package com.example.naoreen.cmdf;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    @BindView(R.id.navigationView)
    BottomNavigationView navigation;

    //@BindView(R.id.toolbar) Toolbar toolbar;
    private int saveState;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch(menuItem.getItemId()) {
                case R.id.menu_overview:
                    selectedFragment = new OverviewFragment();
                    break;
                case R.id.menu_my_tasks:
                    selectedFragment = new MyTasksFragment();
                    break;
                case R.id.menu_points:
                    selectedFragment = new PointsFragment();
                    break;
            }
            //BottomNavigationViewHelper.disableShiftMode(navigation);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, selectedFragment);
            transaction.commit();
            return true;
        }
    };

    @Override
    public void onCreate(Bundle savedInstantState){
        super.onCreate(savedInstantState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
       // BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        int check = getIntent().getIntExtra("check", R.id.menu_overview);

        if(savedInstantState != null){
            navigation.setSelectedItemId(saveState);
        } else {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch(check) {
                case R.id.menu_overview:
                    transaction.replace(R.id.container, new OverviewFragment());
                    break;
                case R.id.menu_my_tasks:
                    transaction.replace(R.id.container, new MyTasksFragment());
                    break;
                case R.id.menu_points:
                    transaction.replace(R.id.container, new PointsFragment());
                    break;
            }
            transaction.commit();
            navigation.setSelectedItemId(check);
        }





    }




}
