/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.transaction;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.TransactionFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsDetailed;
import com.cryptenet.thanatos.dtmweb.pojo.Transaction;
import com.cryptenet.thanatos.dtmweb.utils.JsonKeys;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class TransactionFragment extends BaseFragment<TransactionFragmentContract.Presenter>
        implements TransactionFragmentContract.View {
    public static final String TAG = TagProvider.getDebugTag(TransactionFragment.class);

    @BindView(R.id.textViewTitle)
    TextView textViewTitle;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.profilepic)
    ImageView profilepic;
    @BindView(R.id.textViewName)
    TextView textViewName;
    @BindView(R.id.textViewType)
    TextView textViewType;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.textBankName)
    TextView textBankName;
    @BindView(R.id.textAccountName)
    TextView textAccountName;
    @BindView(R.id.textAccountNumber)
    TextView textAccountNumber;
    @BindView(R.id.textTransactionId)
    TextView textTransactionId;
    @BindView(R.id.noteTV)
    TextView noteTV;
    @BindView(R.id.doneBtn)
    Button doneBtn;
    Unbinder unbinder;

    Transaction transactionData;
    ProjectsDetailed projectData;

    public TransactionFragment() {
        // Required empty public constructor
    }


    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction_detail, container, false);

        unbinder = ButterKnife.bind(this, view);

        transactionData = new Gson().fromJson(getArguments().getString(JsonKeys.TRANSACTION_DETAILS), Transaction.class);

        projectData = transactionData.getProjectsDetailed();

        textViewTitle.setText(projectData.getTitle());

        price.setText("Price: " +
                String.format("%.2f", (Double.parseDouble(projectData.getMinimumInvestmentCost())
                        - Double.parseDouble(projectData.getMaximumInvestmentCost()))));

//        date.setText(projectData.getTitle());
//        status.setText(projectData.getTitle());

        Glide.with(activityContext)
                .load(projectData.getInitiatorImage())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_pp_dummy))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(profilepic);

        textViewName.setText(projectData.getInitiatorsName());
//        textViewType.setText(projectData.getTitle());
        address.setText(projectData.getInitiatorAddress());

        textBankName.setText(transactionData.getBankName());
        textAccountName.setText(transactionData.getBankAccountName());
        textAccountNumber.setText(transactionData.getBankAccountNumber());
        textTransactionId.setText(transactionData.getTransactionId());
        noteTV.setText(transactionData.getNote());

        return view;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.doneBtn)
    public void onViewClicked() {

        showMessage("done clicked");
    }
}
