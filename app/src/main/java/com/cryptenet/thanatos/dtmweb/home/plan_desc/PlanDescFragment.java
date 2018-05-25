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
        shortDetailsTV = convertView.findViewById(R.id.detailsT);


        return convertView;
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
