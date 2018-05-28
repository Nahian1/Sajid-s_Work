/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.registration;

import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseActivity;
import com.cryptenet.thanatos.dtmweb.events.CityFetchEvent;
import com.cryptenet.thanatos.dtmweb.events.CountryFetchEvent;
import com.cryptenet.thanatos.dtmweb.events.RegistrationSuccessEvent;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.RegistrationActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.City;
import com.cryptenet.thanatos.dtmweb.pojo.Country;
import com.cryptenet.thanatos.dtmweb.pojo.User;
import com.cryptenet.thanatos.dtmweb.utils.ImageFilePath;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends BaseActivity<RegistrationActivityContract.Presenter>
        implements RegistrationActivityContract.View, AdapterView.OnItemSelectedListener {
    private static final String TAG = TagProvider.getDebugTag(RegistrationActivity.class);
    private File imageFile;
    private String accType;
    private int countryCode, cityCode;
    private List<Country> countries;
    private List<City> cities;
    private List<String> sCountries, sCities;
    private ArrayAdapter<String> spinAccTypeAdapter, spinCountryAdapter, spinCityAdapter;
    private boolean isEdit;

    @BindView(R.id.iv_pp)
    ImageView ivPp;

    @BindView(R.id.et_name_reg)
    EditText etNameReg;

    @BindView(R.id.et_email_reg)
    EditText etEmailReg;

    @BindView(R.id.et_pwd_reg)
    EditText etPwdReg;

    @BindView(R.id.et_confirm_pwd)
    EditText etConfirmPwd;

    @BindView(R.id.et_address)
    EditText etAddress;

    @BindView(R.id.et_bank_name_reg)
    EditText etBankNameReg;

    @BindView(R.id.et_bank_acc_name_reg)
    EditText etBankAccNameReg;

    @BindView(R.id.et_bank_acc_number_reg)
    EditText etBankAccNumberReg;

    @BindView(R.id.spin_acc_type)
    Spinner spinAccType;

    @BindView(R.id.spin_country)
    Spinner spinCountry;

    @BindView(R.id.spin_city)
    Spinner spinCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        viewUnbinder = ButterKnife.bind(this);

        isEdit = getIntent().getBooleanExtra("isEdit",false);

        init();

        if (isEdit) {

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            String imageUrl = sharedPreferences.getString(ConstantProvider.SP_PICTURE_URL,null);

            if (imageUrl!=null) {
                Glide.with(this)
                        .load(imageUrl)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_nav_profile_picture))
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(ivPp);

            }

            etNameReg.setText(sharedPreferences.getString(ConstantProvider.SP_NAME,null));
            etEmailReg.setText(sharedPreferences.getString(ConstantProvider.SP_EMAIL,null));
            etAddress.setText(sharedPreferences.getString(ConstantProvider.SP_ADDRESS,null));
            etBankNameReg.setText(sharedPreferences.getString(ConstantProvider.SP_BANK_NAME,null));
            etBankAccNameReg.setText(sharedPreferences.getString(ConstantProvider.SP_BANK_ACC_NAME,null));
            etBankAccNumberReg.setText(sharedPreferences.getString(ConstantProvider.SP_BANK_ACC_NO,null));

        }

    }

    private void init() {
        List<String> accTypes = new ArrayList<>();
        sCountries = new ArrayList<>();
        sCities = new ArrayList<>();

        accTypes.add(getString(R.string.acc_type_initiator));
        accTypes.add(getString(R.string.acc_type_investor));

        spinAccTypeAdapter = new ArrayAdapter<>(this,
                R.layout.node_spin_reg, accTypes);
        spinAccTypeAdapter.setDropDownViewResource(R.layout.node_spin_reg);
        spinAccType.setAdapter(spinAccTypeAdapter);

        sCountries.add("Country");

        spinCountryAdapter = new ArrayAdapter<>(this,
                R.layout.node_spin_reg, sCountries);
        spinCountryAdapter.setDropDownViewResource(R.layout.node_spin_reg);
        spinCountry.setAdapter(spinCountryAdapter);

        sCities.add("City");

        spinCityAdapter = new ArrayAdapter<>(this,
                R.layout.node_spin_reg, sCities);
        spinCityAdapter.setDropDownViewResource(R.layout.node_spin_reg);
        spinCity.setAdapter(spinCityAdapter);

        spinAccType.setOnItemSelectedListener(this);
        spinCountry.setOnItemSelectedListener(this);
        spinCity.setOnItemSelectedListener(this);
    }

    @Override
    public RegistrationActivity getActivity() {
        return this;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void printLog(String TAG, String message) {
        Log.d(TAG, "printLog: " + message);
    }

    @Override
    public void restoreState(Bundle savedState) {

    }

    @Override
    public void moveToSignIn() {
        navigator.toLoginActivity(this);
        finish();
    }

    @OnClick(R.id.iv_pp)
    public void getPp(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, ConstantProvider.RESULT_LOAD_IMG);
    }

    @OnClick(R.id.btn_sign_in_reg)
    public void requestRegistration(View view) {
        presenter.carryRegData(
                imageFile,
                accType,
                etNameReg.getText().toString().trim(),
                etEmailReg.getText().toString().trim(),
                etPwdReg.getText().toString().trim(),
                etConfirmPwd.getText().toString().trim(),
                etAddress.getText().toString().trim(),
                countryCode,
                cityCode,
                etBankNameReg.getText().toString().trim(),
                etBankAccNameReg.getText().toString().trim(),
                etBankAccNumberReg.getText().toString().trim()
        );
    }

    @OnClick(R.id.tv_sign_in)
    public void toSignIn(View view) {
        presenter.checkLoginState(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String realPath = null;
        Bitmap selectedImage;

        if (resultCode == RESULT_OK) {
            if (requestCode == ConstantProvider.RESULT_LOAD_IMG) {
                try {
                    final Uri imageUri = data.getData();
                    realPath = ImageFilePath.getPath(RegistrationActivity.this, data.getData());
                    assert imageUri != null;
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    selectedImage = BitmapFactory.decodeStream(imageStream);
                    ivPp.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                imageFile = new File(realPath);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCountryFetchEvent(CountryFetchEvent event) {
        this.countries = event.countries;
        sCountries.clear();
        for (Country country : this.countries)
            sCountries.add(country.getName());
        spinCountryAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCityFetchEvent(CityFetchEvent event) {
        this.cities = event.cities;
        sCities.clear();
        for (City city : this.cities)
            sCities.add(city.getName());
        spinCityAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegistrationSuccessEvent(RegistrationSuccessEvent event) {
        showMessage("Registered");
        navigator.toLoginActivity(this);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spin_acc_type:
                accType = spinAccType.getSelectedItem().toString();
                break;
            case R.id.spin_country:
                if (countries != null)
                countryCode = (countries.get(position)).getId();
                Log.d(TAG, "onItemSelected: " + countryCode);
                presenter.getLimitedCities(countryCode == 0 ? 1 : countryCode);
                break;
            case R.id.spin_city:
                if (cities != null)
                cityCode = cities.get(position).getId();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if (parent.getId() == R.id.spin_country) {
            presenter.getLimitedCities(1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.getAllCountries();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
