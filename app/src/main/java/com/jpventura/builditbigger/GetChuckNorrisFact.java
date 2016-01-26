package com.jpventura.builditbigger;

import android.os.AsyncTask;

import com.appspot.build_it_bigger_1179.jokes.Jokes;
import com.appspot.build_it_bigger_1179.jokes.Jokes.GetJoke;
import com.appspot.build_it_bigger_1179.jokes.JokesRequestInitializer;

public class GetChuckNorrisFact extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... params) {
        Jokes jokes = new GetJoke();
        return null;
    }
}
