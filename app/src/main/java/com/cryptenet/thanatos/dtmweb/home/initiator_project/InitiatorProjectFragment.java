/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.initiator_project;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.events.ManageProjectReceiveEvent;
import com.cryptenet.thanatos.dtmweb.events.ProjectListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.events.RequestDetailFragmentEvent;
import com.cryptenet.thanatos.dtmweb.events.RequestFailureEvent;
import com.cryptenet.thanatos.dtmweb.events.SearchEvent;
import com.cryptenet.thanatos.dtmweb.events.SearchTextClearEvent;
import com.cryptenet.thanatos.dtmweb.events.ToDetailsFragmentEvent;
import com.cryptenet.thanatos.dtmweb.events.ToEditPlanEvent;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.InitiatorProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.Plans;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;
import com.cryptenet.thanatos.dtmweb.utils.ProgressDialogHelper;
import com.cryptenet.thanatos.dtmweb.utils.ViewUtils;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class InitiatorProjectFragment extends BaseFragment<InitiatorProjectFragmentContract.Presenter>
        implements InitiatorProjectFragmentContract.View {
    public static final String TAG = TagProvider.getDebugTag(InitiatorProjectFragment.class);

    private List<ProjectsRsp> projectsRspList;
    private List<Plans> plansList = new ArrayList<>();
    private ListView projectLV;
    private ProjectAdapter manageProjectAdapter;
    private ProjectManageReqAdapter manageReqAdapter;
    private int reqType;
    private Unbinder unbinder;
    private boolean doMoreRequest;
    private boolean moreDataAvailable;


    public InitiatorProjectFragment() {
        projectsRspList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_initiator_project, container, false);

        ((HomeActivity) getActivity()).hideSearchBar(false);

        unbinder = ButterKnife.bind(this, convertView);

        reqType = getArguments().getInt("reqType");

        projectLV = convertView.findViewById(R.id.projectListView);

        if (reqType == 1) {
//        adapter = new ProjectAdapter(activityContext, INVPlanGenerator.getList(), reqType); //test search with dummy data
            manageProjectAdapter = new ProjectAdapter(activityContext, projectsRspList, reqType);
            projectLV.setAdapter(manageProjectAdapter);

            projectLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    ViewUtils.hideKeyboard(getActivity());
                    EventBus.getDefault().post(new SearchTextClearEvent(true));
                    EventBus.getDefault().post(new ToDetailsFragmentEvent(projectsRspList != null && projectsRspList.size() > 0 ? projectsRspList.get(position).getId() : 0, 21));
                    projectsRspList.clear();
                }
            });

        } else {
            convertView.findViewById(R.id.btnAddPlan).setVisibility(View.GONE);
//        adapter = new ProjectAdapter(activityContext, INVPlanGenerator.getList(), reqType); //test search with dummy data
            manageReqAdapter = new ProjectManageReqAdapter(activityContext, plansList, reqType);
            projectLV.setAdapter(manageReqAdapter);

            projectLV.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    doMoreRequest = true;
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    final int lastItem = firstVisibleItem + visibleItemCount;
                    if (lastItem == totalItemCount && totalItemCount > 0) {
                        if (moreDataAvailable && doMoreRequest) {
                            presenter.getMyProjectList(reqType, activityContext, totalItemCount);
                            doMoreRequest = false;
                        }
                    }
                }
            });

            projectLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    EventBus.getDefault().post(new SearchTextClearEvent(true));
                    ViewUtils.hideKeyboard(getActivity());
                    EventBus.getDefault().post(new RequestDetailFragmentEvent(plansList.get(position).getId()));
                }
            });

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

        if (reqType == 1) {

            manageProjectAdapter.getFilter().filter(event.searchTxt);

        } else {

            manageReqAdapter.getFilter().filter(event.searchTxt);
        }
    }

    @Subscribe
    public void onProjectListReceiveEvent(ProjectListReceiveEvent event) {
        Log.d(TAG, "onProjectListReceiveEvent: login");

        ProgressDialogHelper.hideProgress();

        this.projectsRspList = event.projectsRspList;

        if (projectsRspList != null)
            manageProjectAdapter.updateList(this.projectsRspList);
    }

    @Subscribe
    public void onManageProjectReceiveEvent(ManageProjectReceiveEvent event) {
        Log.d(TAG, "onProjectListReceiveEvent: login");

        ProgressDialogHelper.hideProgress();

        if (event.projectsRspList.isEmpty())
            moreDataAvailable = false;
        else
            moreDataAvailable = true;

        if (this.plansList.size() == 0)
            this.plansList = event.projectsRspList;
        else if (doMoreRequest)
            this.plansList.addAll(event.projectsRspList);

        if (this.plansList != null)
            manageReqAdapter.updateList(this.plansList);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRequestFailureEvent(RequestFailureEvent event) {

        ProgressDialogHelper.hideProgress();

        showMessage("Couldn't fetch data, try again!");
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

        ProgressDialogHelper.init(getActivity()).showProgress();

        if (reqType == 1)
            projectsRspList.clear();
        else
            plansList.clear();

        presenter.getMyProjectList(reqType, activityContext, 0);
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

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
