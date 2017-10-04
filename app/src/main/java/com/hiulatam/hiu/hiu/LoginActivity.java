package com.hiulatam.hiu.hiu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.hiulatam.hiu.hiu.utils.profileFacebook;

public class LoginActivity extends AppCompatActivity implements FacebookCallback {

    private static final String TAG ="facebook login:   " ;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;
    private  profileFacebook profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageView info= (ImageView) findViewById(R.id.imageInfo);

        FacebookSdk.sdkInitialize(getApplicationContext());


        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager,this);
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    Profile oldProfile,
                    Profile currentProfile) {
                profile=new profileFacebook(currentProfile);
                // App code
            }

        };
        profileTracker.startTracking();


        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,Walkthrough.class);
                startActivity(intent);
            }
        });
        if(isLoggedIn()){

            facebookSuccess();
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    public void onSuccess(Object o) {

        LoginResult loginresult=(LoginResult)o;
        Log.i(TAG, "LoginButton FacebookCallback onSuccess");
        if(loginresult.getAccessToken() != null){
            Log.i(TAG, "Access Token:: "+loginresult.getAccessToken());

            facebookSuccess();
        }
       //String UserId= loginresult.getAccessToken().getUserId();

    }

    private void facebookSuccess() {
        Profile curreProfile = Profile.getCurrentProfile();
        profile=new profileFacebook(curreProfile);
        Intent intent= new Intent(LoginActivity.this,MainActivity.class);
        intent.putExtra("profile",profile);
        startActivity(intent);
    }

    @Override
    public void onCancel() {
        Toast.makeText(this,"cancel",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(FacebookException error) {
        Toast.makeText(this,"error",
                Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
    }



    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }



}

