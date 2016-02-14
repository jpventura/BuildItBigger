package com.jpventura.builditbigger.controller;

import android.app.Activity;
import android.os.AsyncTask;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.jpventura.builditbigger.R;

public class ShowInterstitialAd extends AdListener {
    private InterstitialAd mInterstitialAd;

    public ShowInterstitialAd(Activity activity) {
        mInterstitialAd = new InterstitialAd(activity);
        mInterstitialAd.setAdUnitId(activity.getString(R.string.ad_unit_id_interstetial));
        mInterstitialAd.setAdListener(this);
    }

    public void execute(AsyncTask callback) {
        mInterstitialAd.show();
        callback.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
