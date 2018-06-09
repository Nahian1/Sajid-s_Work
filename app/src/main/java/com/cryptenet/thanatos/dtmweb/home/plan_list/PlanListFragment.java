/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.plan_list;


import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.events.ProjectListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.events.SearchEvent;
import com.cryptenet.thanatos.dtmweb.events.ToDetailsFragmentEvent;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanListFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;
import com.cryptenet.thanatos.dtmweb.utils.ProgressDialogHelper;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
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
    private boolean doMoreRequest;
    private boolean moreDataAvailable;

    public PlanListFragment() {
        projectsRspList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_plan_list, container, false);

        ((HomeActivity) getActivity()).hideSearchBar(false);

        adapter = new PlanListAdapter(activityContext, projectsRspList);
        projectLV = convertView.findViewById(R.id.projectListPlan);
        projectLV.setAdapter(adapter);
        projectLV.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                doMoreRequest = true;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                final int lastItem = firstVisibleItem + visibleItemCount;

                if(lastItem == totalItemCount && totalItemCount > 0) {
                    if (moreDataAvailable && doMoreRequest) {
                        presenter.getProjectList(activityContext, totalItemCount);
                        doMoreRequest = false;
                    }
                }
            }
        });

//        token = getArguments().getString("token");
        token = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(ConstantProvider.SP_ACCESS_TOKEN, null);
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
        projectsRspList.clear();
        if (type == 1) {
            EventBus.getDefault().post(new ToDetailsFragmentEvent(projectsRsp.getId(), 10));
        } else {
            EventBus.getDefault().post(new ToDetailsFragmentEvent(projectsRsp.getId(), 20));
        }
    }

    @Subscribe
    public void onProjectListReceiveEvent(ProjectListReceiveEvent event) {

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

    @Subscribe
    public void onItemClickEvent(String clickPosition) {
        presenter.checkUserType(projectsRspList.get(Integer.parseInt(clickPosition)), activityContext);

    }

    @Subscribe
    public void onSearchEvent(SearchEvent event) {
        if (event.searchTxt.isEmpty())
            presenter.getProjectList(activityContext, 0);
        else
            presenter.searchMyPlans(activityContext, token, event.searchTxt);

    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.attachView(this);

        ProgressDialogHelper.init(getActivity()).showProgress();

        presenter.getProjectList(activityContext, 0);
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
