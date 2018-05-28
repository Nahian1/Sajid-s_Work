/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.initiator_project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.events.ToEditPlanEvent;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mobile App on 2/9/2018.
 */

public class ProjectAdapter extends ArrayAdapter<ProjectsRsp> {
    private Context context;
    private List<ProjectsRsp> projects;
    private int count = 0;
    private int reqType;

    public ProjectAdapter(@NonNull Context context, List<ProjectsRsp> projects, int reqType) {
        super(context, R.layout.initiator_project_list_row, projects);
        this.context = context;
        this.projects = projects;
        this.reqType = reqType;
    }

    public void updateList(List<ProjectsRsp> projs){
        this.projects.clear();
        this.projects.addAll(projs);
        this.notifyDataSetChanged();

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.initiator_project_list_row,parent,false);
        TextView titleTV = convertView.findViewById(R.id.titleTV);
        TextView priceTV = convertView.findViewById(R.id.priceTV);
        TextView dateTV = convertView.findViewById(R.id.dateTV);
        TextView statusTV = convertView.findViewById(R.id.statusTV);
        ImageView editIV = convertView.findViewById(R.id.editIV);

        titleTV.setText(projects.get(position).getTitle());
        priceTV.setText(projects.get(position).getAccessPrice());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        try {
            dateTV.setText(dateFormat.format(dateFormat.parse(projects.get(position).getCreatedAt())));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(projects.get(position).getIsApproved()){
            statusTV.setText("Approved");
            statusTV.setBackground(context.getResources().getDrawable(R.drawable.tv_shape_apr));
        }
        else{
            statusTV.setText("Pending");
            statusTV.setBackground(context.getResources().getDrawable(R.drawable.tv_shape_pnd));
        }
        //statusTV.setText(projects.get(position).getStatus());
        if (reqType != 1)
            editIV.setVisibility(View.GONE);
        count++;
        Log.e("project", "getView: "+count);

        editIV.setOnClickListener(v -> {
            ProjectsRsp pro = projects.get(position);
            pro.setEditMode(true);

            EventBus.getDefault().post(new ToEditPlanEvent(pro));
        });

        return convertView;

    }
}
