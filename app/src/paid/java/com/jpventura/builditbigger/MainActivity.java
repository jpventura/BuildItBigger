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
package com.jpventura.builditbigger;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.jpventura.builditbigger.command.GetChuckNorrisFact;
import com.jpventura.chucknorris.ChuckNorrisActivity;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String PROGRESS_BAR_VISIBLE = "com.jpventura.builditbigger.progress_bar";

    private static ProgressDialog sProgressDialog;
    private static String sChuckNorrisFact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_get_fact).setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        onSaveProgressDialog(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onRestoreProgressBarState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        dismissProgressDialog();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
		getChuckNorrisFact();
    }

    private void onSaveProgressDialog(Bundle state) {
        boolean isShowing = (null != sProgressDialog) && sProgressDialog.isShowing();
        state.putBoolean(PROGRESS_BAR_VISIBLE, isShowing);
    }

    private void onRestoreProgressBarState(Bundle state) {
        if (state.getBoolean(PROGRESS_BAR_VISIBLE, false)) {
            showProgressDialog();
        }
    }

    private void showProgressDialog() {
        if (null == sProgressDialog) {
            sProgressDialog = new ProgressDialog(this);
            sProgressDialog.setCancelable(false);
            sProgressDialog.setTitle(getString(R.string.dialog_title));
            sProgressDialog.setMessage(getString(R.string.dialog_message));
        }
        sProgressDialog.show();
    }

    private void dismissProgressDialog() {
        if (null != sProgressDialog) {
            sProgressDialog.dismiss();
            sProgressDialog = null;
        }
    }

    private void setChuckNorrisFact(String fact) {
        sChuckNorrisFact = fact;
        Intent intent = new Intent(this, ChuckNorrisActivity.class);
        intent.putExtra("fact", fact);
        startActivity(intent);
    }

    private void getChuckNorrisFact() {
        GetChuckNorrisFact command = new GetChuckNorrisFact(this) {
            @Override
            protected void onPreExecute() {
                showProgressDialog();
            }

            @Override
            protected void onPostExecute(String fact) {
                dismissProgressDialog();
                setChuckNorrisFact(fact);
            }
        };

        command.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
