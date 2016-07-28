package com.ver_techs.sth_android.activities;

import com.ver_techs.sth_android.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class FacebookLoginActivity extends FragmentActivity {

    private LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(isLoggedIn()){
            Intent i = new Intent(FacebookLoginActivity.this, MainActivity.class);
            startActivity(i);
        }

        setContentView(R.layout.activity_login);

        loginButton = (LoginButton) findViewById(R.id.authButton);

        callbackManager = CallbackManager.Factory.create(); //create facebook callbackmanager

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.i("aaki", "fb login success");
                Intent i = new Intent(FacebookLoginActivity.this, MainActivity.class);
                startActivity(i);

            }

            @Override
            public void onCancel() {
                // App code
                Log.i("aaki", "fb login cancel");
                Intent i = new Intent(FacebookLoginActivity.this, LandingActivity.class);
                startActivity(i);
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.i("aaki", "fb login error");
            }
        });
    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if(isLoggedIn()) {
            Log.i("aaki", "fb login success");
            Intent i = new Intent(FacebookLoginActivity.this, MainActivity.class);
            startActivity(i);
        }
        else{
            Log.i("aaki", "fb login error");
            Intent i = new Intent(FacebookLoginActivity.this, LandingActivity.class);
            startActivity(i);
        }
    }

}
