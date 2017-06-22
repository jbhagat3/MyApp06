package com.example.jaishri.newapps;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Jaishri on 6/21/2017.
 */

public class NetworkUtils
{
    private static final String TAG = NetworkUtils.class.getSimpleName();
    private static final String BASE_URL =
            "https://newsapi.org/v1/articles";
    final static String QUERY_PARAM = "source";
    final static String PARAM_SORT = "sortBy";
    final static String PARAM_API = "apiKey";

    public static URL buildUrl() {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM,"the-next-web")
                .appendQueryParameter(PARAM_SORT,"latest")
                //Enter your API KEY
                .appendQueryParameter(PARAM_API,"//Enter your API KEY")
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;

    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
