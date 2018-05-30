/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragActivity;
import com.cryptenet.thanatos.dtmweb.events.IssueTopicChosenEvent;
import com.cryptenet.thanatos.dtmweb.events.PlanDetailsRequestEvent;
import com.cryptenet.thanatos.dtmweb.events.ReturnToHomeEvent;
import com.cryptenet.thanatos.dtmweb.events.ToDetailsFragmentEvent;
import com.cryptenet.thanatos.dtmweb.events.ToEditPlanEvent;
import com.cryptenet.thanatos.dtmweb.events.TransactionSuccessEvent;
import com.cryptenet.thanatos.dtmweb.home.edit_project.EditProjectFragment;
import com.cryptenet.thanatos.dtmweb.home.form.FormFragment;
import com.cryptenet.thanatos.dtmweb.home.initiator_project.InitiatorProjectFragment;
import com.cryptenet.thanatos.dtmweb.home.investor_project.InvestorProjectFragment;
import com.cryptenet.thanatos.dtmweb.home.other_report.OtherReportFragment;
import com.cryptenet.thanatos.dtmweb.home.plan_desc.PlanDescFragment;
import com.cryptenet.thanatos.dtmweb.home.plan_list.PlanListFragment;
import com.cryptenet.thanatos.dtmweb.home.report_issue.ReportIssueFragment;
import com.cryptenet.thanatos.dtmweb.home.thread_list.ThreadListFragment;
import com.cryptenet.thanatos.dtmweb.home.transaction.TransactionFragment;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.HomeActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.NavHeader;
import com.cryptenet.thanatos.dtmweb.pojo.User;
import com.cryptenet.thanatos.dtmweb.utils.JsonKeys;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
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

    @BindView(R.id.report)
    LinearLayout report;

    @BindView(R.id.project)
    LinearLayout project;
    @BindView(R.id.request)
    LinearLayout request;
    @BindView(R.id.language)
    LinearLayout language;
    @BindView(R.id.conversation)
    LinearLayout conversation;
    @BindView(R.id.logOut)
    LinearLayout logOut;
    @BindView(R.id.terms)
    TextView terms;
    @BindView(R.id.rateUs)
    TextView rateUs;
    @BindView(R.id.about)
    TextView about;
    @BindView(R.id.iv_nav_pp)
    CircleImageView ivNavPp;
    @BindView(R.id.tv_nav_name)
    TextView tvNavName;
    @BindView(R.id.tv_nav_type)
    TextView tvNavType;
    @BindView(R.id.tv_nav_address)
    TextView tvNavAddress;
    @BindView(R.id.iv_nav_edit_profile)
    ImageView ivNavEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewUnbinder = ButterKnife.bind(this);

        if (PreferenceManager.getDefaultSharedPreferences(this).getString(ConstantProvider.SP_USER_TYPE, null).equals("Initiator"))
            report.setVisibility(View.GONE);

        ivNavPp = findViewById(R.id.iv_nav_pp);
        tvNavName = findViewById(R.id.tv_nav_name);
        tvNavType = findViewById(R.id.tv_nav_type);
        tvNavAddress = findViewById(R.id.tv_nav_address);
        ivNavEditProfile = findViewById(R.id.iv_nav_edit_profile);

        setSupportActionBar(toolbar);

        //presenter.getNavHeaderData();

