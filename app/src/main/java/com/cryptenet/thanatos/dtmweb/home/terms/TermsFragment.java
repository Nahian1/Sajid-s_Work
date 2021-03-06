/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.terms;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TermsFragment extends Fragment {
    public static final String TAG = TagProvider.getDebugTag(TermsFragment.class);
    @BindView(R.id.tv_para1)
    TextView tvPara1;
    @BindView(R.id.tv_para2)
    TextView tvPara2;

    public TermsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_terms, container, false);

        boolean flag = getArguments().getBoolean("flag", false);

        if (flag)
            ((HomeActivity) getActivity()).setToolBarTitle(getString(R.string.nav_tc));

        ButterKnife.bind(this, convertView);

        tvPara1.setText(getString(R.string.lorem));

        tvPara2.setText(getString(R.string.lorem));

        return convertView;
    }
}
