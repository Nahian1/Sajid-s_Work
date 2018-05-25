/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home;

import android.os.Bundle;
import android.os.Parcelable;
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
import com.cryptenet.thanatos.dtmweb.events.ProjectListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.home.initiator_project.InitiatorProjectFragment;
import com.cryptenet.thanatos.dtmweb.home.plan_list.PlanListFragment;
import com.cryptenet.thanatos.dtmweb.home.report_issue.ReportIssueFragment;
import com.cryptenet.thanatos.dtmweb.home.request_detail.RequestDetailFragment;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.HomeActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.NavHeader;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseFragActivity<HomeActivityContract.Presenter>
        implements HomeActivityContract.View, View.OnClickListener {
    public static final String TAG = TagProvider.getDebugTag(HomeActivity.class);
    private View headerView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.lay_drawer)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.menuRight)
    ImageView menuRight;

    ImageView ivNavPp;
    TextView tvNavName;
    TextView tvNavType;
    TextView tvNavAddress;
    TextView tvNavDetails;
    ImageView ivNavEditProfile;

    ImageView ivToolbarHam;

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

        ivToolbarHam = toolbar.findViewById(R.id.menuRight);

        setSupportActionBar(toolbar);

        //presenter.getNavHeaderData();

        setUpNavigation();

        if (savedInstanceState == null) {
            navigationView.getMenu().getItem(0).setChecked(true);
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

    @Subscribe
    public void onProjectListReceiveEvent(ProjectListReceiveEvent event) {
        Log.d(TAG, "onProjectListReceiveEvent: home");
        PlanListFragment planListFragment = new PlanListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("projects", (ArrayList<? extends Parcelable>) event.projectsList);
        planListFragment.setArguments(bundle);
        addFragment(R.id.frame_container, planListFragment);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menuRight:
                drawerLayout.openDrawer();
                break;
        }
    }
}
