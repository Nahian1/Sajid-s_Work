/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.report_issue;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.events.IssueListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.events.IssueTopicChosenEvent;
import com.cryptenet.thanatos.dtmweb.events.RequestFailureEvent;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ReportIssueFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.IssueParent;
import com.cryptenet.thanatos.dtmweb.utils.ProgressDialogHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class ReportIssueFragment extends BaseFragment<ReportIssueFragmentContract.Presenter>
        implements ReportIssueFragmentContract.View {
//    public static final String TAG = TagProvider.getDebugTag(ReportIssueFragment.class);

    private ExpandableListView expandableListView;
    private ELVAdapter adapter;

    private List<IssueParent> issueParents;

    public ReportIssueFragment() {
        issueParents = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_report_issue, container, false);

        ((HomeActivity) getActivity()).setToolBarTitle(getString(R.string.report_issue));

        expandableListView = convertView.findViewById(R.id.elv_report);
        expandableListView.setGroupIndicator(null);

        setItems();
        setListener();
        return convertView;
    }

    void setItems() {
        adapter = new ELVAdapter(activityContext, issueParents);

        expandableListView.setAdapter(adapter);
    }

    void setListener() {
        expandableListView
                .setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                    // Default position
                    int previousGroup = -1;

                    @Override
                    public void onGroupExpand(int groupPosition) {
                        if (groupPosition != previousGroup)

                            // Collapse the expanded group
                            expandableListView.collapseGroup(previousGroup);
                        previousGroup = groupPosition;
                    }

                });

        expandableListView.setOnChildClickListener((listview, view, groupPos, childPos, id) -> {
            EventBus.getDefault().post(new IssueTopicChosenEvent(
                    issueParents.get(groupPos).getTopics().get(childPos).getId()
            ));
            return true;
        });
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
    public void onIssueListReceiveEvent(IssueListReceiveEvent event) {

        ProgressDialogHelper.hideProgress();

        this.issueParents = event.issueParents;

        adapter.updateList(this.issueParents);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRequestFailureEvent(RequestFailureEvent event) {

        ProgressDialogHelper.hideProgress();

        showMessage("Couldn't fetch data, try again!");
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.attachView(this);

        ProgressDialogHelper.init(getActivity()).showProgress();
        presenter.getAllIssues(activityContext);
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

}
