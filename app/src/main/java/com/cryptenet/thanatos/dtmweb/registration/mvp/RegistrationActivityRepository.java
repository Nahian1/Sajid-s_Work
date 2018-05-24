/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.registration.mvp;

import android.util.Log;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.events.CityFetchEvent;
import com.cryptenet.thanatos.dtmweb.events.CountryFetchEvent;
import com.cryptenet.thanatos.dtmweb.http.ApiClient;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.RegistrationActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.City;
import com.cryptenet.thanatos.dtmweb.pojo.CityResponse;
import com.cryptenet.thanatos.dtmweb.pojo.Country;
import com.cryptenet.thanatos.dtmweb.pojo.CountryResponse;
import com.cryptenet.thanatos.dtmweb.pojo.RegistrationInput;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@PerActivity
public class RegistrationActivityRepository extends BaseRepository
        implements RegistrationActivityContract.Repository {
    private static String TAG = TagProvider.getDebugTag(RegistrationActivityRepository.class);
    private ApiClient client;
    private List<Country> countries;
    private List<City> cities;

    public RegistrationActivityRepository() {
        countries = new ArrayList<>();
        cities = new ArrayList<>();
        
        if (client == null) {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(ConstantProvider.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            client = retrofit.create(ApiClient.class);
        }
    }

    @Override
    public void getAllCountries() {
        Call<CountryResponse> countryResponseCall = client.getCountries();

        countryResponseCall.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                CountryResponse countryResponse = response.body();
                setCountries(countryResponse.getResults());
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    @Override
    public void getLimitedCities(int countryCode) {
        Call<CityResponse> cityResponseCall = client.getCities(countryCode);
        
        cityResponseCall.enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                CityResponse cityResponse = response.body();
                setCities(cityResponse.getResults());
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    @Override
    public boolean attemptReg(final RegistrationInput regData) {

        retrofit2.Call<okhttp3.ResponseBody> req = client.attemptReg(
                regData.getPicture(),
                regData.getUserType(),
                regData.getName(),
                regData.getEmail(),
                regData.getPassword(),
                regData.getAddress(),
                regData.getCountry(),
                regData.getCity(),
                regData.getBankName(),
                regData.getBankAccountName(),
                regData.getBankAccountNumber()
        );
        
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: " + response.toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: reg");
                t.printStackTrace();
            }
        });

        return false;
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
