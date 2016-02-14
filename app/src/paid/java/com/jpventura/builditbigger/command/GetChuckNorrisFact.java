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
package com.jpventura.builditbigger.command;

import android.content.Context;
import android.os.AsyncTask;

import com.jpventura.builditbigger.controller.ChuckNorrisClient;

import java.lang.ref.WeakReference;

public abstract class GetChuckNorrisFact extends AsyncTask<Void, Integer, String> {
    private WeakReference<Context> mContext;

    public GetChuckNorrisFact(Context context) {
        mContext = new WeakReference<>(context);
    }

    @Override
    protected String doInBackground(Void... params) {
        ChuckNorrisClient client = ChuckNorrisClient.getInstance();
        return client.getJoke();
    }
}
