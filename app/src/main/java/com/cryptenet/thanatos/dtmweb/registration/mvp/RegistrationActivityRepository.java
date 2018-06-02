/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.registration.mvp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.cryptenet.thanatos.dtmweb.borrowed.PostAsync;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.events.CityFetchEvent;
import com.cryptenet.thanatos.dtmweb.events.CountryFetchEvent;
import com.cryptenet.thanatos.dtmweb.events.RegistrationFailureEvent;
import com.cryptenet.thanatos.dtmweb.events.RegistrationSuccessEvent;
import com.cryptenet.thanatos.dtmweb.http.ApiClient;
import com.cryptenet.thanatos.dtmweb.http.RetrofitServiceFactory;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.RegistrationActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.City;
import com.cryptenet.thanatos.dtmweb.pojo.CityResponse;
import com.cryptenet.thanatos.dtmweb.pojo.Country;
import com.cryptenet.thanatos.dtmweb.pojo.CountryResponse;
import com.cryptenet.thanatos.dtmweb.pojo.RegistrationInput;
import com.cryptenet.thanatos.dtmweb.pojo.RegistrationResponse;
import com.cryptenet.thanatos.dtmweb.pojo.UpdateProfileInput;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@PerActivity
public class RegistrationActivityRepository extends BaseRepository
        implements RegistrationActivityContract.Repository {
    private static String TAG = TagProvider.getDebugTag(RegistrationActivityRepository.class);
    private List<Country> countries;
    private List<City> cities;

    private ApiClient mApiClient = RetrofitServiceFactory.createRetrofitService();

    public RegistrationActivityRepository() {
        countries = new ArrayList<>();
        cities = new ArrayList<>();
    }

    @Override
    public void getAllCountries() {

        //mApiClient = RetrofitServiceFactory.createRetrofitService();
        mApiClient.getAllcountries().enqueue(new retrofit2.Callback<CountryResponse>() {
            @Override
            public void onResponse(retrofit2.Call<CountryResponse> call, retrofit2.Response<CountryResponse> response) {
                try {
                    Log.d(TAG, "onResponse: " + response.body().getResults());
                    setCountries(response.body().getResults());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<CountryResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: country");
            }
        });
    }

    @Override
    public void getLimitedCities(int countryCode) {

        mApiClient.getAllCities(countryCode).enqueue(new retrofit2.Callback<CityResponse>() {
            @Override
            public void onResponse(retrofit2.Call<CityResponse> call, retrofit2.Response<CityResponse> response) {

                try {
                    Log.d(TAG, "onResponse: " + response.body().getResults());
                    setCities(response.body().getResults());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(retrofit2.Call<CityResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: city");
            }
        });
    }

    @Override
    public boolean attemptReg(String reqType, final RegistrationInput regData) {
//        PostAsync async = new PostAsync();
//
//        async.execute(
//                reqType,
//                regData.getName(),
//                regData.getEmail(),
//                regData.getPassword(),
//                regData.getPicture(),
//                regData.getAddress(),
//                regData.getCountry(),
//                regData.getCity(),
//                regData.getBankName(),
//                regData.getBankAccountName(),
//                regData.getBankAccountNumber(),
//                regData.getUserType()
//        );

        registerNewUserProfile(reqType, regData);


        return false;
    }

    @Override
    public boolean attemptUpdateProfile(String reqType, UpdateProfileInput regData) {
        return false;
    }

    @Override
    public boolean checkLoginState(Context context) {
        return (PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN, null)) != null;
    }

    private void setCountries(List<Country> countries) {
        this.countries = countries;
        EventBus.getDefault().post(new CountryFetchEvent(this.countries));
    }

    private void setCities(List<City> cities) {
        this.cities = cities;
        EventBus.getDefault().post(new CityFetchEvent(this.cities));
    }

    //calling method to register new profile
    private void registerNewUserProfile(String reqType, final RegistrationInput regData) {
        //mProgressBarHandler.showProgress();

        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), regData.getName());
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), regData.getEmail());
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), regData.getPassword());

        RequestBody filePicture;
        MultipartBody.Part bodyPicture = null;
        filePicture = RequestBody.create(MediaType.parse("image/*"), regData.getPicture());
        bodyPicture = MultipartBody.Part.createFormData("picture", regData.getPicture().getName(), filePicture);


        RequestBody city = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(regData.getCity()));
        RequestBody country = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(regData.getCountry()));
        RequestBody bank_name = RequestBody.create(MediaType.parse("text/plain"), regData.getBankName());
        RequestBody bank_account_name = RequestBody.create(MediaType.parse("text/plain"), regData.getBankAccountName());
        RequestBody bank_account_number = RequestBody.create(MediaType.parse("text/plain"), regData.getBankAccountNumber());
        RequestBody user_type = RequestBody.create(MediaType.parse("text/plain"), regData.getUserType());
        RequestBody address = RequestBody.create(MediaType.parse("text/plain"), regData.getAddress());

        mApiClient.createNewUser(name, email, password, bodyPicture, city,
                country, bank_name, bank_account_name, bank_account_number, user_type, address)
                .enqueue(new Callback<RegistrationResponse>() {
                    @Override
                    public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                        if (response.isSuccessful()){
                            Log.d(TAG, "reg response: " + response.body().toString());
                            EventBus.getDefault().post(new RegistrationSuccessEvent(response.body()));
                        }else {
                            EventBus.getDefault().post(new RegistrationFailureEvent(true));
                        }

                    }

                    @Override
                    public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                        t.printStackTrace();
                        EventBus.getDefault().post(new RegistrationFailureEvent(true));
                    }
                });

    }
}