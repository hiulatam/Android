package com.hiulatam.hiu.hiu;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hiulatam.hiu.hiu.common.Config;

/**
 * Created by: Shiny Solutions
 * Created on: 9/30/17
 */

public class DashboardActivity extends AppCompatActivity {

    public static final String TAG = "DashboardActivity - ";

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    LinearLayout linearLayoutTitle;
    ImageButton imageButtonNavigationView;
    SearchView searchViewCelebrity;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        bindComponents();
        init();
        addListeners();
    }

    /**
     * Created by: Shiny Solutions
     * Created on: 9/30/17
     */
    public void bindComponents(){

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        linearLayoutTitle = (LinearLayout) findViewById(R.id.linear_layout_title);

        imageButtonNavigationView = (ImageButton) findViewById(R.id.image_button_navigation_view);

        searchViewCelebrity = (SearchView) findViewById(R.id.search_view_celebrity);
    }

    /**
     * Created by: Shiny Solutions
     * Created on: 9/30/17
     */
    public void init(){

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


    }

    /**
     * Created by: Shiny Solutions
     * Created on: 9/30/17
     */
    public void addListeners(){
        imageButtonNavigationView.setOnClickListener(onClickListener);
        searchViewCelebrity.setOnSearchClickListener(onClickListener);
        searchViewCelebrity.setOnCloseListener(onCloseListener);
    }

    /**
     * Created by: Shiny Solutions
     * Created on: 10/03/17
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
            }
        }
    };

    SearchView.OnCloseListener onCloseListener = new SearchView.OnCloseListener() {
        @Override
        public boolean onClose() {
            imageButtonNavigationView.setVisibility(View.VISIBLE);
            linearLayoutTitle.setVisibility(View.VISIBLE);
            return false;
        }
    };



}
