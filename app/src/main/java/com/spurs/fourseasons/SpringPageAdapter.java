package com.spurs.fourseasons;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by spurs on 2017/08-05(005).
 */

public class SpringPageAdapter extends FragmentPagerAdapter {

    Fragment[] frags=new Fragment[3];
    String[] titles=new String[]{"날씨","명소","축제"};

    public SpringPageAdapter(FragmentManager fm) {
        super(fm);
        frags[0]=new SpringTab1Fragment();
        frags[1]=new SpringTab2Fragment();
        frags[2]=new SpringTab3Fragment();
    }

    @Override
    public Fragment getItem(int position) {
        return frags[position];
    }

    @Override
    public int getCount() {
        return frags.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
