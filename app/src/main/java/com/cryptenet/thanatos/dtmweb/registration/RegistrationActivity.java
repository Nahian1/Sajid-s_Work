/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.registration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.RegistrationActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.ImageFilePath;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class RegistrationActivity extends BaseActivity<RegistrationActivityContract.Presenter>
        implements RegistrationActivityContract.View, AdapterView.OnItemSelectedListener {
    private static final String TAG = TagProvider.getDebugTag(RegistrationActivity.class);
    private File imageFile;
    private String accType;
    private int countryCode, cityCode;
    private List<Country> countries;
    private List<City> cities;

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
    }

    @Override
    public RegistrationActivity getActivity() {
        return null;
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

    @OnClick(R.id.iv_pp)
    public void getPp() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, ConstantProvider.RESULT_LOAD_IMG);
    }

    @OnClick(R.id.btn_sign_in_reg)
    public void requestRegistration() {
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

    @Override
    public void updateCountries(List<Country> countries) {

    }

    @Override
    public void updateCities(List<City> cities) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.spin_acc_type:
                accType = spinAccType.getSelectedItem().toString();
                break;
            case R.id.spin_country:
                countryCode = countries.get(position).getCountryId();
                presenter.getLimitedCities(countryCode);
                break;
            case R.id.spin_city:
                cityCode = cities.get(position).getCityId();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
