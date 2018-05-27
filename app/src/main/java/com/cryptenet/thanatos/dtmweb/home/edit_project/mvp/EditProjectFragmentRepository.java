/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.edit_project.mvp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.cryptenet.thanatos.dtmweb.borrowed.PostAsync;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.events.CategoriesReceiveEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.EditProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.AllCategoriesResponse;
import com.cryptenet.thanatos.dtmweb.pojo.AllPlansResponse;
import com.cryptenet.thanatos.dtmweb.pojo.Categories;
import com.cryptenet.thanatos.dtmweb.pojo.CountryResponse;
import com.cryptenet.thanatos.dtmweb.pojo.NewPlan;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@PerFragment
public class EditProjectFragmentRepository extends BaseFragRepository
        implements EditProjectFragmentContract.Repository {
    private static String TAG = TagProvider.getDebugTag(EditProjectFragmentRepository.class);

    @Override
    public void getAllCategories(Context context) {

//        String head = "application/json";
//
//        OkHttpClient client = new OkHttpClient();
//
//        final Request request = new Request.Builder()
//                .url(ConstantProvider.BASE_URL + "api/v1/country/")
//                .get()
//                .addHeader("Content-Type", head)
//                .build();

        retrofit2.Call<AllCategoriesResponse> req = apiClient.getCategories("Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN,null));
        req.enqueue(new retrofit2.Callback<AllCategoriesResponse>() {
            @Override
            public void onResponse(retrofit2.Call<AllCategoriesResponse> call, retrofit2.Response<AllCategoriesResponse> response) {
                try {
                    AllCategoriesResponse categoriesResponse = response.body();
                    Log.d(TAG, "onResponse: " + categoriesResponse.toString());
                    setCategories(categoriesResponse.getResults());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<AllCategoriesResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: AllPlansResponse");
            }
        });
    }

    @Override
    public void saveNewPlan(NewPlan plan, Context context) {
        PostAsync async = new PostAsync();

        async.execute(
                "3",
                plan.getTitle(),
                plan.getCategory(),
                plan.getShortDescription(),
                plan.getLongDescription(),
                plan.getMinimumInvestmentCost(),
                plan.getMaximumInvestmentCost(),
                plan.getAccessPrice(),
                plan.getCover(),
                plan.getUploadedFile(),
                PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN,null)
        );
    }

    private void setCategories(List<Categories> categoriesList) {
        EventBus.getDefault().post(new CategoriesReceiveEvent(categoriesList));
    }
}
