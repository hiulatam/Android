package com.hiulatam.hiu.hiu.adapter;

import android.animation.ObjectAnimator;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.an.customfontview.CustomTextView;
import com.github.lzyzsd.circleprogress.CircleProgress;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.hiulatam.hiu.hiu.R;
import com.hiulatam.hiu.hiu.common.Config;
import com.hiulatam.hiu.hiu.interfaces.ClickListener;
import com.hiulatam.hiu.hiu.interfaces.FilterResultsCallback;
import com.hiulatam.hiu.hiu.modal.CelebrityItemModal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by:  Shiny Solutions
 * Created on:  10/8/17
 */

public class CelebrityItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable{

    private static final String TAG = "CelebrityItemAdapter - ";

    private List<CelebrityItemModal> celebrityItemModalList;
    private List<CelebrityItemModal> celebrityItemModalListBackup;

    private ClickListener clickListener;
    private FilterResultsCallback filterResultsCallback;

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/8/17
     * @param celebrityItemModalList
     */
    public CelebrityItemAdapter(List<CelebrityItemModal> celebrityItemModalList){
        this.celebrityItemModalListBackup = celebrityItemModalList;
        this.celebrityItemModalList = celebrityItemModalList;
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/12/2017
     * @param clickListener
     */
    public void setOnClickListener(ClickListener clickListener){
        Log.i(Config.TAG, TAG + "setOnClickListener");
        this.clickListener = clickListener;
    }

    public void setFilterResultsCallback(FilterResultsCallback filterResultsCallback){
        this.filterResultsCallback = filterResultsCallback;
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
        celebrityItemViewHolder.textViewCelebrityPercentage.setText(String.valueOf(celebrityItemModal.getPercentage()));

        celebrityItemViewHolder.progressBar.setDonut_progress(String.valueOf(celebrityItemModal.getPercentage()));
        celebrityItemViewHolder.progressBar.setText(String.valueOf(celebrityItemModal.getPercentage()));



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

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/12/2017
     * @param position
     * @return
     */
    public CelebrityItemModal getItem(int position){
        return celebrityItemModalList.get(position);
    }

    @Override
    public Filter getFilter() {
        Config.logInfo(TAG + "getFilter");
        return new SearchFilter();
    }

    public class CelebrityItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imageViewCelebrity;
        public CardView cardViewCelebrityItem;
        public CustomTextView textViewCelebrityName, textViewCelebrityArticle, textViewCelebrityPercentage;
        public DonutProgress progressBar;

        /**
         * Created by: Shiny Solutions
         * Created on: 10/12/2017
         * @param v
         */
        public CelebrityItemViewHolder(View v){
            super(v);

            Log.i(Config.TAG, TAG + "CelebrityItemViewHolder");

            imageViewCelebrity = (ImageView) v.findViewById(R.id.image_view_celebrity);
            cardViewCelebrityItem = (CardView) v.findViewById(R.id.card_view_celebrity_item);
            textViewCelebrityName = (CustomTextView) v.findViewById(R.id.text_view_celebrity_name);
            textViewCelebrityArticle = (CustomTextView) v.findViewById(R.id.text_view_celebrity_article);
            textViewCelebrityPercentage = (CustomTextView) v.findViewById(R.id.text_view_celebrity_percentage);
            progressBar = (DonutProgress) v.findViewById(R.id.celebrity_rating);

            imageViewCelebrity.setOnClickListener(this);

        }

        /**
         * Created by:  Shiny Solutions
         * Created on:  10/12/2017
         * @param view
         */
        @Override
        public void onClick(View view) {
            Log.i(Config.TAG, TAG + "onClick");
            clickListener.onClick(view, getAdapterPosition());
        }
    }

    private class SearchFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            Config.logInfo(TAG + "performFiltering");
            String filterString = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (filterString.trim().equalsIgnoreCase(Config.kAll)){
                filterResults.values = celebrityItemModalListBackup;
                filterResults.count = celebrityItemModalListBackup.size();
            }else{
                if (filterString.trim().length() > 0){
                    List<CelebrityItemModal> listCelebrityItemModal = celebrityItemModalListBackup;
                    List<CelebrityItemModal> filteredlistCelebrityItemModal = new ArrayList<CelebrityItemModal>();

                    String filterableString;
                    CelebrityItemModal filteredCelebrityItemModal;

                    for (int i = 0; i < listCelebrityItemModal.size(); i++){
                        filterableString = listCelebrityItemModal.get(i).getName();
                        filteredCelebrityItemModal = listCelebrityItemModal.get(i);
                        if (filterableString.toLowerCase().contains(filterString)){
                            filteredlistCelebrityItemModal.add(filteredCelebrityItemModal);
                        }
                    }
                    filterResults.values = filteredlistCelebrityItemModal;
                    filterResults.count = filteredlistCelebrityItemModal.size();
                }else{
                    filterResults.values = celebrityItemModalListBackup;
                    filterResults.count = celebrityItemModalListBackup.size();
                }
            }




            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            Config.logInfo(TAG + "publishResults");
            celebrityItemModalList = (List<CelebrityItemModal>) filterResults.values;
            filterResultsCallback.getFilterResultCount(celebrityItemModalList.size());
            notifyDataSetChanged();
        }
    }
}
