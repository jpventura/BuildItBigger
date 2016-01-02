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
package com.jpventura;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.RetrofitError;

public class ChuckNorrisDatabase implements IChuckNorrisDatabase {
    private static IChuckNorrisDatabase sChuckNorrisDatabase;
    private static final Object sLock = new Object();

    private IChuckNorrisDatabase mChuckNorrisDatabase;

    public synchronized static IChuckNorrisDatabase getInstance() {
        synchronized (sLock) {
            if (null == sChuckNorrisDatabase) {
                sChuckNorrisDatabase = new ChuckNorrisDatabase();
            }
        }
        return sChuckNorrisDatabase;
    }

    public ChuckNorrisDatabase() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(IChuckNorrisDatabase.BASE_URL)
                .setLogLevel(LogLevel.NONE)
                .build();
        mChuckNorrisDatabase = restAdapter.create(IChuckNorrisDatabase.class);
    }

    @Override
    public ResponsePage<Joke> getRandomJoke() throws RetrofitError {
        return mChuckNorrisDatabase.getRandomJoke();
    }
}