//        setUpNavigation();
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
            bundle.putString("token", user.getAccessToken());
            Log.d(TAG, "sending tk: " + user.getAccessToken());
            fragment.setArguments(bundle);
            addFragment(R.id.frame_container, fragment);
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

    @OnClick(R.id.project)
    public void onManageProject(View view) {

        drawerLayout.closeDrawer(GravityCompat.START);

        if (PreferenceManager.getDefaultSharedPreferences(this).getString(ConstantProvider.SP_USER_TYPE, null).equals("Initiator")) {
            InitiatorProjectFragment fragment1 = new InitiatorProjectFragment();
            Bundle bundle1 = new Bundle();
            bundle1.putInt("reqType", 1);
            fragment1.setArguments(bundle1);
            replaceFragment(R.id.frame_container, fragment1);
        } else {
            InvestorProjectFragment fragment1 = new InvestorProjectFragment();
            Bundle bundle1 = new Bundle();
            bundle1.putInt("reqType", 1);
            fragment1.setArguments(bundle1);
            replaceFragment(R.id.frame_container, fragment1);
        }
    }

    @OnClick(R.id.request)
    public void onManageRequest(View view) {

        drawerLayout.closeDrawer(GravityCompat.START);

        if (PreferenceManager.getDefaultSharedPreferences(this).getString(ConstantProvider.SP_USER_TYPE, null).equals("Initiator")) {
            InitiatorProjectFragment fragment1 = new InitiatorProjectFragment();
            Bundle bundle1 = new Bundle();
            bundle1.putInt("reqType", 2);
            fragment1.setArguments(bundle1);
            replaceFragment(R.id.frame_container, fragment1);
        } else {
            InvestorProjectFragment fragment1 = new InvestorProjectFragment();
            Bundle bundle1 = new Bundle();
            bundle1.putInt("reqType", 2);
            fragment1.setArguments(bundle1);
            replaceFragment(R.id.frame_container, fragment1);
        }

    }

    @OnClick(R.id.conversation)
    public void onConversation(View view) {

        drawerLayout.closeDrawer(GravityCompat.START);

        ThreadListFragment fragment1 = new ThreadListFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("reqType", PreferenceManager.getDefaultSharedPreferences(this).getString(ConstantProvider.SP_USER_TYPE, null) == "Investor" ? 1 : 2);
        fragment1.setArguments(bundle1);
        replaceFragment(R.id.frame_container, fragment1);
    }

    @OnClick(R.id.report)
    public void onReport(View view) {
        drawerLayout.closeDrawer(GravityCompat.START);
        replaceFragment(R.id.frame_container, new ReportIssueFragment());
    }

    @OnClick(R.id.iv_nav_edit_profile)
    public void editProfile(View view) {

        drawerLayout.closeDrawer(GravityCompat.START);

        navigator.toRegistrationActivity(this, true);

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
    }

    @Subscribe
    public void onToDetailsFragmentEvent(ToDetailsFragmentEvent event) {
        PlanDescFragment fragment = new PlanDescFragment();
        Bundle bundle = new Bundle();

        bundle.putInt("project_id", event.projectId);
        bundle.putInt("type", event.layoutType);
        fragment.setArguments(bundle);
        replaceFragment(R.id.frame_container, fragment);
    }

    @Subscribe
    public void onTransactionSuccessEvent(TransactionSuccessEvent event) {
        TransactionFragment fragment = new TransactionFragment();
        Bundle bundle = new Bundle();

        bundle.putString(JsonKeys.TRANSACTION_DETAILS, new Gson().toJson(event.transaction));

        fragment.setArguments(bundle);
        replaceFragment(R.id.frame_container, fragment);
    }

    @Subscribe
    public void onPlanDetailsRequestEvent(PlanDetailsRequestEvent event) {
        FormFragment fragment = new FormFragment();
        Bundle bundle = new Bundle();

        bundle.putString("project_details", new Gson().toJson(event.detailed));
        fragment.setArguments(bundle);
        replaceFragment(R.id.frame_container, fragment);
    }

    @Subscribe
    public void onToEditProjectFragmentEvent(ToEditPlanEvent event) {
        EditProjectFragment fragment = new EditProjectFragment();
        Bundle bundle = new Bundle();

        bundle.putString("project", new Gson().toJson(event.project));
        fragment.setArguments(bundle);
        replaceFragment(R.id.frame_container, fragment);
    }

    @Subscribe
    public void onIssueTopicChosenEvent(IssueTopicChosenEvent event) {
        OtherReportFragment fragment = new OtherReportFragment();
        Bundle bundle = new Bundle();

        bundle.putInt("issue_code", event.issueCode);
        fragment.setArguments(bundle);
        replaceFragment(R.id.frame_container, fragment);
    }

    @Subscribe
    public void onReturnToHomeEvent(ReturnToHomeEvent event) {
        replaceFragment(R.id.frame_container, new PlanListFragment());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);

        presenter.getNavHeaderData(this);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.iv_nav_edit_profile)
    public void onViewClicked() {
    }
}
