package com.cryptenet.thanatos.dtmweb.borrowed;

import android.os.AsyncTask;
import android.util.Log;

import com.cryptenet.thanatos.dtmweb.events.EditPlanSuccessEvent;
import com.cryptenet.thanatos.dtmweb.events.RegistrationSuccessEvent;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;
import com.cryptenet.thanatos.dtmweb.pojo.RegistrationResponse;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

public class PostAsync extends AsyncTask<Object, Void, String> {
    public static final String TAG = TagProvider.getDebugTag(PostAsync.class);

    @Override
    protected String doInBackground(Object[] objects) {
        ApiUtil util = new ApiUtil("https://fa-sa-801-dev.herokuapp.com/");
        String response = null;

        if (objects[0].equals("1")) {
            //registration

            response = util.createUser(
                    (String) objects[1],    //name
                    (String) objects[2],    //email
                    (String) objects[3],    //password
                    (File) objects[4],      //picture (file)
                    (String) objects[5],    //address
                    (String) objects[6],    //country
                    (String) objects[7],    //city
                    (String) objects[8],    //bank name
                    (String) objects[9],    //bank account name
                    (String) objects[10],   //bank account number
                    (String) objects[11]    //user type
            );
            Log.d(TAG, "doInBackground: " + response);
            Gson gson = new Gson();
            RegistrationResponse registrationResponse = gson.fromJson(response, RegistrationResponse.class);
            EventBus.getDefault().post(new RegistrationSuccessEvent(registrationResponse));

        } else if (objects[0].equals("2")) {
            //edit or update user

            response = util.editUser(
                    (String) objects[1],    //user id
                    (String) objects[2],    //name
                    (String) objects[3],    //email
                    (File) objects[4],      //picture (file)
                    (String) objects[5],    //user type
                    (String) objects[6],    //password
                    (String) objects[7],    //address
                    (String) objects[8],    //country
                    (String) objects[9],    //city
                    (String) objects[10],   //bank name
                    (String) objects[11],   //bank account name
                    (String) objects[12],   //bank account number
                    (String) objects[13]    //accessToken = "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN, null);
            );

            Gson gson = new Gson();
            RegistrationResponse registrationResponse = gson.fromJson(response, RegistrationResponse.class);
            EventBus.getDefault().post(new RegistrationSuccessEvent(registrationResponse));

        } else if (objects[0].equals("3")) {
            //add plan

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

            Gson gson = new Gson();
            ProjectsRsp projectsRsp = gson.fromJson(response, ProjectsRsp.class);
            EventBus.getDefault().post(new EditPlanSuccessEvent(projectsRsp));

        } else if (objects[0].equals("4")) {
            //edit or update plan

            response = util.editPlan(
                    (String) objects[1],    //title
                    (int) objects[2],       //category
                    (String) objects[3],    //shortDesc
                    (String) objects[4],    //longDesc
                    (int) objects[5],       //min
                    (int) objects[6],       //max
                    (int) objects[7],       //access
                    (File) objects[8],      //cover (file)
                    (File) objects[9],      //uploadFile (file)
                    (String) objects[10],   //accessToken = "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN, null);
                    (int) objects[11]       // plan id
            );

            Gson gson = new Gson();
            ProjectsRsp projectsRsp = gson.fromJson(response, ProjectsRsp.class);
            EventBus.getDefault().post(new EditPlanSuccessEvent(projectsRsp));

        }
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, "onPostExecute: " + s);
    }
}
