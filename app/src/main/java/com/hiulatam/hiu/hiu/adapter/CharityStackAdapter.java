package com.hiulatam.hiu.hiu.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.an.customfontview.CustomTextView;
import com.hiulatam.hiu.hiu.R;
import com.hiulatam.hiu.hiu.modal.CelebrityItemModal;
import com.huxq17.swipecardsview.BaseCardAdapter;

import java.util.ArrayList;

/**
 * Created by:  Shiny Solutions
 * Created on:  11/3/17.
 */

public class CharityStackAdapter extends ArrayAdapter<CelebrityItemModal> {

    private static final String TAG = "CharityStackAdapter - ";

    private Context context;

    private ArrayList<CelebrityItemModal> celebrityItemModalArrayList;

    public CharityStackAdapter(Context context, ArrayList<CelebrityItemModal> celebrityItemModalArrayList) {
        super(context, 0, celebrityItemModalArrayList);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CelebrityItemModal celebrityItemModal = getItem(i);
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_charity, viewGroup, false);
        }

        CustomTextView customTextViewCharityName = (CustomTextView) view.findViewById(R.id.customTextViewCharityName);

        customTextViewCharityName.setText(String.valueOf(celebrityItemModal.getName()));

        return view;
    }
}
