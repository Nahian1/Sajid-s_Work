/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.plan_list.mvp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.events.ProjectListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.events.RequestFailureEvent;
import com.cryptenet.thanatos.dtmweb.events.TokenRefreshEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanListFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.AllPlansResponse;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;
import com.cryptenet.thanatos.dtmweb.pojo.User;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@PerFragment
public class PlanListFragmentRepository extends BaseFragRepository
        implements PlanListFragmentContract.Repository {
//    private static String TAG = TagProvider.getDebugTag(PlanListFragmentRepository.class);
    private List<ProjectsRsp> projectsRspList;

    public PlanListFragmentRepository() {
        this.projectsRspList = new ArrayList<>();
    }

    @Override
    public void getAllProjects(Context context, int offset) {
        if (PreferenceManager.getDefaultSharedPreferences(context).getLong(ConstantProvider.SP_EXPIRES_IN, 0) <= System.currentTimeMillis()) {
            String head = "application/x-www-form-urlencoded";

            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("grant_type", "refresh_token")
                    .add("client_id", PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.CLIENT_KEY, ""))
                    .add("client_secret", PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.CLIENT_SECRET, ""))
                    .add("refresh_token", PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_REFRESH_TOKEN, ""))
                    .build();

            final Request request = new Request.Builder()
                    .url(ConstantProvider.BASE_URL + "o/token/")
                    .post(formBody)
                    .addHeader("Content-Type", head)
                    .build();

            client.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {
//                    Log.d(TAG, "onFailure: " + "failed");
                    EventBus.getDefault().post(new RequestFailureEvent(true));
                }

                @Override
                public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                    if (response.isSuccessful()) {
                        Gson gson = new Gson();
                        User user = gson.fromJson(response.body().string(), User.class);
                        saveUserToSP(user, context);
                        EventBus.getDefault().post(new TokenRefreshEvent());
                    } else {
                        Log.d("login failed", response.body().toString());
                        EventBus.getDefault().post(new RequestFailureEvent(true));
                    }
                }
            });
        } else {
            Call<AllPlansResponse> req = apiClient.getAllPlans(
                    "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN, null),
                    50,
                    offset
            );
            req.enqueue(new Callback<AllPlansResponse>() {
                @Override
                public void onResponse(Call<AllPlansResponse> call, retrofit2.Response<AllPlansResponse> response) {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        setProjects(response.body().getResults());

                        if (response.body().getResults().size() == 0 && offset == 0)
                            Toast.makeText(context, context.getString(R.string.no_all_plans), Toast.LENGTH_LONG).show();
                        else if (response.body().getResults().size() == 0 && offset > 0)
                            Toast.makeText(context, context.getString(R.string.no_more_plans), Toast.LENGTH_LONG).show();
                    } else {
//                        Log.d(TAG, "onResponse: " + response.code());
                        EventBus.getDefault().post(new RequestFailureEvent(true));
                    }
                }

                @Override
                public void onFailure(Call<AllPlansResponse> call, Throwable t) {
                    EventBus.getDefault().post(new RequestFailureEvent(true));
                }
            });
        }
    }

    @Override
    public void searchMyPlans(Context context, String token, String searchTerm) {

        Call<AllPlansResponse> req = apiClient.getAllMyPlansSearch("Bearer " + token, searchTerm);

        req.enqueue(new Callback<AllPlansResponse>() {
            @Override
            public void onResponse(Call<AllPlansResponse> call, Response<AllPlansResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    EventBus.getDefault().post(new ProjectListReceiveEvent(response.body().getResults(), true));
                    if (response.body().getResults().size() == 0)
                        Toast.makeText(context, context.getString(R.string.no_search_result), Toast.LENGTH_LONG).show();
                }
//                } else {
//                    Log.d(TAG, "onResponse: " + response.code());
//                }
            }

            @Override
            public void onFailure(Call<AllPlansResponse> call, Throwable t) {
//                Log.d(TAG, "onFailure: " + "search failed");
            }
        });

    }

    @Override
    public int checkUserType(Context context) {
        String user = PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_USER_TYPE, null);

        assert user != null;
        if (user.equals("Initiator"))
            return 2;
        else
            return 1;
    }

    private void setProjects(List<ProjectsRsp> projectsRspList) {
        this.projectsRspList = projectsRspList;
//        for (ProjectsRsp projectsRsp : projectsRspList)
//            Log.d(TAG, "setProjects: " + projectsRsp.getTitle());
        EventBus.getDefault().post(new ProjectListReceiveEvent(this.projectsRspList, false));
    }

    public boolean saveUserToSP(User user, Context context) {
        long expiresIn = System.currentTimeMillis() + (user.getExpiresIn() * 1000);

        return PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(ConstantProvider.SP_ACCESS_TOKEN, user.getAccessToken())
                .putString(ConstantProvider.SP_REFRESH_TOKEN, user.getRefreshToken())
                .putInt(ConstantProvider.SP_ID, user.getId())
                .putString(ConstantProvider.SP_NAME, user.getName())
                .putString(ConstantProvider.SP_EMAIL, user.getEmail())
                .putString(ConstantProvider.SP_PICTURE_URL, user.getPicture())
                .putString(ConstantProvider.SP_ADDRESS, user.getAddress())
                .putInt(ConstantProvider.SP_CITY, user.getCity())
                .putInt(ConstantProvider.SP_COUNTRY, user.getCountry())
                .putString(ConstantProvider.SP_BANK_NAME, user.getBankName())
                .putString(ConstantProvider.SP_BANK_ACC_NAME, user.getBankAccountName())
                .putString(ConstantProvider.SP_BANK_ACC_NO, user.getBankAccountNumber())
                .putString(ConstantProvider.SP_USER_TYPE, user.getUserType())
                .putLong(ConstantProvider.SP_EXPIRES_IN, expiresIn)
                .commit();
    }
}
