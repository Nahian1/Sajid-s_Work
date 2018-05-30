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
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.RegistrationActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.City;
import com.cryptenet.thanatos.dtmweb.pojo.CityResponse;
import com.cryptenet.thanatos.dtmweb.pojo.Country;
import com.cryptenet.thanatos.dtmweb.pojo.CountryResponse;
import com.cryptenet.thanatos.dtmweb.pojo.RegistrationInput;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@PerActivity
public class RegistrationActivityRepository extends BaseRepository
        implements RegistrationActivityContract.Repository {
    private static String TAG = TagProvider.getDebugTag(RegistrationActivityRepository.class);
    private List<Country> countries;
    private List<City> cities;

    public RegistrationActivityRepository() {
        countries = new ArrayList<>();
        cities = new ArrayList<>();
    }

    @Override
    public void getAllCountries() {
        String head = "application/json";

        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(ConstantProvider.BASE_URL + "api/v1/country/")
                .get()
                .addHeader("Content-Type", head)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: country");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    Gson gson = new Gson();
                    CountryResponse countryResponse = gson.fromJson(response.body().string(), CountryResponse.class);
                    Log.d(TAG, "onResponse: " + countryResponse.toString());
                    setCountries(countryResponse.getResults());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getLimitedCities(int countryCode) {
        String head = "application/json";

        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(ConstantProvider.BASE_URL + "api/v1/city/?country=" + countryCode)
                .get()
                .addHeader("Content-Type", head)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: city");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    Gson gson = new Gson();
                    CityResponse cityResponse = gson.fromJson(response.body().string(), CityResponse.class);
                    Log.d(TAG, "onResponse: " + cityResponse.toString());
                    setCities(cityResponse.getResults());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean attemptReg(final RegistrationInput regData) {
        PostAsync async = new PostAsync();

        async.execute(
                "1",
                regData.getName(),
                regData.getEmail(),
                regData.getPassword(),
                regData.getPicture(),
                regData.getAddress(),
                regData.getCountry().toString(),
                regData.getCity().toString(),
                regData.getBankName(),
                regData.getBankAccountName(),
                regData.getBankAccountNumber(),
                regData.getUserType()
        );

        return false;
    }

    @Override
    public boolean checkLoginState(Context context) {
        return (PreferenceManager
                        .getDefaultSharedPreferences(context)
                        .getString(ConstantProvider.SP_ACCESS_TOKEN, null)) != null;
    }

    private void setCountries(List<Country> countries) {
        this.countries = countries;
        EventBus.getDefault().post(new CountryFetchEvent(this.countries));
    }

    private void setCities(List<City> cities) {
        this.cities = cities;
        EventBus.getDefault().post(new CityFetchEvent(this.cities));
    }
}