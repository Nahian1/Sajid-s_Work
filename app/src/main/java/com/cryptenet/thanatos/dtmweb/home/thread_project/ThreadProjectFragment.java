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
import com.cryptenet.thanatos.dtmweb.events.RequestFailureEvent;
import com.cryptenet.thanatos.dtmweb.events.ThreadProjectListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ThreadInv;
import com.cryptenet.thanatos.dtmweb.utils.ProgressDialogHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class ThreadProjectFragment extends BaseFragment<ThreadProjectFragmentContract.Presenter>
        implements ThreadProjectFragmentContract.View {
//    public static final String TAG = TagProvider.getDebugTag(ThreadProjectFragment.class);

    private int planId;
    private RecyclerView mRecyclerView;
    private InitiatorThreadAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ThreadInv> threadInvs;

    private boolean doMoreRequest;
    private boolean moreDataAvailable;

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    public ThreadProjectFragment() {
        this.threadInvs = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_thread_project, container, false);

        ((HomeActivity) getActivity()).setToolBarTitle(getString(R.string.nav_conversation));

        planId = getArguments().getInt("plan_id");

        mRecyclerView = convertView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new InitiatorThreadAdapter(this.threadInvs);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                doMoreRequest = true;
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) == totalItemCount && totalItemCount > 0) {
                            loading = false;
                            Log.v("lastitemcheck", "Last Item Wow !");
                            showMessage(String.valueOf(totalItemCount) + "\t" + String.valueOf(visibleItemCount + pastVisiblesItems));
                            if (moreDataAvailable && doMoreRequest) {
                                presenter.getInvestorThreads(planId, activityContext, totalItemCount);
                                doMoreRequest = false;
                            }
                        }
                    }
                }
            }
        });

        return convertView;
    }

    @Subscribe
    public void onThreadProjectListReceive(ThreadProjectListReceiveEvent event) {

        ProgressDialogHelper.hideProgress();

        if (event.threadInvs.isEmpty())
            moreDataAvailable = false;
        else
            moreDataAvailable = true;

        if (this.threadInvs.size() == 0)
            this.threadInvs = event.threadInvs;
        else if (doMoreRequest)
            this.threadInvs.addAll(event.threadInvs);

        if (this.threadInvs != null) {
            mAdapter.setData(this.threadInvs);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRequestFailureEvent(RequestFailureEvent event) {

        ProgressDialogHelper.hideProgress();

        showMessage("Couldn't fetch data, try again!");
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
