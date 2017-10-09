package com.hiulatam.hiu.hiu.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hiulatam.hiu.hiu.R;
import com.hiulatam.hiu.hiu.adapter.CelebrityItemAdapter;
import com.hiulatam.hiu.hiu.common.Config;
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);


        bindComponents();
        init();
        addListeners();
    }

    private void bindComponents(){
        recyclerViewCelebirty = getView().findViewById(R.id.recycler_view_celebrity);
    }

    private void init(){

        recyclerViewCelebirty.setHasFixedSize(true);

        reccyclerViewLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewCelebirty.setLayoutManager(reccyclerViewLayoutManager);
        recyclerViewCelebirty.setItemAnimator(new DefaultItemAnimator());

        setCelebrityItemModalList();

        setCelebrityItemAdapter();


    }

    private void addListeners(){

    }

    private void setCelebrityItemAdapter(){
        if (celebrityItemAdapter == null)
            celebrityItemAdapter = new CelebrityItemAdapter(setCelebrityItemModalList());
        recyclerViewCelebirty.setAdapter(celebrityItemAdapter);
    }

    private List<CelebrityItemModal> setCelebrityItemModalList(){
        List<CelebrityItemModal> celebrityItemModalList = new ArrayList<CelebrityItemModal>();

        CelebrityItemModal celebrityItemModal = new CelebrityItemModal();
        celebrityItemModal.setImage("andres_cepeda");
        celebrityItemModal.setName("Andres Cepeda");
        celebrityItemModal.setArticle("Musico");
        celebrityItemModal.setPercentage("6.2");
        celebrityItemModalList.add(celebrityItemModal);

        celebrityItemModal = new CelebrityItemModal();
        celebrityItemModal.setImage("scarlett_johansson");
        celebrityItemModal.setName("Scarlett Johansson");
        celebrityItemModal.setArticle("Actriz");
        celebrityItemModal.setPercentage("7.5");
        celebrityItemModalList.add(celebrityItemModal);


        return celebrityItemModalList;
    }


}
