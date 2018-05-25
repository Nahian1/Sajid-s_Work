/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.plan_desc;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanDescFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.Projects;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;


public class PlanDescFragment extends BaseFragment<PlanDescFragmentContract.Presenter>
        implements PlanDescFragmentContract.View {
    public static final String TAG = TagProvider.getDebugTag(PlanDescFragment.class);
    private Projects projects;
    private TextView titleTV, priceTV, shortDetailsTV, nameTV, typeTV, addressTV, detailsTV, bankNameTV,
            personNameTV, accountNumberTV, payPriceTV;
    private ImageView demoIV,profileIV,seeMoreIV;


    public PlanDescFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_plan_desc, container, false);
        projects = (Projects) getArguments().get("project");
        titleTV = convertView.findViewById(R.id.titleTV);
        priceTV = convertView.findViewById(R.id.priceTV);
//        shortDetailsTV = convertView.findViewById(R.id.detailsTV);
        titleTV = convertView.findViewById(R.id.textViewTitle);
        priceTV = convertView.findViewById(R.id.textViewPrice);
        shortDetailsTV = convertView.findViewById(R.id.textViewDetails);
        nameTV = convertView.findViewById(R.id.textViewName);
        typeTV = convertView.findViewById(R.id.textViewType);
        addressTV = convertView.findViewById(R.id.address);
        detailsTV = convertView.findViewById(R.id.details);
        bankNameTV = convertView.findViewById(R.id.editTextBankName);
        personNameTV = convertView.findViewById(R.id.NameOfperson);
        accountNumberTV = convertView.findViewById(R.id.accountNumber);
        payPriceTV = convertView.findViewById(R.id.price);

        demoIV = convertView.findViewById(R.id.projectIV);
        profileIV = convertView.findViewById(R.id.profilepic);
        seeMoreIV = convertView.findViewById(R.id.seeMoreIV);


        return convertView;
    }

    public void requestforDetails(){

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(activityContext, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void printLog(String TAG, String message) {
        Log.d(TAG, "printLog: " + message);
    }

    @Override
    public void restoreState(Bundle savedState) {

    }
}
