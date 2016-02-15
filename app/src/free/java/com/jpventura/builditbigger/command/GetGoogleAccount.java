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

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.AccountPicker;
import com.jpventura.builditbigger.R;

import java.lang.ref.WeakReference;

public class GetGoogleAccount {
    public interface OnGetGoogleAccountListener {
        void onGetGoogleAccount(String account);
    }

    public static final int REQUEST_CODE = 2222;
    private final static String PREFS_KEY_EMAIL = "email_account";

    private WeakReference<Activity> mActivity;
    private SharedPreferences mSharedPreferences;
    private OnGetGoogleAccountListener mOnGetGoogleAccountListener;

    public GetGoogleAccount(Activity activity) {
        mActivity = new WeakReference<>(activity);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    public void onActivityResult(int resultCode, Intent data) {
        if (Activity.RESULT_OK == resultCode) {
            setGoogleAccount(data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME));
        }
    }

    public void setOnGetGoogleAccountListener(OnGetGoogleAccountListener onGetGoogleAccountListener) {
        mOnGetGoogleAccountListener = onGetGoogleAccountListener;
    }

    public void execute() {
        String googleAccount = getGoogleAccount();
        if ((null != googleAccount) && (null != mOnGetGoogleAccountListener)) {
            mOnGetGoogleAccountListener.onGetGoogleAccount(googleAccount);
            return;
        }

        Account[] accounts = getAccountsByType();
        if (1 == accounts.length) {
            setGoogleAccount(accounts[0].name);
            return;
        }

        if (accounts.length > 1) {
            showAccountPicker();
        }
    }

    private Account[] getAccountsByType() {
        AccountManager am = AccountManager.get(mActivity.get());
        return am.getAccountsByType(GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
    }

    private String getGoogleAccount() {
        return mSharedPreferences.getString(PREFS_KEY_EMAIL, null);
    }

    private void setGoogleAccount(String email) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(PREFS_KEY_EMAIL, email);
        editor.apply();

        if (null != mOnGetGoogleAccountListener) {
            mOnGetGoogleAccountListener.onGetGoogleAccount(email);
        }
    }

    private void showAccountPicker() {
        Intent accountSelector = AccountPicker.newChooseAccountIntent(
                null,
                null,
                new String[] { GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE },
                false,
                mActivity.get().getString(R.string.dialog_choose_account),
                null,
                null,
                null
        );
        mActivity.get().startActivityForResult(accountSelector, REQUEST_CODE);
    }
}
