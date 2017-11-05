package com.hiulatam.hiu.hiu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.hiulatam.hiu.hiu.adapter.CharityStackAdapter;
import com.hiulatam.hiu.hiu.common.Config;
import com.hiulatam.hiu.hiu.modal.CelebrityItemModal;
import com.yuyakaido.android.cardstackview.CardStackView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by:  Shiny Solutions
 * Created on:  10/22/17
 */

public class CharityActivity extends AppCompatActivity {

    public static final String TAG = "CharityActivity - ";

    DrawerLayout drawerLayout;
    ImageButton imageButtonNavigationView;
    Toolbar toolbar;
    SearchView searchViewCelebrity;
    LinearLayout linearLayoutTitle;
    Button buttonNext;
    CardStackView cardStackViewCharity;

    private CelebrityItemModal celebrityItemModal;

    private CharityStackAdapter charityStackAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charity_activity);

        bindComponents();
        init();
        addListeners();
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/22/17
     */
    private void bindComponents(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        imageButtonNavigationView = (ImageButton) findViewById(R.id.image_button_navigation_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        searchViewCelebrity = (SearchView) findViewById(R.id.search_view_celebrity);

        linearLayoutTitle = (LinearLayout) findViewById(R.id.linear_layout_title);

        buttonNext = (Button) findViewById(R.id.buttonNext);

        cardStackViewCharity = (CardStackView) findViewById(R.id.cardStackViewChairty);
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/22/17
     */
    private void init(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            if (extras.containsKey(Config.EXTRA_CELEBRITY_ITEM)){
                celebrityItemModal = (CelebrityItemModal) extras.getParcelable(Config.EXTRA_CELEBRITY_ITEM);
            }
        }

        setCharityAdapter();
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/22/17
     */
    private void addListeners(){
        imageButtonNavigationView.setOnClickListener(onClickListener);
        searchViewCelebrity.setOnSearchClickListener(onClickListener);
        searchViewCelebrity.setOnCloseListener(onCloseListener);
        buttonNext.setOnClickListener(onClickListener);
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/23/17
     */
    private void openPaymentDetail(){
        Intent intent = new Intent();
        intent.setClass(this, PaymentDetailActivity.class);
        intent.putExtra(Config.EXTRA_CELEBRITY_ITEM, celebrityItemModal);
        startActivity(intent);
    }

    private void setCharityAdapter(){
        if (charityStackAdapter == null){
            charityStackAdapter = new CharityStackAdapter(this, setCelebrityItemModalList());
        }
        cardStackViewCharity.setAdapter(charityStackAdapter);
    }

    private ArrayList<CelebrityItemModal> setCelebrityItemModalList(){
        ArrayList<CelebrityItemModal> celebrityItemModalList = new ArrayList<CelebrityItemModal>();

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

        celebrityItemModal = new CelebrityItemModal();
        celebrityItemModal.setImage("scarlett_johansson");
        celebrityItemModal.setName("Scarlett Johansson");
        celebrityItemModal.setArticle("Actriz");
        celebrityItemModal.setPercentage("7.5");
        celebrityItemModalList.add(celebrityItemModal);

        celebrityItemModal = new CelebrityItemModal();
        celebrityItemModal.setImage("scarlett_johansson");
        celebrityItemModal.setName("Scarlett Johansson");
        celebrityItemModal.setArticle("Actriz");
        celebrityItemModal.setPercentage("7.5");
        celebrityItemModalList.add(celebrityItemModal);


        return celebrityItemModalList;
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/22/17
     */
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.image_button_navigation_view:
                    drawerLayout.openDrawer(Gravity.LEFT);
                    break;
                case R.id.search_view_celebrity:
                    imageButtonNavigationView.setVisibility(View.GONE);
                    linearLayoutTitle.setVisibility(View.GONE);
                    break;
                case R.id.buttonNext:
                    openPaymentDetail();
                    break;
            }
        }
    };

    /**
     * Created by:  Shiny Solutions
     * Created on:  10/22/17
     */
    SearchView.OnCloseListener onCloseListener = new SearchView.OnCloseListener() {
        @Override
        public boolean onClose() {
            imageButtonNavigationView.setVisibility(View.VISIBLE);
            linearLayoutTitle.setVisibility(View.VISIBLE);
            return false;
        }
    };
}