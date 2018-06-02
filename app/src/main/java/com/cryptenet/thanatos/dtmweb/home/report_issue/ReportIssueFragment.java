/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.report_issue;


import android.app.Activity;
import android.content.Context;
import android.os.Build;
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
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ReportIssueFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.IssueParent;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class ReportIssueFragment extends BaseFragment<ReportIssueFragmentContract.Presenter>
        implements ReportIssueFragmentContract.View {
    public static final String TAG = TagProvider.getDebugTag(ReportIssueFragment.class);

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

        ((HomeActivity) getActivity()).hideSearchBar(true);

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

//        // This listener will show toast on group click
//        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//
//            @Override
//            public boolean onGroupClick(ExpandableListView listview, View view,
//                                        int group_pos, long id) {
//
//                Toast.makeText(activityContext,
//                        "You clicked : " + adapter.getGroup(group_pos),
//                        Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });

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
//            Toast.makeText(
//                    activityContext,
//                    "You clicked : " + issueParents.get(groupPos).getTopics().get(childPos).getName(),
//                    Toast.LENGTH_SHORT).show();
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
        this.issueParents = event.issueParents;

        adapter.updateList(this.issueParents);
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.attachView(this);

        presenter.getAllIssues(activityContext);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            EventBus.getDefault().register(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

}
