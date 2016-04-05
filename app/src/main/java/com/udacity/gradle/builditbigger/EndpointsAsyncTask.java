package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;

import com.example.alexandra.androidlibrary.AndroidLibActivity;
import com.example.alexandra.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected final String doInBackground(Pair<Context, String>... params) {
        if (myApiService == null) {
            MyApi.Builder builder =
                    new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                                      new AndroidJsonFactory(), null)
                            .setRootUrl("https://mybackend-1272.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        context = params[0].first;

        try {
            return myApiService.pullJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(context, AndroidLibActivity.class);
        intent.putExtra(AndroidLibActivity.JOKE_KEY, result);
        context.startActivity(intent);
    }
}