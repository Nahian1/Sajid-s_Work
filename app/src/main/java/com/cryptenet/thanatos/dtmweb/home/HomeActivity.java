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
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseFragActivity<HomeActivityContract.Presenter>
        implements HomeActivityContract.View {
    public static final String TAG = TagProvider.getDebugTag(HomeActivity.class);
    private View headerView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.lay_drawer)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    ImageView ivNavPp;
    TextView tvNavName;
    TextView tvNavType;
    TextView tvNavAddress;
    TextView tvNavDetails;
    ImageView ivNavEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewUnbinder = ButterKnife.bind(this);

        headerView = navigationView.getHeaderView(0);
        ivNavPp = headerView.findViewById(R.id.iv_nav_pp);
        tvNavName = headerView.findViewById(R.id.tv_nav_name);
        tvNavType = headerView.findViewById(R.id.tv_nav_type);
        tvNavAddress = headerView.findViewById(R.id.tv_nav_address);
        tvNavDetails = headerView.findViewById(R.id.tv_nav_details);
        ivNavEditProfile = headerView.findViewById(R.id.iv_nav_edit_profile);

        setSupportActionBar(toolbar);

        //presenter.getNavHeaderData();

        setUpNavigation();

        if (savedInstanceState == null) {
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
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.inflateMenu(R.menu.menu_nav_investor);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_man_project:
                        addFragment(R.id.frame_container, new InitiatorProjectFragment());
                        break;
                    case R.id.nav_man_request:
                        addFragment(R.id.frame_container, new RequestDetailFragment());
                        break;
                    case R.id.nav_language:
                        break;
                    case R.id.nav_conversation:
                        break;
                    case R.id.nav_report:
                        addFragment(R.id.frame_container, new ReportIssueFragment());
                        break;
                    case R.id.nav_logout:
                        break;
                    case R.id.nav_tc:
                        break;
                    case R.id.nav_rate:
                        break;
                    case R.id.nav_about:
                        break;
                    default:
                        addFragment(R.id.frame_container, new PlanListFragment());
                        break;
                }

                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                item.setChecked(true);

                return true;
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
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
