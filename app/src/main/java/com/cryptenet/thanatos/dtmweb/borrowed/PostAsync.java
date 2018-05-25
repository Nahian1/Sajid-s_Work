package com.cryptenet.thanatos.dtmweb.borrowed;

import android.os.AsyncTask;
import android.util.Log;

import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import java.io.File;

public class PostAsync extends AsyncTask<Object, Void, String> {
    public static final String TAG = TagProvider.getDebugTag(PostAsync.class);

    @Override
    protected String doInBackground(Object[] objects) {
        ApiUtil util;
        String response = null;

        if (objects[0].equals("1")) {
            //registration
            util = new ApiUtil("https://fa-sa-801.herokuapp.com/api/v1/");
            response = util.createUser(
                        (String) objects[1],
                        (String) objects[2],
                        (String) objects[3],
                        (File) objects[4],
                        (String) objects[5],
                        (String) objects[6],
                        (String) objects[7],
                        (String) objects[8],
                        (String) objects[9],
                        (String) objects[10],
                        (String) objects[11]
            );
        } else if (objects[0].equals("2")) {
            util = new ApiUtil("https://fa-sa-801.herokuapp.com/");
            response = util.tryLogin(
                    (String) objects[1],
                    (String) objects[2],
                    (String) objects[3],
                    (String) objects[4]
            );
        }

        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, "onPostExecute: " + s);
    }
}
