package com.hiulatam.hiu.hiu.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hiulatam.hiu.hiu.R;
import com.hiulatam.hiu.hiu.fragments.CelebrityFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by:  Shiny Solutions
 * Created on:  10/5/17
 */

public class CelebrityFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "CelebrityFragmentPagerAdapter - ";

    private List<Fragment> fragments;
    private String[] pageTitles;


    /**
     * Created by:  Shiny Solutions
     * Created on:  10/5/17
     * @param fm
     */
    public CelebrityFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        pageTitles = context.getResources().getStringArray(R.array.page_title);

        this.fragments = getFragments();

    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/5/17
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/5/17
     * @return
     */
    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/7/17
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position){
        return pageTitles[position];
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/5/17
     * @return
     */
    public List<Fragment> getFragments(){
        List<Fragment> fragmentList = new ArrayList<Fragment>();

        for (int i = 0; i < pageTitles.length; i++){
            fragmentList.add(new CelebrityFragment().newInstance(pageTitles[i]));
        }

        return fragmentList;
    }
}
