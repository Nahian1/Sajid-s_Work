/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragActivity;
import com.cryptenet.thanatos.dtmweb.events.InitiatorThreadsEvent;
import com.cryptenet.thanatos.dtmweb.events.InvestorThreadsEvent;
import com.cryptenet.thanatos.dtmweb.events.IssueTopicChosenEvent;
import com.cryptenet.thanatos.dtmweb.events.PlanDetailsRequestEvent;
import com.cryptenet.thanatos.dtmweb.events.RequestDetailFragmentEvent;
import com.cryptenet.thanatos.dtmweb.events.ReturnToHomeEvent;
import com.cryptenet.thanatos.dtmweb.events.SearchEvent;
import com.cryptenet.thanatos.dtmweb.events.ThreadIdReceiveEvent;
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
import com.cryptenet.thanatos.dtmweb.home.request_detail.RequestDetailFragment;
import com.cryptenet.thanatos.dtmweb.home.thread_list.ThreadListFragment;
import com.cryptenet.thanatos.dtmweb.home.thread_msg.ThreadMsgFragment;
import com.cryptenet.thanatos.dtmweb.home.thread_project.ThreadProjectFragment;
import com.cryptenet.thanatos.dtmweb.home.transaction.TransactionFragment;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.HomeActivityContract;
import com.cryptenet.thanatos.dtmweb.pojo.NavHeader;
import com.cryptenet.thanatos.dtmweb.utils.JsonKeys;
import com.cryptenet.thanatos.dtmweb.utils.LocaleHelper;
import com.cryptenet.thanatos.dtmweb.utils.ProgressDialogHelper;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    ImageView ivNavPp;
    @BindView(R.id.tv_nav_name)
    TextView tvNavName;
    @BindView(R.id.tv_nav_type)
    TextView tvNavType;
    @BindView(R.id.tv_nav_address)
    TextView tvNavAddress;
    @BindView(R.id.iv_nav_edit_profile)
    ImageView ivNavEditProfile;
    @BindView(R.id.editTextSearch)
    EditText editTextSearch;
    @BindView(R.id.buttonSearch)
    ImageView buttonSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewUnbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        PlanListFragment fragment = new PlanListFragment();

        addFragment(R.id.frame_container, fragment);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                EventBus.getDefault().post(new SearchEvent(editable.toString().trim()));
            }
        });
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

    @OnClick(R.id.language)
    public void language(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getString(ConstantProvider.SELECTED_LANGUAGE, null).equals("en")) {
            LocaleHelper.setNewLocale(this, "ar");
//            preferences.edit().putString(ConstantProvider.SELECTED_LANGUAGE, "ar").apply();
        } else {
            LocaleHelper.setNewLocale(this, "en");
//            preferences.edit().putString(ConstantProvider.LOCALE, "en").apply();
        }
        finish();
        startActivity(new Intent(HomeActivity.this, HomeActivity.class));
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

