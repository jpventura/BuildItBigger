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

import org.junit.After;
import org.junit.Before;

import retrofit.RetrofitError;

import static org.junit.Assert.*;

public class TestChuckNorrisDatabase {
    private static final String ERROR_NO_JOKE = "No joke text from ICNDb.com";
    private static final String ERROR_NO_RESPONSE = "No response from ICNDb.com";
    private static final String ERROR_NO_VALUE = "No joke value from ICNDb.com";
    private static final String ERROR_RETROFIT = "Server connection failed";

    private IChuckNorrisDatabase mChuckNorrisDatabase;

    @Before
    public void setUp() {
        mChuckNorrisDatabase = ChuckNorrisDatabase.getInstance();
    }

    @org.junit.Test
    public void testGetRandomJoke() throws Exception {
        try {
            ResponsePage<Joke> page = mChuckNorrisDatabase.getRandomJoke();
            assertNotNull(ERROR_NO_RESPONSE, page);
            assertNotNull(ERROR_NO_VALUE, page.value);
            assertNotNull(ERROR_NO_JOKE, page.value.joke);
        } catch (RetrofitError e) {
            fail(ERROR_RETROFIT);
        }
    }

    @After
    public void tearDown() {
        mChuckNorrisDatabase = null;
    }
}
