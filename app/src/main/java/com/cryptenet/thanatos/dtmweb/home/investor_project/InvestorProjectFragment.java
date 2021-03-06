/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.investor_project;


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
import com.cryptenet.thanatos.dtmweb.events.ManageProjectReceiveEvent;
import com.cryptenet.thanatos.dtmweb.events.RequestDetailFragmentEvent;
import com.cryptenet.thanatos.dtmweb.events.RequestFailureEvent;
import com.cryptenet.thanatos.dtmweb.events.SearchEvent;
import com.cryptenet.thanatos.dtmweb.events.ToDetailsFragmentEvent;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.InvestorProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.Plans;
import com.cryptenet.thanatos.dtmweb.utils.ProgressDialogHelper;
import com.cryptenet.thanatos.dtmweb.utils.ViewUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class InvestorProjectFragment extends BaseFragment<InvestorProjectFragmentContract.Presenter>
        implements InvestorProjectFragmentContract.View {
//    public static final String TAG = TagProvider.getDebugTag(InvestorProjectFragment.class);
    private List<Plans> projectsRspList;
    private ListView projectLV;
    private ProjectManageAdapter adapter;
    private int reqType;
    private Unbinder unbinder;
    private boolean doMoreRequest;
    private boolean moreDataAvailable;

    public InvestorProjectFragment() {
        projectsRspList = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_investor_project, container, false);

        ((HomeActivity) getActivity()).hideSearchBar(false);

        unbinder = ButterKnife.bind(this, convertView);

        reqType = getArguments().getInt("reqType");

        projectLV = convertView.findViewById(R.id.projectListView);
//        adapter = new ProjectAdapter(activityContext, INVPlanGenerator.getList(), reqType); //test search with dummy data
        adapter = new ProjectManageAdapter(activityContext, projectsRspList, reqType);
        projectLV.setAdapter(adapter);

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

        projectLV.setOnItemClickListener((parent, view, position, id) -> {

            if (reqType == 1) {

                ViewUtils.hideKeyboard(getActivity());
                EventBus.getDefault().post(new ToDetailsFragmentEvent(projectsRspList.get(position).getPlan(), 11));

            } else {

                ViewUtils.hideKeyboard(getActivity());
                EventBus.getDefault().post(new RequestDetailFragmentEvent(projectsRspList.get(position).getId()));
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
    public void onSearchEvent(SearchEvent event) {

        adapter.getFilter().filter(event.searchTxt);

    }

    @Subscribe
    public void onManageProjectReceiveEvent(ManageProjectReceiveEvent event) {
//        Log.d(TAG, "onProjectListReceiveEvent: login");

        ProgressDialogHelper.hideProgress();

        if (event.projectsRspList.isEmpty())
            moreDataAvailable = false;
        else
            moreDataAvailable = true;

        if (this.projectsRspList.size() == 0)
            this.projectsRspList = event.projectsRspList;
        else if (doMoreRequest)
            this.projectsRspList.addAll(event.projectsRspList);

        if (this.projectsRspList != null)
            adapter.updateList(this.projectsRspList);
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

        projectsRspList.clear();
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
