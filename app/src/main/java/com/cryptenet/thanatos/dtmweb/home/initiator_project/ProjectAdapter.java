/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.initiator_project;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.pojo.Projects;

import java.util.List;

/**
 * Created by Mobile App on 2/9/2018.
 */

public class ProjectAdapter extends ArrayAdapter<Projects> {
    private Context context;
    private List<Projects> projects;
    private int count = 0;
    public ProjectAdapter(@NonNull Context context, List<Projects> projects) {
        super(context, R.layout.initiator_project_list_row, projects);
        this.context = context;
        this.projects = projects;
    }

    public void updateList(List<Projects> projs){
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
        dateTV.setText(projects.get(position).getCreatedAt());
        if(projects.get(position).getIsApproved() != null){
            statusTV.setText("Approved");
            statusTV.setBackgroundColor(Color.GREEN);
        }
        else{
            statusTV.setText("Pending");
        }
        //statusTV.setText(projects.get(position).getStatus());
        Glide.with(context)
                .load(projects.get(position).getCoverThumbnail())
                .apply(RequestOptions.placeholderOf(R.drawable.ppimg))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(editIV);
        count++;
        Log.e("project", "getView: "+count);

        return convertView;

    }
}
