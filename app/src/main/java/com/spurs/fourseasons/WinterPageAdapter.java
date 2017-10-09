package com.spurs.fourseasons;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by spurs on 2017/08-05(005).
 */

public class WinterPageAdapter extends FragmentPagerAdapter {

    Fragment[] frags=new Fragment[2];
    String[] titles=new String[]{"명소","축제"};

    public WinterPageAdapter(FragmentManager fm) {
        super(fm);

        frags[0]=new WinterTab2Fragment();
        frags[1]=new SpringTab3Fragment();
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
