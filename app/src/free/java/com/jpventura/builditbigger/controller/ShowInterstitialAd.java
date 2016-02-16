/*
 * Copyright 2015 JP Ventura
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jpventura.builditbigger.controller;

import android.app.Activity;
import android.os.AsyncTask;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.jpventura.builditbigger.R;

public class ShowInterstitialAd extends AdListener {
    private InterstitialAd mInterstitialAd;

    public ShowInterstitialAd(Activity activity) {
        mInterstitialAd = new InterstitialAd(activity);
        mInterstitialAd.setAdUnitId(activity.getString(R.string.ad_unit_id_interstetial));
        mInterstitialAd.setAdListener(this);
    }

    public void onResume() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    public void execute(AsyncTask<Void, Integer, String> callback) {
        mInterstitialAd.show();
        callback.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
