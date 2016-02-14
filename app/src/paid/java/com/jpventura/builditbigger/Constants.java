package com.jpventura.builditbigger;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;

public class Constants {
    public static final String ANDROID_CLIENT_ID = "280863524374-293ph7d5ruem5493kkithp9e0sjh0vld.apps.googleusercontent.com";
    public static final String WEB_CLIENT_ID = "280863524374-2h5fheidg07o7ctb4c2o7i1qfpmko1ft.apps.googleusercontent.com";
    public static final String AUDIENCE = "server:client_id:" + WEB_CLIENT_ID;
    public static final JsonFactory JSON_FACTORY = new AndroidJsonFactory();
    public static final HttpTransport HTTP_TRANSPORT = AndroidHttp.newCompatibleTransport();
}
