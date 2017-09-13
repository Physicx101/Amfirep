package com.example.fauziw97.taxapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;


import com.example.fauziw97.taxapp.Adapter.TabPagerAdapter;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("LIZARD").setIcon(R.drawable.selector_lizard));
        tabLayout.addTab(tabLayout.newTab().setText("FROG").setIcon(R.drawable.selector_frog));
        tabLayout.addTab(tabLayout.newTab().setText("SNAKE").setIcon(R.drawable.selector_snake));
        tabLayout.addTab(tabLayout.newTab().setText("TURTLE").setIcon(R.drawable.selector_turtle));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        viewPager = (ViewPager) findViewById(R.id.viewPager);

        //set adapter to ViewPager
        viewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        //Adding onTabSelectedListener to swipe views
        tabLayout.addOnTabSelectedListener(this);

        /*TabLayout.Tab tabLizard = tabLayout.getTabAt(0);
        tabLizard.setIcon(R.drawable.selector_lizard);
        TabLayout.Tab tabFrog = tabLayout.getTabAt(1);
        tabLizard.setIcon(R.drawable.selector_frog);
        TabLayout.Tab tabSnake = tabLayout.getTabAt(2);
        tabLizard.setIcon(R.drawable.selector_snake);
        TabLayout.Tab tabTurtle = tabLayout.getTabAt(3);
        tabLizard.setIcon(R.drawable.selector_turtle);*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
