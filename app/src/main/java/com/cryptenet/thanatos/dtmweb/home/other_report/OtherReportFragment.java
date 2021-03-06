/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.other_report;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.events.IssueSubmittedEvent;
import com.cryptenet.thanatos.dtmweb.events.RequestFailureEvent;
import com.cryptenet.thanatos.dtmweb.events.ReturnToHomeEvent;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.OtherReportFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.ProgressDialogHelper;
import com.cryptenet.thanatos.dtmweb.utils.ViewUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OtherReportFragment extends BaseFragment<OtherReportFragmentContract.Presenter>
        implements OtherReportFragmentContract.View {
//    public static final String TAG = TagProvider.getDebugTag(OtherReportFragment.class);
    @BindView(R.id.add_report)
    EditText addReport;

    private int issueCode;

    public OtherReportFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_other_issue, container, false);

        ((HomeActivity) getActivity()).setToolBarTitle(getString(R.string.submit_issue));

        issueCode = getArguments().getInt("issue_code");
        ButterKnife.bind(this, convertView);

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

    @Subscribe
    public void onIssueSubmittedEvent(IssueSubmittedEvent event) {

        ProgressDialogHelper.hideProgress();
        if (event.isSubmitted) {

            EventBus.getDefault().post(new ReturnToHomeEvent());

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRequestFailureEvent(RequestFailureEvent event) {

        ProgressDialogHelper.hideProgress();

        showMessage("Couldn't fetch data, try again!");
    }

    @OnClick({R.id.report_submit, R.id.report_cancel})
    public void onViewClicked(View view) {

        ViewUtils.hideKeyboard(getActivity());

        switch (view.getId()) {
            case R.id.report_submit:
                String issue = addReport.getText().toString().trim();

                if (!issue.isEmpty()) {

                    ProgressDialogHelper.init(getActivity()).showProgress();
                    presenter.sendIssue(activityContext, issueCode, issue);

                } else {
                    showMessage("Field can not be empty");
                }
                break;
            case R.id.report_cancel:
                getActivity().onBackPressed();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.attachView(this);
    }
}
