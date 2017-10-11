package com.hiulatam.hiu.hiu.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.an.customfontview.CustomTextView;
import com.hiulatam.hiu.hiu.R;
import com.hiulatam.hiu.hiu.modal.CelebrityItemModal;

import java.util.List;

/**
 * Created by:  Shiny Solutions
 * Created on:  10/8/17
 */

public class CelebrityItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final String TAG = "CelebrityItemAdapter - ";

    private List<CelebrityItemModal> celebrityItemModalList;

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/8/17
     * @param celebrityItemModalList
     */
    public CelebrityItemAdapter(List<CelebrityItemModal> celebrityItemModalList){
        this.celebrityItemModalList = celebrityItemModalList;
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/8/17
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.celebrity_item, parent, false);

        CelebrityItemViewHolder celebrityItemViewHolder = new CelebrityItemViewHolder(v);



        return celebrityItemViewHolder;
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/8/17
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CelebrityItemViewHolder celebrityItemViewHolder = (CelebrityItemViewHolder) holder;
        CelebrityItemModal celebrityItemModal = celebrityItemModalList.get(position);
        if (celebrityItemModal.getImage().equalsIgnoreCase("andres_cepeda")) {
            celebrityItemViewHolder.imageViewCelebrity.setImageResource(R.drawable.andres_cepeda);
        }
        if (celebrityItemModal.getImage().equalsIgnoreCase("scarlett_johansson")) {
            celebrityItemViewHolder.imageViewCelebrity.setImageResource(R.drawable.scarlett_johansson);
        }

        celebrityItemViewHolder.textViewCelebrityName.setText(String.valueOf(celebrityItemModal.getName()));
        celebrityItemViewHolder.textViewCelebrityArticle.setText(String.valueOf(celebrityItemModal.getArticle()));

    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/8/17
     * @return
     */
    @Override
    public int getItemCount() {
        return celebrityItemModalList.size();
    }

    public static class CelebrityItemViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageViewCelebrity;
        public CardView cardViewCelebrityItem;
        public CustomTextView textViewCelebrityName, textViewCelebrityArticle;

        public CelebrityItemViewHolder(View v){
            super(v);

            imageViewCelebrity = (ImageView) v.findViewById(R.id.image_view_celebrity);
            cardViewCelebrityItem = (CardView) v.findViewById(R.id.card_view_celebrity_item);
            textViewCelebrityName = (CustomTextView) v.findViewById(R.id.text_view_celebrity_name);
            textViewCelebrityArticle = (CustomTextView) v.findViewById(R.id.text_view_celebrity_article);
        }
    }
}
