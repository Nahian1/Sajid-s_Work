/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.initiator_project;

import android.annotation.SuppressLint;
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
import com.cryptenet.thanatos.dtmweb.viewholders.VHInitiatorProjectList;
import com.cryptenet.thanatos.dtmweb.viewholders.VHInitiatorProjectManageReqList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ProjectManageReqAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<Plans> projects = new ArrayList<>();
    private List<Plans> filteredList = new ArrayList<>();
    private int count = 0;
    private int reqType;
    private LayoutInflater inflater;
    private InitiatorProjectFilter initiatorProjectFilter;

    public ProjectManageReqAdapter(@NonNull Context context, List<Plans> projects, int reqType) {
//        super(context, R.layout.initiator_project_list_row, projects);
        this.context = context;
        this.projects = projects;
        this.filteredList = projects;
        this.reqType = reqType;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        getFilter();
    }

    public void updateList(List<Plans> projs) {
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

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        VHInitiatorProjectManageReqList vhInitiatorProjectManageReqList;

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.initiator_project_list_row, parent, false);

            vhInitiatorProjectManageReqList = new VHInitiatorProjectManageReqList();

            vhInitiatorProjectManageReqList.titleTV = convertView.findViewById(R.id.titleTV);
            vhInitiatorProjectManageReqList.priceTV = convertView.findViewById(R.id.priceTV);
            vhInitiatorProjectManageReqList.dateTV = convertView.findViewById(R.id.dateTV);
            vhInitiatorProjectManageReqList.statusTV = convertView.findViewById(R.id.statusTV);
            vhInitiatorProjectManageReqList.editIV = convertView.findViewById(R.id.editIV);

        } else {

            vhInitiatorProjectManageReqList = (VHInitiatorProjectManageReqList) convertView.getTag();

        }

        vhInitiatorProjectManageReqList.setData(context, filteredList.get(position), reqType);

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
