/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragActivity;
import com.cryptenet.thanatos.dtmweb.home.initiator_project.InitiatorProjectFragment;
import com.cryptenet.thanatos.dtmweb.home.plan_list.PlanListFragment;
import com.cryptenet.thanatos.dtmweb.home.report_issue.ReportIssueFragment;
import com.cryptenet.thanatos.dtmweb.home.request_detail.RequestDetailFragment;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.HomeActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.NavHeader;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;

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

    @BindView(R.id.menuRight)
    ImageView menuRight;

    @BindView(R.id.iv_nav_pp)
    ImageView ivNavPp;

    @BindView(R.id.tv_nav_name)
    TextView tvNavName;

    @BindView(R.id.tv_nav_type)
    TextView tvNavType;

    @BindView(R.id.tv_nav_address)
    TextView tvNavAddress;

    @BindView(R.id.tv_nav_details)
    TextView tvNavDetails;

    @BindView(R.id.iv_nav_edit_profile)
    ImageView ivNavEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewUnbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        presenter.getNavHeaderData();

        setUpNavigation();

        if (savedInstanceState == null) {
            navigationView.getMenu().getItem(0).setChecked(true);
            addFragment(R.id.frame_container, new PlanListFragment());
        }
    }

    @Override
    public HomeActivity getActivity() {
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

    private void setUpNavigation() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_man_project:
                        addFragment(R.id.frame_container, new InitiatorProjectFragment());
                        return true;
                    case R.id.nav_man_request:
                        addFragment(R.id.frame_container, new RequestDetailFragment());
                        return true;
                    case R.id.nav_language:
                        return true;
                    case R.id.nav_conversation:
                        return true;
                    case R.id.nav_report:
                        addFragment(R.id.frame_container, new ReportIssueFragment());
                        return true;
                    case R.id.nav_logout:
                        return true;
                    case R.id.nav_tc:
                        return true;
                    case R.id.nav_rate:
                        return true;
                    case R.id.nav_about:
                        return true;
                }

                return false;
            }
        });
    }

    @Override
    public void getNavHeaderData(NavHeader header) {
        Glide.with(this)
                .load(header.getPpUrl())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_nav_profile_picture))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivNavPp);

        tvNavName.setText(header.getName());
        tvNavType.setText(header.getType());
        tvNavAddress.setText(header.getLocation());
        tvNavDetails.setText(header.getDesc());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().register(this);
    }
}
