package com.spurs.fourseasons;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by spurs on 2017/08-05(005).
 */

public class SummerPageAdapter extends FragmentPagerAdapter {

    Fragment[] frags=new Fragment[2];
    String[] titles=new String[]{"명소","축제"};

    public SummerPageAdapter(FragmentManager fm) {
        super(fm);

        frags[0]=new SummerTab2Fragment();
        frags[1]=new SummerTab3Fragment();
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
