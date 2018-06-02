/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.investor_project;


import android.content.Intent;
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
import com.cryptenet.thanatos.dtmweb.events.SearchEvent;
import com.cryptenet.thanatos.dtmweb.events.ToDetailsFragmentEvent;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.InvestorProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.Plans;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class InvestorProjectFragment extends BaseFragment<InvestorProjectFragmentContract.Presenter>
        implements InvestorProjectFragmentContract.View {
    public static final String TAG = TagProvider.getDebugTag(InvestorProjectFragment.class);
    private List<Plans> projectsRspList;
    private ListView projectLV;
    private ProjectManageAdapter adapter;
    private int reqType;
    private Unbinder unbinder;

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


//        projectLV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), MessageRequestActivity.class);
//                  startActivity(intent);
//            }
//        });

        projectLV.setOnItemClickListener((parent, view, position, id) ->
                EventBus.getDefault().post(
                        new ToDetailsFragmentEvent(
                                projectsRspList.get(position).getPlan(),
                                (projectsRspList.get(position).getIsApproved()) ? 11 : 10)));

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
        Log.d(TAG, "onProjectListReceiveEvent: login");
        this.projectsRspList = event.projectsRspList;
        adapter.updateList(this.projectsRspList);
    }

    @Override
    public void onResume() {
        super.onResume();

//        presenter.attachView(this);

        presenter.getMyProjectList(reqType,activityContext);
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
