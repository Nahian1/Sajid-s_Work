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
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ProjectAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<ProjectsRsp> projects;
    private List<ProjectsRsp> filteredList;
    private int count = 0;
    private int reqType;
    private InitiatorProjectFilter initiatorProjectFilter;

    public ProjectAdapter(@NonNull Context context, List<ProjectsRsp> projects, int reqType) {
//        super(context, R.layout.initiator_project_list_row, projects);
        this.context = context;
        this.projects = projects;
        this.filteredList = projects;
        this.reqType = reqType;

        getFilter();
    }

    public void updateList(List<ProjectsRsp> projs) {
        this.projects.clear();
        this.projects.addAll(projs);
        this.filteredList.clear();
        this.filteredList.addAll(projs);
        this.notifyDataSetChanged();
    }


    /**
     * Get size of user list
     *
     * @return userList size
     */
    @Override
    public int getCount() {
        return filteredList.size();
    }

    /**
     * Get specific item from user list
     *
     * @param i item index
     * @return list item
     */
    @Override
    public Object getItem(int i) {
        return filteredList.get(i);
    }

    /**
     * Get user list item id
     *
     * @param i item index
     * @return current item id
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.initiator_project_list_row, parent, false);
        TextView titleTV = convertView.findViewById(R.id.titleTV);
        TextView priceTV = convertView.findViewById(R.id.priceTV);
        TextView dateTV = convertView.findViewById(R.id.dateTV);
        TextView statusTV = convertView.findViewById(R.id.statusTV);
        ImageView editIV = convertView.findViewById(R.id.editIV);

        titleTV.setText(filteredList.get(position).getTitle());
        priceTV.setText(String.valueOf(filteredList.get(position).getAccessPrice()));

        String dateInputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String dateOutputPattern = "dd MMM yyyy";

        SimpleDateFormat inputDateFormat = new SimpleDateFormat(dateInputPattern, Locale.getDefault());
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(dateOutputPattern, Locale.getDefault());

        try {
            Date date = inputDateFormat.parse(filteredList.get(position).getCreatedAt());
            dateTV.setText(outputDateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (filteredList.get(position).getIsApproved()) {
            statusTV.setText("Approved");
            statusTV.setBackground(context.getResources().getDrawable(R.drawable.tv_shape_apr));
        } else {
            statusTV.setText("Pending");
            statusTV.setBackground(context.getResources().getDrawable(R.drawable.tv_shape_pnd));
        }

        count++;
        Log.e("project", "getView: " + count);

        return convertView;
    }

    /**
     * Get custom filter
     *
     * @return filter
     */
    @NonNull
    @Override
    public Filter getFilter() {
        if (initiatorProjectFilter == null) {
            initiatorProjectFilter = new InitiatorProjectFilter();
        }

        return initiatorProjectFilter;
    }

    /**
     * Custom filter for initiator project list
     * Filter content in initiator project list according to the search text
     */
    private class InitiatorProjectFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults filterResults = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<ProjectsRsp> tempList = new ArrayList<>();

                // search content in friend list
                for (ProjectsRsp project : projects) {
                    if (project.getTitle().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(project);
                    }
                }

                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = projects.size();
                filterResults.values = projects;
            }

            return filterResults;
        }

        /**
         * Notify about filtered list to ui
         *
         * @param constraint text
         * @param results    filtered result
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (List<ProjectsRsp>) results.values;
//            updateList(filteredList);
            notifyDataSetChanged();
        }
    }
}
