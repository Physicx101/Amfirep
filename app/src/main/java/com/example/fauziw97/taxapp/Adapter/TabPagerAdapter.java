package com.example.fauziw97.taxapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.fauziw97.taxapp.FragmentTurtle;
import com.example.fauziw97.taxapp.FragmentLizard;
import com.example.fauziw97.taxapp.FragmentSnake;
import com.example.fauziw97.taxapp.FragmentFrog;

/**
 * Created by Fauziw97 on 9/12/17.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount = tabCount;
    }


    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                FragmentLizard tab1 = new FragmentLizard();
                return tab1;
            case 1:
                FragmentFrog tab2 = new FragmentFrog();
                return tab2;
            case 2:
                FragmentSnake tab3 = new FragmentSnake();
                return tab3;
            case 3:
                FragmentTurtle tab4 = new FragmentTurtle();
                return tab4;
            default:
                return null;
        }
    }


    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}

