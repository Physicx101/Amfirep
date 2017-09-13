package com.example.fauziw97.taxapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.fauziw97.taxapp.FragmentFour;
import com.example.fauziw97.taxapp.FragmentOne;
import com.example.fauziw97.taxapp.FragmentThree;
import com.example.fauziw97.taxapp.FragmentTwo;

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
                FragmentOne tab1 = new FragmentOne();
                return tab1;
            case 1:
                FragmentTwo tab2 = new FragmentTwo();
                return tab2;
            case 2:
                FragmentThree tab3 = new FragmentThree();
                return tab3;
            case 3:
                FragmentFour tab4 = new FragmentFour();
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

