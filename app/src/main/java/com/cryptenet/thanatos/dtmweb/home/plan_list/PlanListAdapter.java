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

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;
import com.cryptenet.thanatos.dtmweb.viewholders.VHProjectList;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class PlanListAdapter extends ArrayAdapter<ProjectsRsp> {
    private Context context;
    private LayoutInflater inflater;
    private List<ProjectsRsp> projects;
//    private ItemClickListener itemClickListener;

    public PlanListAdapter(@NonNull Context context, List<ProjectsRsp> projects) {
        super(context, R.layout.plan_list_row, projects);
        this.context = context;
        this.projects = projects;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateList(List<ProjectsRsp> projectsRsps) {
        this.projects.clear();

        if (projectsRsps != null)
            this.projects.addAll(projectsRsps);

        this.notifyDataSetChanged();
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        VHProjectList vhProjectList;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.plan_list_row, parent, false);

            vhProjectList = new VHProjectList();

            vhProjectList.projectIV = convertView.findViewById(R.id.projectImg);
            vhProjectList.nameTV = convertView.findViewById(R.id.nameTV);
            vhProjectList.dateTV = convertView.findViewById(R.id.dateTV);
            vhProjectList.titleTV = convertView.findViewById(R.id.titleTV);
            vhProjectList.priceTV = convertView.findViewById(R.id.priceTV);

            convertView.setTag(vhProjectList);
        } else
            vhProjectList = (VHProjectList) convertView.getTag();

        vhProjectList.setProjectData(context, projects.get(position));

        convertView.findViewById(R.id.viewProject).setOnClickListener(view -> EventBus.getDefault().post(String.valueOf(position)));

        return convertView;
    }
}
