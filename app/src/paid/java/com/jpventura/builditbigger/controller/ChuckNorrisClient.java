package com.jpventura.builditbigger.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.appspot.build_it_bigger_1179.jokes.Jokes;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAuthIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.jpventura.builditbigger.Constants;
import com.jpventura.builditbigger.R;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class ChuckNorrisClient {
    private static ChuckNorrisClient sChuckNorrisClient;
    private static final Object lock = new Object();

    private Jokes mClient;

    public static synchronized void initialize(Activity activity, String email) {
        if (null == sChuckNorrisClient) {
            synchronized (lock) {
                sChuckNorrisClient = new ChuckNorrisClient(activity, email);
            }
        }
    }

    public static ChuckNorrisClient getInstance() throws IllegalStateException {
        if (null == sChuckNorrisClient) {
            throw new IllegalStateException("Chuck Norris Exception");
        }
        return sChuckNorrisClient;
    }

    public static final int REQUEST_CODE = 45;
    private WeakReference<Activity> mActivity;

    private ChuckNorrisClient(Activity activity, String email) {
        mActivity = new WeakReference<>(activity);
        GoogleAccountCredential credential = GoogleAccountCredential.usingAudience(activity, Constants.AUDIENCE);
        credential.setSelectedAccountName(email);
        Jokes.Builder builder = new Jokes.Builder(Constants.HTTP_TRANSPORT, Constants.JSON_FACTORY, null);
        builder.setApplicationName(activity.getString(R.string.app_name));
        mClient = builder.build();
    }

    public void onActivityResult(int resultCode,  Intent data) {
        if (Activity.RESULT_OK == resultCode) {
            Log.e("ventura", "deu " + data.toString());
        } else {
            Log.e("ventura", "nao deu");
        }
    }

    @WorkerThread
    public String getJoke() {
        try {
            return mClient.getJoke().execute().getJoke();
        } catch (UserRecoverableAuthIOException e) {
            mActivity.get().startActivityForResult(e.getIntent(), REQUEST_CODE);
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
