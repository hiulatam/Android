package com.hiulatam.hiu.hiu.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hiulatam.hiu.hiu.DetailActivity;
import com.hiulatam.hiu.hiu.R;
import com.hiulatam.hiu.hiu.adapter.CelebrityItemAdapter;
import com.hiulatam.hiu.hiu.common.Config;
import com.hiulatam.hiu.hiu.interfaces.ClickListener;
import com.hiulatam.hiu.hiu.interfaces.FilterResultsCallback;
import com.hiulatam.hiu.hiu.modal.CelebrityItemModal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by:  Shiny Solutions
 * Created on:  10/5/17.
 */

public class CelebrityFragment extends Fragment {

    private static final String TAG = "CelebrityFragment - ";

    //UI Components
    private RecyclerView recyclerViewCelebirty;
    private TextView textViewEmptyResult;

    //API
    private RecyclerView.LayoutManager reccyclerViewLayoutManager;


    //Custom Classses
    private CelebrityItemAdapter celebrityItemAdapter;


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

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/7/2017
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        String message = getArguments().getString(Config.EXTRA_MESSAGE);
        View view = inflater.inflate(R.layout.celebrity_fragment, container, false);
        return view;
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/7/2017
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);


        bindComponents();
        init();
        addListeners();
    }

    @Override
    public void onResume(){
        super.onResume();
        Config.logInfo(TAG + "onResume");

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Config.ACTION_SEARCH_QUERY);
        getActivity().registerReceiver(searchBroadcastReceiver, intentFilter);
    }

    @Override
    public void onPause(){
        super.onPause();
        Config.logInfo(TAG + "onPause");
        getActivity().unregisterReceiver(searchBroadcastReceiver);
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/7/2017
     */
    private void bindComponents(){
        recyclerViewCelebirty = getView().findViewById(R.id.recycler_view_celebrity);

        textViewEmptyResult = getView().findViewById(R.id.textViewEmptyResult);
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/7/2017
     */
    private void init(){



        reccyclerViewLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerViewCelebirty.setHasFixedSize(true);
        recyclerViewCelebirty.setLayoutManager(reccyclerViewLayoutManager);
        recyclerViewCelebirty.setItemAnimator(new DefaultItemAnimator());

        setCelebrityItemModalList();

        setCelebrityItemAdapter();


    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/7/2017
     */
    private void addListeners(){

    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/7/2017
     */
    private void setCelebrityItemAdapter(){
        if (celebrityItemAdapter == null)
            celebrityItemAdapter = new CelebrityItemAdapter(setCelebrityItemModalList());
        celebrityItemAdapter.setOnClickListener(clickListener);
        celebrityItemAdapter.setFilterResultsCallback(filterResultsCallback);
        recyclerViewCelebirty.setAdapter(celebrityItemAdapter);
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/7/2017
     * @return
     */
    private List<CelebrityItemModal> setCelebrityItemModalList(){
        List<CelebrityItemModal> celebrityItemModalList = new ArrayList<CelebrityItemModal>();

        CelebrityItemModal celebrityItemModal = new CelebrityItemModal();
        celebrityItemModal.setImage("andres_cepeda");
        celebrityItemModal.setName("Andres Cepeda");
        celebrityItemModal.setArticle("Musico");
        celebrityItemModal.setPercentage(60);
        celebrityItemModal.setEmptyResult("No");
        celebrityItemModalList.add(celebrityItemModal);

        celebrityItemModal = new CelebrityItemModal();
        celebrityItemModal.setImage("scarlett_johansson");
        celebrityItemModal.setName("Scarlett Johansson");
        celebrityItemModal.setArticle("Actriz");
        celebrityItemModal.setPercentage(70);
        celebrityItemModal.setEmptyResult("No");
        celebrityItemModalList.add(celebrityItemModal);


        return celebrityItemModalList;
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/12/2017
     */
    ClickListener clickListener = new ClickListener() {
        @Override
        public void onClick(View view, int position) {
            Log.i(Config.TAG, TAG + "clickListener - onClick");
            Intent intent = new Intent();
            intent.setClass(getActivity(), DetailActivity.class);
            intent.putExtra(Config.EXTRA_CELEBRITY_ITEM, celebrityItemAdapter.getItem(position));
            startActivity(intent);
        }

        @Override
        public void onLongClick(View view, int position) {

        }
    };

    BroadcastReceiver searchBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Config.logInfo(TAG + "onReceive");
            if (intent.getAction().equalsIgnoreCase(Config.ACTION_SEARCH_QUERY)){
                String query = null;
                Bundle bundle = intent.getExtras();
                if (bundle != null){
                    if (bundle.containsKey(Config.EXTRA_SEARCH_QUERY)){
                        query = bundle.getString(Config.EXTRA_SEARCH_QUERY);
                    }
                }

                celebrityItemAdapter.getFilter().filter(query);



            }
        }
    };


    FilterResultsCallback filterResultsCallback = new FilterResultsCallback() {
        @Override
        public void getFilterResultCount(int filterResultCount) {
            if (filterResultCount > 0){
                textViewEmptyResult.setVisibility(View.GONE);
                recyclerViewCelebirty.setVisibility(View.VISIBLE);
            }else{
                textViewEmptyResult.setText(getString(R.string.empty_result));
                textViewEmptyResult.setVisibility(View.VISIBLE);
                recyclerViewCelebirty.setVisibility(View.GONE);
            }
        }
    };


}
