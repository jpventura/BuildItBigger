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

import android.content.Context;
import android.support.annotation.WorkerThread;

import com.appspot.build_it_bigger_1179.jokes.Jokes;
import com.jpventura.builditbigger.Constants;
import com.jpventura.builditbigger.R;

import java.io.IOException;

public class ChuckNorrisClient {
    public static final int REQUEST_CODE = 45;

    private static ChuckNorrisClient sChuckNorrisClient;
    private static final Object lock = new Object();

    private Jokes mClient;

    public static ChuckNorrisClient getInstance(Context context) throws IllegalStateException {
        if (null == sChuckNorrisClient) {
            synchronized (lock) {
                sChuckNorrisClient = new ChuckNorrisClient(context);
            }
        }
        return sChuckNorrisClient;
    }

    private ChuckNorrisClient(Context context) {
        Jokes.Builder builder = new Jokes.Builder(Constants.HTTP_TRANSPORT, Constants.JSON_FACTORY, null);
        builder.setApplicationName(context.getString(R.string.app_name));
        mClient = builder.build();
    }

    @WorkerThread
    public String getJoke() {
        try {
            return mClient.getJoke().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
