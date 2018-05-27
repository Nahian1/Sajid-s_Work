/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragActivity;
import com.cryptenet.thanatos.dtmweb.events.ToDetailsFragmentEvent;
import com.cryptenet.thanatos.dtmweb.home.initiator_project.InitiatorProjectFragment;
import com.cryptenet.thanatos.dtmweb.home.plan_desc.PlanDescFragment;
import com.cryptenet.thanatos.dtmweb.home.plan_list.PlanListFragment;
import com.cryptenet.thanatos.dtmweb.home.report_issue.ReportIssueFragment;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.HomeActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.NavHeader;
import com.cryptenet.thanatos.dtmweb.pojo.User;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

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
    ImageView ivMenuRight;

    CircleImageView ivNavPp;
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

        ivNavPp = findViewById(R.id.iv_nav_pp);
        tvNavName = findViewById(R.id.tv_nav_name);
        tvNavType = findViewById(R.id.tv_nav_type);
        tvNavAddress = findViewById(R.id.tv_nav_address);
        ivNavEditProfile = findViewById(R.id.iv_nav_edit_profile);

        setSupportActionBar(toolbar);

        //presenter.getNavHeaderData();

        setUpNavigation();
        Intent intent = getIntent();
        String s = intent.getStringExtra("user");
        User user = null;
        if (s != null) {
            Gson gson = new Gson();
            user = gson.fromJson(s, User.class);
        }
        if (savedInstanceState == null) {
            PlanListFragment fragment = new PlanListFragment();
            Bundle bundle = new Bundle();
            if (user != null) {
                bundle.putString("token", user.getAccessToken());
            }
            fragment.setArguments(bundle);
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

    @OnClick(R.id.menuRight)
    public void changeNavMenuState(View view) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            drawerLayout.openDrawer(GravityCompat.START);
    }

    private void setUpNavigation() {
        navigationView.inflateMenu(R.menu.menu_nav_investor);
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_man_project:
                    InitiatorProjectFragment fragment1 = new InitiatorProjectFragment();
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("reqType", 1);
                    fragment1.setArguments(bundle1);
                    replaceFragment(R.id.frame_container, fragment1);
                    break;
                case R.id.nav_man_request:
                    InitiatorProjectFragment fragment2 = new InitiatorProjectFragment();
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("reqType", 2);
                    fragment2.setArguments(bundle2);
                    replaceFragment(R.id.frame_container, fragment2);
                    break;
                case R.id.nav_language:
                    break;
                case R.id.nav_conversation:
                    break;
                case R.id.nav_report:
                    replaceFragment(R.id.frame_container, new ReportIssueFragment());
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
                    replaceFragment(R.id.frame_container, new PlanListFragment());
                    break;
            }

            if (item.isChecked()) {
                item.setChecked(false);
            } else {
                item.setChecked(true);
            }
            item.setChecked(true);

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
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

    @Subscribe
    public void onToDetailsFragmentEvent(ToDetailsFragmentEvent event) {
        PlanDescFragment fragment = new PlanDescFragment();
        Bundle bundle = new Bundle();

        bundle.putParcelable("project", event.project);
        fragment.setArguments(bundle);
        replaceFragment(R.id.frame_container, fragment);
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
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
