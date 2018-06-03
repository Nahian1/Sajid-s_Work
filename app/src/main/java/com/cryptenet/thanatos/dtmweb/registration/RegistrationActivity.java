/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.registration;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.cryptenet.thanatos.dtmweb.utils.ImageFilePath;
import com.cryptenet.thanatos.dtmweb.utils.ProgressDialogHelper;
import com.cryptenet.thanatos.dtmweb.utils.ViewUtils;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

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
    private List<String> accTypes, sCountries, sCities;
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

    @BindView(R.id.btn_sign_in_reg)
    Button btnSignInReg;

    @BindView(R.id.tv_have_acc)
    TextView tvHaveAcc;

    @BindView(R.id.tv_sign_in)
    TextView tvSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        viewUnbinder = ButterKnife.bind(this);

        isEdit = getIntent().getBooleanExtra("isEdit", false);

        init();

    }

    private void init() {
        accTypes = new ArrayList<>();
        sCountries = new ArrayList<>();
        sCities = new ArrayList<>();

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


        if (isEdit) {

            btnSignInReg.setText(R.string.update_profile);
            tvHaveAcc.setVisibility(View.GONE);
            tvSignIn.setVisibility(View.GONE);

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            String imageUrl = sharedPreferences.getString(ConstantProvider.SP_PICTURE_URL, null);

            if (imageUrl != null) {
                Glide.with(this)
                        .load(imageUrl)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_pp_dummy))
                        .apply(RequestOptions.circleCropTransform())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(ivPp);
            }

            etNameReg.setText(sharedPreferences.getString(ConstantProvider.SP_NAME, null));
            etEmailReg.setText(sharedPreferences.getString(ConstantProvider.SP_EMAIL, null));
            etAddress.setText(sharedPreferences.getString(ConstantProvider.SP_ADDRESS, null));
            etBankNameReg.setText(sharedPreferences.getString(ConstantProvider.SP_BANK_NAME, null));
            etBankAccNameReg.setText(sharedPreferences.getString(ConstantProvider.SP_BANK_ACC_NAME, null));
            etBankAccNumberReg.setText(sharedPreferences.getString(ConstantProvider.SP_BANK_ACC_NO, null));

            accTypes.add(sharedPreferences.getString(ConstantProvider.SP_USER_TYPE, null));

            spinAccType.setEnabled(false);

        } else {

            accTypes.add(getString(R.string.acc_type_initiator));
            accTypes.add(getString(R.string.acc_type_investor));

        }

        spinAccTypeAdapter = new ArrayAdapter<>(this,
                R.layout.node_spin_reg, accTypes);
        spinAccTypeAdapter.setDropDownViewResource(R.layout.node_spin_reg);
        spinAccType.setAdapter(spinAccTypeAdapter);
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
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");

                        startActivityForResult(photoPickerIntent, ConstantProvider.RESULT_LOAD_IMG);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (response.isPermanentlyDenied()) {
                            // navigate user to app settings
                            showMessage("must grant permission");
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    @OnClick(R.id.btn_sign_in_reg)
    public void requestRegistration(View view) {

        ViewUtils.hideKeyboard(this);

        String name = etNameReg.getText().toString().trim();
        String email = etEmailReg.getText().toString().trim();
        String pwd = etPwdReg.getText().toString().trim();
        String cPwd = etConfirmPwd.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String bankName = etBankNameReg.getText().toString().trim();
        String bankAccName = etBankAccNameReg.getText().toString().trim();
        String bankAccNum = etBankAccNumberReg.getText().toString().trim();

//        String name = "Britly";
//        String email = "britly@gmail.com";
//        String pwd = "asdasd123";
//        String cPwd = "asdasd123";
//        String address = "home";
//        String bankName = "bank";
//        String bankAccName = "Britly";
//        String bankAccNum = "123456789";


        if (imageFile != null && !name.isEmpty() && !email.isEmpty() && !address.isEmpty()
                && !bankName.isEmpty() && !bankAccName.isEmpty() && !bankAccNum.isEmpty()) {
            if (pwd.equals(cPwd)) {

                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                    ProgressDialogHelper.init(this).showProgress();

                    presenter.carryRegData(
                            imageFile,
                            accType,
                            name,
                            email,
                            pwd,
                            address,
                            countryCode,
                            cityCode,
                            bankName,
                            bankAccName,
                            bankAccNum
                    );

                } else {

                    showMessage("Invalid email!");

                }

            } else {
                showMessage("Password did not match");
            }
        } else {
            showMessage("Please fill all fields");
        }

        if (isEdit) {
            navigator.toHomeActivity(this, null);
        }
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

                    imageFile = new File(realPath);

                    Glide.with(this)
                            .load(selectedImage)
                            .apply(RequestOptions.circleCropTransform())
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(ivPp);

//                    ivPp.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCountryFetchEvent(CountryFetchEvent event) {
        this.countries = event.countries;
        sCountries.clear();
        for (Country country : this.countries)
            sCountries.add(country.getName());
        countryCode = countries.get(0).getId();
        presenter.getLimitedCities(countries.get(0).getId());
        spinCountryAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCityFetchEvent(CityFetchEvent event) {
        this.cities = event.cities;
        sCities.clear();
        for (City city : this.cities)
            sCities.add(city.getName());
        cityCode = cities.get(0).getId();
        spinCityAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegistrationSuccessEvent(RegistrationSuccessEvent event) {

        ProgressDialogHelper.hideProgress();

        if (!isEdit) {
            showMessage("Registered");
            navigator.toLoginActivity(this);

        } else {
            showMessage("Info updated.");
        }

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
                presenter.getLimitedCities(countryCode);
                break;
            case R.id.spin_city:
                if (cities != null)
                    cityCode = cities.get(position).getId();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
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
