package com.hiulatam.hiu.hiu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.an.customfontview.CustomButton;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity - ";

    CustomButton buttonSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageView info= (ImageView) findViewById(R.id.imageInfo);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,Walkthrough.class);
                startActivity(intent);
            }
        });

        bindComponents();
        init();
        addListeners();
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  09/30/2017
     */
    private void bindComponents(){
        buttonSignIn = (CustomButton) findViewById(R.id.sign_in);

    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  09/30/2017
     */
    private void init(){

    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  09/30/2017
     */
    private void addListeners(){
        buttonSignIn.setOnClickListener(clickListener);
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  09/30/2017
     */
    private void showDashboard(){
        Intent intent = new Intent();
        intent.setClass(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Created by:  Shiny Solutions
     * Created on:  09/30/2017
     */
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showDashboard();
        }
    };
}
