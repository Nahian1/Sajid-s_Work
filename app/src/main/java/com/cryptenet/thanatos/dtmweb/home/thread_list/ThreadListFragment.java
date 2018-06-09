/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.thread_list;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.events.DistinctThreadsReceived;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadListFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ThreadIdentity;
import com.cryptenet.thanatos.dtmweb.utils.ProgressDialogHelper;
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
    private boolean doMoreRequest;
    private boolean moreDataAvailable;

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

        ((HomeActivity) getActivity()).setToolBarTitle(getString(R.string.thread_list));

        unbinder = ButterKnife.bind(this, convertView);
        reqType = getArguments().getInt("reqType");

        adapter = new ThreadListAdapter(activityContext, threadIdentities, reqType);
//        lvThreadList = convertView.findViewById(R.id.lv_thread_list);
        lvThreadList.setAdapter(adapter);

        lvThreadList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                doMoreRequest = true;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                final int lastItem = firstVisibleItem + visibleItemCount;

                if(lastItem == totalItemCount && totalItemCount > 0) {
                    if (moreDataAvailable && doMoreRequest) {
                        presenter.getThreadList(activityContext, totalItemCount);
                        doMoreRequest = false;
                    }
                }
            }
        });

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

        ProgressDialogHelper.hideProgress();

        if (event.threadIdentities.isEmpty())
            moreDataAvailable = false;
        else
            moreDataAvailable = true;

        if (this.threadIdentities.size() == 0)
            this.threadIdentities = event.threadIdentities;
        else if (doMoreRequest)
            this.threadIdentities.addAll(event.threadIdentities);

        if (this.threadIdentities != null)
            adapter.updateList(this.threadIdentities);
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.attachView(this);

        ProgressDialogHelper.init(getActivity()).showProgress();

        threadIdentities.clear();

        presenter.getThreadList(activityContext, 0);
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
