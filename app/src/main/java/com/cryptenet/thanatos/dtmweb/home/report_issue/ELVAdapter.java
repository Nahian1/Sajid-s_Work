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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.pojo.IssueParent;

import java.util.List;

public class ELVAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<IssueParent> issueParents;

    public ELVAdapter(Context context, List<IssueParent> issueParents) {
        this.context = context;
        this.issueParents = issueParents;
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
        String parentTitle = issueParents.get(groupPosition).getName();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.node_elv_parent, parent, false);
        }
        TextView header_text = convertView.findViewById(R.id.tv_elv_parent);

        header_text.setText(parentTitle);

        if (isExpanded) {
            header_text.setTypeface(null, Typeface.BOLD);
            header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    R.drawable.ic_right_arrow, 0);
        } else {
            // If group is not expanded then change the text back into normal
            // and change the icon

            header_text.setTypeface(null, Typeface.NORMAL);
            header_text.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    R.drawable.ic_down_arrow, 0);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = issueParents.get(groupPosition).getTopics().get(childPosition).getName();

        // Inflating child layout and setting textview
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.node_elv_child, parent, false);
        }
        TextView child_text = (TextView) convertView.findViewById(R.id.child_text);

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
