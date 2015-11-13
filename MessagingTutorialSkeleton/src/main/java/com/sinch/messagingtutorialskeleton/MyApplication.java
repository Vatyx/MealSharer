package com.sinch.messagingtutorialskeleton;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by himankyadav on 11/13/15.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.


        // Parse.initialize(this, "app-id", "client-key");
    }
}