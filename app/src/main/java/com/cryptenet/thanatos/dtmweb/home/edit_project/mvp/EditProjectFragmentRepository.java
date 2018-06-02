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
import com.cryptenet.thanatos.dtmweb.pojo.Categories;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRq;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

@PerFragment
public class EditProjectFragmentRepository extends BaseFragRepository
        implements EditProjectFragmentContract.Repository {
    private static String TAG = TagProvider.getDebugTag(EditProjectFragmentRepository.class);

    @Override
    public void getAllCategories(Context context) {
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
    public void saveUpdatePlan(ProjectsRq plan, Context context, int id) {
        if (plan.isNew()) {
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
                    PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN, null)
            );

//            async.execute(
//                    "3",
//                    "ekta project",
//                    65,
//                    "short description",
//                    "long description",
//                    11223,
//                    112233,
//                    7896,
//                    plan.getCover(),
//                    plan.getUploadedFile(),
//                    "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN, null)
//            );
        } else {
            PostAsync async = new PostAsync();
            async.execute(
                    "4",
                    plan.getTitle(),
                    plan.getCategory(),
                    plan.getShortDescription(),
                    plan.getLongDescription(),
                    plan.getMinimumInvestmentCost(),
                    plan.getMaximumInvestmentCost(),
                    plan.getAccessPrice(),
                    plan.getCover(),
                    plan.getUploadedFile(),
                    PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN, null),
                    id
            );
        }
    }

    private void setCategories(List<Categories> categoriesList) {
        EventBus.getDefault().post(new CategoriesReceiveEvent(categoriesList));
    }
}
