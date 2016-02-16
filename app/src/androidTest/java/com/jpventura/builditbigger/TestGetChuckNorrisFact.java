package com.jpventura.builditbigger;

import android.os.AsyncTask;
import android.test.AndroidTestCase;

import com.jpventura.builditbigger.command.GetChuckNorrisFact;

public class TestGetChuckNorrisFact extends AndroidTestCase {
    public void testDoInBackGround() {
        GetChuckNorrisFact command = new GetChuckNorrisFact(getContext()) {
            @Override
            protected void onPostExecute(String fact) {
                assertNotNull(fact);
            }
        };
        command.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
