package com.hiulatam.hiu.hiu;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.steelkiwi.instagramhelper.model.InstagramUser;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.models.User;

import retrofit2.Call;

public class TwitterActivity extends FragmentActivity {
    public static final String TAG = "TwitterActivity - ";
    private TwitterAuthClient mTwitterAuthClient;
    private TwitterSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);
        Twitter.initialize(this);
        //∫TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);

        mTwitterAuthClient= new TwitterAuthClient();
        mTwitterAuthClient.authorize(this, new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> twitterSessionResult) {
                // Success
                Log.i(TAG,"ok logged in");
                session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;
                Long id_user=session.getUserId();
                //User user = showUser(id_user);
                getUserProfile(token);
               // Twu user = instagramHelper.getInstagramUser(this);


            }

            @Override
            public void failure(TwitterException e) {
                e.printStackTrace();
                Log.i(TAG,"ups! logged error");
                //Toast.makeText(this, "Login failed", Toast.LENGTH_LONG).show();
                Intent inten= new Intent(TwitterActivity.this,LoginActivity.class);
                startActivity(inten);
            }
        });


    }

    private void getUserProfile(final String userToken) {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        twitterApiClient.getAccountService().verifyCredentials(true,true,false).enqueue(new Callback<User>() {
            @Override
            public void success(Result<User> userResult) {
                try {
                    User user = userResult.data;
                    String fullname = user.name;
                    Long twitterID = user.getId();
                    String userSocialProfile = user.profileImageUrl;
                    String userEmail = user.email;
                    String userFirstNmae = fullname.substring(0, fullname.lastIndexOf(" "));
                    String userLastNmae = fullname.substring(fullname.lastIndexOf(" "));
                    String userScreenName = user.screenName;
                    MyApplication.profile.profileTwitter=user;
                    Intent intent= new Intent(TwitterActivity.this,DashboardActivity.class);
                    intent.putExtra("profile", MyApplication.profile);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void failure(TwitterException e) {
                Log.i(TAG,"ups! getting user error");
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);
        // Pass the activity result to the login button.
        //loginButton.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
