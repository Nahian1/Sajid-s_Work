/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.plan_list;


import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.events.ProjectListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.events.ToDetailsFragmentEvent;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanListFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class PlanListFragment extends BaseFragment<PlanListFragmentContract.Presenter>
        implements PlanListFragmentContract.View {

    public static final String TAG = TagProvider.getDebugTag(PlanListFragment.class);
    private List<ProjectsRsp> projectsRspList;
    private ListView projectLV;
    private PlanListAdapter adapter;
    private String token;

    public PlanListFragment() {
        projectsRspList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_plan_list, container, false);
        adapter = new PlanListAdapter(activityContext, projectsRspList);
        projectLV = convertView.findViewById(R.id.projectListPlan);
        projectLV.setAdapter(adapter);

        token = getArguments().getString("token");
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

    @Override
    public void toDetailsView(ProjectsRsp projectsRsp, int type) {
        if (type == 1 && projectsRsp.getIsApproved())
            EventBus.getDefault().post(new ToDetailsFragmentEvent(projectsRsp.getId(), 1));
        else if (type == 1 && !projectsRsp.getIsApproved()) {
            EventBus.getDefault().post(new ToDetailsFragmentEvent(projectsRsp.getId(), 2));
        } else {
            EventBus.getDefault().post(new ToDetailsFragmentEvent(projectsRsp.getId(), 3));
        }
    }

    @Subscribe
    public void onProjectListReceiveEvent(ProjectListReceiveEvent event) {
        this.projectsRspList = event.projectsRspList;
        for (ProjectsRsp projectsRsp : projectsRspList)
            Log.d(TAG, "onProjectListReceiveEvent: " + projectsRsp.getTitle());
        adapter.updateList(this.projectsRspList);
    }

    @Subscribe
    public void onItemClickEvent(String clickPosition) {

        presenter.checkUserType(projectsRspList.get(Integer.parseInt(clickPosition)), activityContext);

    }

    @Subscribe
    public void onPlanSearchEvent(String searchQuery) {

        if (searchQuery.equals("null"))
            presenter.getProjectList(activityContext, token);
        else
            presenter.searchMyPlans(activityContext, token, searchQuery);

    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.attachView(this);

        presenter.getProjectList(activityContext, token);
    }

    //commented out by Asif
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
//            EventBus.getDefault().register(this);
//    }
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
//            EventBus.getDefault().register(this);
//    }

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

}
