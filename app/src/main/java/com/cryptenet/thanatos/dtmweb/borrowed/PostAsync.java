package com.cryptenet.thanatos.dtmweb.borrowed;

import android.os.AsyncTask;
import android.util.Log;

import com.cryptenet.thanatos.dtmweb.events.RegistrationSuccessEvent;
import com.cryptenet.thanatos.dtmweb.pojo.RegistrationResponse;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

public class PostAsync extends AsyncTask<Object, Void, String> {
    public static final String TAG = TagProvider.getDebugTag(PostAsync.class);

    @Override
    protected String doInBackground(Object[] objects) {
        ApiUtil util;
        String response = null;

        if (objects[0].equals("1")) {
            //registration
            util = new ApiUtil("https://fa-sa-801-dev.herokuapp.com/");
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
            Log.d(TAG, "doInBackground: " + response);
            Gson gson = new Gson();
            RegistrationResponse registrationResponse = gson.fromJson(response, RegistrationResponse.class);
            EventBus.getDefault().post(new RegistrationSuccessEvent(registrationResponse));
        } else if (objects[0].equals("3")) {
            util = new ApiUtil("https://fa-sa-801-dev.herokuapp.com/");

            response = util.addPlan(
                    (String) objects[1],
                    (int) objects[2],
                    (String) objects[3],
                    (String) objects[4],
                    (int) objects[5],
                    (int) objects[6],
                    (int) objects[7],
                    (File) objects[8],
                    (File) objects[9],
                    (String) objects[10]
            );
        } else if (objects[0].equals("4")) {
            util = new ApiUtil("https://fa-sa-801-dev.herokuapp.com/");

            response = util.editPlan(
                    (String) objects[1],
                    (int) objects[2],
                    (String) objects[3],
                    (String) objects[4],
                    (int) objects[5],
                    (int) objects[6],
                    (int) objects[7],
                    (File) objects[8],
                    (File) objects[9],
                    (String) objects[10],
                    (int) objects[11]
            );
        }

        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, "onPostExecute: " + s);
    }
}
