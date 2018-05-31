/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.investor_project;

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
import com.cryptenet.thanatos.dtmweb.pojo.Plans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProjectManageAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<Plans> projects = new ArrayList<>();
    private List<Plans> filteredList = new ArrayList<>();
    private int count = 0;
    private int reqType;
    private InitiatorProjectFilter initiatorProjectFilter;

    public ProjectManageAdapter(@NonNull Context context, List<Plans> projects, int reqType) {
//        super(context, R.layout.initiator_project_list_row, projects);
        this.context = context;
        this.projects = projects;
        this.filteredList = projects;
        this.reqType = reqType;

        getFilter();
    }

    public void updateList(List<Plans> projs) {
        this.projects.clear();
        this.filteredList.clear();
        this.projects.addAll(projs);
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

        editIV.setVisibility(View.GONE);

        if (reqType == 2)
            statusTV.setVisibility(View.GONE);

        titleTV.setText(filteredList.get(position).getPlanTitle());
        priceTV.setText(String.valueOf(filteredList.get(position).getPlanAccessPrice()));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        try {
            dateTV.setText(dateFormat.format(dateFormat.parse(filteredList.get(position).getCreatedAt())));
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

        //statusTV.setText(filteredList.get(position).getStatus());
        if (reqType != 1)
            editIV.setVisibility(View.GONE);

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
                List<Plans> tempList = new ArrayList<>();

                // search content in friend list
                for (Plans project : projects) {
                    if (project.getPlanTitle().toLowerCase().contains(constraint.toString().toLowerCase())) {
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
            filteredList = (List<Plans>) results.values;
//            updateList(filteredList);
            notifyDataSetChanged();
        }
    }
}
