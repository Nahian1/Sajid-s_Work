/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.thread_project;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.events.ThreadProjectListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ThreadInv;
import com.cryptenet.thanatos.dtmweb.utils.ProgressDialogHelper;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class ThreadProjectFragment extends BaseFragment<ThreadProjectFragmentContract.Presenter>
        implements ThreadProjectFragmentContract.View {
    public static final String TAG = TagProvider.getDebugTag(ThreadProjectFragment.class);

//    @BindView(R.id.threadProjectListView)
//    ListView projectLV;

//    private ThreadProjectAdapter adapter;
    private int planId;
//
//    Unbinder unbinder;

    private RecyclerView mRecyclerView;
    private InitiatorThreadAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ThreadInv> threadInvs;

    public ThreadProjectFragment() {
        this.threadInvs = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_thread_project, container, false);

        ((HomeActivity) getActivity()).setToolBarTitle(getString(R.string.nav_conversation));

        // unbinder = ButterKnife.bind(this, convertView);

        planId = getArguments().getInt("plan_id");

//        adapter = new ProjectAdapter(activityContext, ProjectListGenerator.generateProjects());
//        projectLV.setAdapter(adapter);


        mRecyclerView = convertView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new InitiatorThreadAdapter(this.threadInvs);
        mRecyclerView.setAdapter(mAdapter);

        return convertView;
    }

    @Subscribe
    public void onThreadProjectListReceive(ThreadProjectListReceiveEvent event) {

        ProgressDialogHelper.hideProgress();

//        if (event.threadInvs.isEmpty())
//            moreDataAvailable = false;
//        else
//            moreDataAvailable = true;
//
//        if (this.projectsRspList.size() == 0)
//            this.projectsRspList = event.projectsRspList;
//        else if (doMoreRequest)
//            this.projectsRspList.addAll(event.projectsRspList);
//
//        if (this.projectsRspList != null) {
//            mAdapter.setData(this.projectsRspList);
//            mAdapter.notifyDataSetChanged();
//        }
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
    public void onResume() {
        super.onResume();
        presenter.attachView(this);

        ProgressDialogHelper.init(getActivity()).showProgress();

        threadInvs.clear();
        presenter.getInvestorThreads(planId, activityContext, 0);
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
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }
}
