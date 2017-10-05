package com.hiulatam.hiu.hiu.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hiulatam.hiu.hiu.R;
import com.hiulatam.hiu.hiu.common.Config;

/**
 * Created by:  Shiny Solutions
 * Created on:  10/5/17.
 */

public class CelebrityFragment extends Fragment {

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/5/17
     * @param message
     * @return
     */
    public static final CelebrityFragment newInstance(String message){
        CelebrityFragment celebrityFragment = new CelebrityFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Config.EXTRA_MESSAGE, message);
        celebrityFragment.setArguments(bundle);
        return celebrityFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        String message = getArguments().getString(Config.EXTRA_MESSAGE);
        View view = inflater.inflate(R.layout.celebrity_item, container, false);
        return view;
    }

}
