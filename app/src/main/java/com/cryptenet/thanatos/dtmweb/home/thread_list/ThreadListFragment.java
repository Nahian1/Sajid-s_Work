/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.thread_list;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.events.DistinctThreadsReceived;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadListFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ThreadIdentity;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ThreadListFragment extends BaseFragment<ThreadListFragmentContract.Presenter>
        implements ThreadListFragmentContract.View {
    public static final String TAG = TagProvider.getDebugTag(ThreadListFragment.class);
    private List<ThreadIdentity> threadIdentities;
    private ThreadListAdapter adapter;
    private int reqType;

    @BindView(R.id.lv_thread_list)
    ListView lvThreadList;
    Unbinder unbinder;

    public ThreadListFragment() {
        threadIdentities = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_thread_list, container, false);

        ((HomeActivity) getActivity()).hideSearchBar(true);

        unbinder = ButterKnife.bind(this, convertView);
        reqType = getArguments().getInt("reqType");

        adapter = new ThreadListAdapter(activityContext, threadIdentities, reqType);
//        lvThreadList = convertView.findViewById(R.id.lv_thread_list);
        lvThreadList.setAdapter(adapter);


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
    public void onDistinctThreadsReceived(DistinctThreadsReceived event) {
        this.threadIdentities = event.threadIdentities;
        adapter.updateList(this.threadIdentities);
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.attachView(this);
        presenter.getThreadList(activityContext);
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
        unbinder.unbind();
    }
}
