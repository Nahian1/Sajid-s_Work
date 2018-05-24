/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.plan_list;


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
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanListFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

public class PlanListFragment extends BaseFragment<PlanListFragmentContract.Presenter>
        implements PlanListFragmentContract.View {
    public static final String TAG = TagProvider.getDebugTag(PlanListFragment.class);


    public PlanListFragment() {
        // Required empty public constructor
    }

    private ListView projectLV;
    private ProjectAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_plan_list, container, false);
        adapter = new ProjectAdapter(activityContext, ProjectListGenerator.generateProjects());
        projectLV =  convertView.findViewById(R.id.projectListView);
        projectLV.setAdapter(adapter);

        projectLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(activityContext, "position: "+ProjectListGenerator.generateProjects().get(position), Toast.LENGTH_SHORT).show();

                /*startActivity(new Intent(MainActivity.this,DetailsActivity.class)
                .putExtra("pos",position));*/
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
}
