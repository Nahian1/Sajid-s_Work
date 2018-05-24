/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragActivity;
import com.cryptenet.thanatos.dtmweb.home.plan_list.PlanListFragment;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.HomeActivityContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseFragActivity<HomeActivityContract.Presenter>
        implements HomeActivityContract.View {
    public static final String TAG = TagProvider.getDebugTag(HomeActivity.class);
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.lay_drawer)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewUnbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        if(savedInstanceState == null)
            addFragment(R.id.frame_container, new PlanListFragment());
    }

    @Override
    public HomeActivity getActivity() {
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

    @Override
    protected void onResume() {
        super.onResume();
    }
}
