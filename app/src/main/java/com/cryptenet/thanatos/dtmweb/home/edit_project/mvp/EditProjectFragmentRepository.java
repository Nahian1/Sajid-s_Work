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
import com.cryptenet.thanatos.dtmweb.events.EditPlanSuccessEvent;
import com.cryptenet.thanatos.dtmweb.http.ApiClient;
import com.cryptenet.thanatos.dtmweb.http.RetrofitServiceFactory;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.EditProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.AllCategoriesResponse;
import com.cryptenet.thanatos.dtmweb.pojo.Categories;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRq;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;
import com.cryptenet.thanatos.dtmweb.utils.ProgressBarHandler;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@PerFragment
public class EditProjectFragmentRepository extends BaseFragRepository
        implements EditProjectFragmentContract.Repository {
    private static String TAG = TagProvider.getDebugTag(EditProjectFragmentRepository.class);

    private ApiClient mApiClient = RetrofitServiceFactory.createRetrofitService();


    @Override
    public void getAllCategories(Context context) {
        retrofit2.Call<AllCategoriesResponse> req = apiClient.getCategories("Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN, null));
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

            String access_token = "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN, null);

            String title = plan.getTitle();
            String category = String.valueOf(plan.getCategory());
            String shortDesc = plan.getShortDescription();
            String longDesc = plan.getLongDescription();
            String minInvestCost = String.valueOf(plan.getMinimumInvestmentCost());
            String maxInvestCost = String.valueOf(plan.getMaximumInvestmentCost());
            String accessPrice = String.valueOf(plan.getAccessPrice());


            RequestBody coverPictureFile;
            MultipartBody.Part coverPictureBody = null;

            coverPictureFile = RequestBody.create(MediaType.parse("image/*"), plan.getCover());
            coverPictureBody = MultipartBody.Part.createFormData("cover", plan.getCover().getName(), coverPictureFile);

            RequestBody uploadedFile;
            MultipartBody.Part uploadedFileBody = null;

            uploadedFile = RequestBody.create(MediaType.parse("application/*"), plan.getUploadedFile());
            uploadedFileBody = MultipartBody.Part.createFormData("uploaded_file", plan.getUploadedFile().getName(), uploadedFile);


            mApiClient.crerateNewInitiatorPlan(access_token, title, category, shortDesc, longDesc,
                    minInvestCost, maxInvestCost, accessPrice, coverPictureBody, uploadedFileBody)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ProjectsRsp>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(ProjectsRsp projectsRsp) {
//                            Log.d(TAG, "add plan rsp: " + projectsRsp.toString());

                            EventBus.getDefault().post(new EditPlanSuccessEvent(projectsRsp));

                        }
                    });


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
