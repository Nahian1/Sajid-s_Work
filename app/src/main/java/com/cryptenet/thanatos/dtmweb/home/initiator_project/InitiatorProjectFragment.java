/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.initiator_project;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.events.ManageProjectReceiveEvent;
import com.cryptenet.thanatos.dtmweb.events.ProjectListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.events.SearchEvent;
import com.cryptenet.thanatos.dtmweb.events.ToDetailsFragmentEvent;
import com.cryptenet.thanatos.dtmweb.events.ToEditPlanEvent;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.InitiatorProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.Plans;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class InitiatorProjectFragment extends BaseFragment<InitiatorProjectFragmentContract.Presenter>
        implements InitiatorProjectFragmentContract.View {
    public static final String TAG = TagProvider.getDebugTag(InitiatorProjectFragment.class);

    private List<ProjectsRsp> projectsRspList = new ArrayList<>();
    private List<Plans> plansList = new ArrayList<>();
    private ListView projectLV;
    private ProjectAdapter adapter;
    private ProjectManageAdapter adapter2;
    private int reqType;
    private Unbinder unbinder;


    public InitiatorProjectFragment() {
        projectsRspList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_initiator_project, container, false);

        unbinder = ButterKnife.bind(this, convertView);

        reqType = getArguments().getInt("reqType");

        if (reqType ==1) {
            projectLV = convertView.findViewById(R.id.projectListView);
//        adapter = new ProjectAdapter(activityContext, INVPlanGenerator.getList(), reqType); //test search with dummy data
            adapter = new ProjectAdapter(activityContext, projectsRspList, reqType);
            projectLV.setAdapter(adapter);

            projectLV.setOnItemClickListener((parent, view, position, id) ->
                    EventBus.getDefault().post(new ToDetailsFragmentEvent(projectsRspList != null && projectsRspList.size() > 0 ? projectsRspList.get(position).getId() : 0, 3)));

        } else {
            convertView.findViewById(R.id.btnAddPlan).setVisibility(View.GONE);

            projectLV = convertView.findViewById(R.id.projectListView);
//        adapter = new ProjectAdapter(activityContext, INVPlanGenerator.getList(), reqType); //test search with dummy data
            adapter2 = new ProjectManageAdapter(activityContext, plansList, reqType);
            projectLV.setAdapter(adapter2);

            projectLV.setOnItemClickListener((parent, view, position, id) ->
                    EventBus.getDefault().post(new ToDetailsFragmentEvent(plansList != null && plansList.size() > 0 ? plansList.get(position).getId() : 0, 3)));

        }

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
    public void onSearchEvent(SearchEvent event) {
        adapter.getFilter().filter(event.searchTxt);
    }

    @Subscribe
    public void onProjectListReceiveEvent(ProjectListReceiveEvent event) {
        Log.d(TAG, "onProjectListReceiveEvent: login");
//        this.projectsRspList.clear();
//        this.projectsRspList.addAll(event.projectsRspList);
        this.projectsRspList = event.projectsRspList;
        adapter.updateList(this.projectsRspList);
    }

    @Subscribe
    public void onManageProjectReceiveEvent(ManageProjectReceiveEvent event) {
        Log.d(TAG, "onProjectListReceiveEvent: login");
//        this.projectsRspList.clear();
//        this.projectsRspList.addAll(event.projectsRspList);
        this.plansList = event.projectsRspList;
        adapter2.updateList(this.plansList);
    }

    @OnClick(R.id.btnAddPlan)
    public void btnAddPlan(View view) {
        ProjectsRsp pro = new ProjectsRsp();
        pro.setEditMode(false);

        EventBus.getDefault().post(new ToEditPlanEvent(pro));
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.attachView(this);

        presenter.getMyProjectList(reqType, activityContext);
    }

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
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
