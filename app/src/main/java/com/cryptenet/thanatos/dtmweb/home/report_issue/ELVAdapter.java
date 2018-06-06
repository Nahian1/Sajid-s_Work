/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.report_issue;

import android.content.Context;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.pojo.IssueParent;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;

import java.util.List;

public class ELVAdapter extends BaseExpandableListAdapter {
    private Context context;
    private String locale;
    private List<IssueParent> issueParents;

    public ELVAdapter(Context context, List<IssueParent> issueParents) {
        this.context = context;
        this.issueParents = issueParents;
        this.locale = PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SELECTED_LANGUAGE, "en");
    }

    @Override
    public int getGroupCount() {
        return this.issueParents.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.issueParents.get(groupPosition).getTopics().size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.issueParents.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.issueParents.get(groupPosition).getTopics().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return this.issueParents.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return this.issueParents.get(groupPosition).getTopics().get(childPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String parentTitle;
        if (locale.equals("en"))
            parentTitle = issueParents.get(groupPosition).getName();
        else
            parentTitle = issueParents.get(groupPosition).getNameAr();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.node_elv_parent, parent, false);
        }
        TextView header_text = convertView.findViewById(R.id.tv_elv_parent);

        header_text.setText(parentTitle);

        if (isExpanded) {
            header_text.setTypeface(null, Typeface.BOLD);
            if (locale.equals("en"))
                header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    R.drawable.ic_right_arrow, 0);
            else
                header_text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_right_arrow, 0,
                        0, 0);
        } else {
            // If group is not expanded then change the text back into normal
            // and change the icon

            header_text.setTypeface(null, Typeface.NORMAL);
            if (locale.equals("en"))
                header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                        R.drawable.ic_down_arrow, 0);
            else
                header_text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_down_arrow, 0,
                        0, 0);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childText;
        if (locale.equals("en"))
            childText = issueParents.get(groupPosition).getTopics().get(childPosition).getName();
        else
            childText = issueParents.get(groupPosition).getTopics().get(childPosition).getNameAr();

        // Inflating child layout and setting textview
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.node_elv_child, parent, false);
        }
        TextView child_text = convertView.findViewById(R.id.child_text);

        child_text.setText(childText);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void updateList(List<IssueParent> issueParents) {
        this.issueParents.clear();

        if (issueParents != null)
            this.issueParents = issueParents;

        this.notifyDataSetChanged();
    }
}