//
//        Intent intent = new Intent(HomeActivity.this, MessageRequestActivity.class);
//        startActivity(intent);

        ThreadListFragment fragment1 = new ThreadListFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("reqType", PreferenceManager.getDefaultSharedPreferences(this).getString(ConstantProvider.SP_USER_TYPE, null).equals("Investor") ? 1 : 2);
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

    @OnClick(R.id.logOut)
    public void logOut(View view) {
        presenter.clearUserData(this);
    }


    @Override
    public void getNavHeaderData(NavHeader header) {
        Glide.with(this)
                .load(header.getPpUrl())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_profile_white))
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions.circleCropTransform())
                .into(ivNavPp);

        tvNavName.setText(header.getName());
        tvNavType.setText(header.getType());
        tvNavAddress.setText(header.getLocation());
    }

    @Override
    public void userDataCleaned() {
        navigator.toLoginActivity(this);
        finish();
    }


    public void hideSearchBar(boolean shouldHideSearchBar) {

        if (shouldHideSearchBar) {
            editTextSearch.setVisibility(View.GONE);
            buttonSearch.setVisibility(View.GONE);
        } else {
            editTextSearch.setVisibility(View.VISIBLE);
            buttonSearch.setVisibility(View.VISIBLE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onToDetailsFragmentEvent(ToDetailsFragmentEvent event) {

        PlanDescFragment fragment = new PlanDescFragment();
        Bundle bundle = new Bundle();

        bundle.putInt("project_id", event.projectId);
        bundle.putInt("type", event.layoutType);
        fragment.setArguments(bundle);
        replaceFragment(R.id.frame_container, fragment);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRequestDetailFragmentEvent(RequestDetailFragmentEvent event) {
        RequestDetailFragment fragment = new RequestDetailFragment();

        Bundle bundle = new Bundle();

        bundle.putInt("transaction_id", event.transactionId);
        bundle.putString("user_type", PreferenceManager.getDefaultSharedPreferences(this).getString(ConstantProvider.SP_USER_TYPE, null));

        fragment.setArguments(bundle);
        replaceFragment(R.id.frame_container, fragment);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onInitiatorThreadsEvent(InitiatorThreadsEvent event) {
        ThreadProjectFragment fragment = new ThreadProjectFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("plan_id", event.planId);
        fragment.setArguments(bundle);
        replaceFragment(R.id.frame_container, fragment);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onInvestorThreadsEvent(InvestorThreadsEvent event) {
        ThreadMsgFragment fragment = new ThreadMsgFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("thread_id", event.threadId);
        fragment.setArguments(bundle);
        replaceFragment(R.id.frame_container, fragment);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTransactionSuccessEvent(TransactionSuccessEvent event) {

        ProgressDialogHelper.hideProgress();
        showMessage("Submitted!");

        showMessage(getString(R.string.submitted));

        TransactionFragment fragment = new TransactionFragment();
        Bundle bundle = new Bundle();

        bundle.putString(JsonKeys.TRANSACTION_DETAILS, new Gson().toJson(event.transaction));

        fragment.setArguments(bundle);
        replaceFragment(R.id.frame_container, fragment);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPlanDetailsRequestEvent(PlanDetailsRequestEvent event) {
        FormFragment fragment = new FormFragment();
        Bundle bundle = new Bundle();

        bundle.putString("project_details", new Gson().toJson(event.detailed));
        fragment.setArguments(bundle);
        replaceFragment(R.id.frame_container, fragment);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onToEditProjectFragmentEvent(ToEditPlanEvent event) {
        EditProjectFragment fragment = new EditProjectFragment();
        Bundle bundle = new Bundle();

        bundle.putString("project", new Gson().toJson(event.project));
        fragment.setArguments(bundle);
        replaceFragment(R.id.frame_container, fragment);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onIssueTopicChosenEvent(IssueTopicChosenEvent event) {
        OtherReportFragment fragment = new OtherReportFragment();
        Bundle bundle = new Bundle();

        bundle.putInt("issue_code", event.issueCode);
        fragment.setArguments(bundle);
        replaceFragment(R.id.frame_container, fragment);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReturnToHomeEvent(ReturnToHomeEvent event) {
        replaceFragment(R.id.frame_container, new PlanListFragment());

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onThreadIdReceiveEvent(ThreadIdReceiveEvent event) {
        ThreadMsgFragment fragment = new ThreadMsgFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("thread_id", event.threadInitResponse.getId());
        fragment.setArguments(bundle);
        replaceFragment(R.id.frame_container, fragment);


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

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }


    @OnClick({R.id.terms, R.id.rateUs, R.id.about})
    public void onViewClicked(View view) {

        showMessage("Coming soon...");
//        switch (view.getId()) {
//            case R.id.terms:
//                break;
//            case R.id.rateUs:
//                break;
//            case R.id.about:
//                break;
//        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        String lang = PreferenceManager.getDefaultSharedPreferences(newBase).getString(ConstantProvider.SELECTED_LANGUAGE, "en");
        super.attachBaseContext(LocaleHelper.setNewLocale(newBase, lang));
    }
}
