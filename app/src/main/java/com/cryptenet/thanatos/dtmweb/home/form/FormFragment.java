/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.form;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.events.FormSubmitEvent;
import com.cryptenet.thanatos.dtmweb.events.RequestFailureEvent;
import com.cryptenet.thanatos.dtmweb.events.TransactionSuccessEvent;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.FormFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsDetailed;
import com.cryptenet.thanatos.dtmweb.pojo.Transaction;
import com.cryptenet.thanatos.dtmweb.utils.ProgressDialogHelper;
import com.cryptenet.thanatos.dtmweb.utils.ViewUtils;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FormFragment extends BaseFragment<FormFragmentContract.Presenter>
        implements FormFragmentContract.View {
    public static final String TAG = TagProvider.getDebugTag(FormFragment.class);

    Unbinder unbinder;
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
    @BindView(R.id.bankNameET)
    EditText bankNameET;
    @BindView(R.id.bankAccNameET)
    EditText bankAccNameET;
    @BindView(R.id.bankAccNumET)
    EditText bankAccNumET;
    @BindView(R.id.transIdET)
    EditText transIdET;
    @BindView(R.id.add_report)
    EditText addReport;


    private ProjectsDetailed details;


    public FormFragment() {
        // Required empty public constructor
    }


    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction_form, container, false);

        ((HomeActivity) getActivity()).setToolBarTitle(getString(R.string.req_details));


        unbinder = ButterKnife.bind(this, view);

        details = new Gson().fromJson(getArguments().getString("project_details"), ProjectsDetailed.class);

        if (details != null && details.getId() != null && details.getId() > 0) {
            textViewTitle.setText(details.getTitle());
            price.setText(getString(R.string.price) + " " +
                    String.format("%.2f", (Double.parseDouble(details.getMinimumInvestmentCost()) - Double.parseDouble(details.getMaximumInvestmentCost()))));

            String dateInputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
            String dateOutputPattern = "dd MMM yyyy";

            SimpleDateFormat inputDateFormat = new SimpleDateFormat(dateInputPattern, Locale.getDefault());
            SimpleDateFormat outputDateFormat = new SimpleDateFormat(dateOutputPattern, Locale.getDefault());

            try {
                Date datef = inputDateFormat.parse(details.getCreatedAt());
                date.setText(outputDateFormat.format(datef));
            } catch (ParseException e) {
                e.printStackTrace();
            }

//        status.setText(details.getTitle());

            Glide.with(activityContext)
                    .load(details.getInitiatorImage())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_profile_grey))
                    .apply(RequestOptions.circleCropTransform())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(profilepic);

            textViewName.setText(details.getInitiatorsName());
//        textViewType.setText(details.getInitiator());
            address.setText(details.getInitiatorAddress());
        }
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
    public void onResume() {
        super.onResume();

        presenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cancelBtn, R.id.submitBtn})
    public void onViewClicked(View view) {

        ViewUtils.hideKeyboard(getActivity());

        switch (view.getId()) {
            case R.id.cancelBtn:

                getFragmentManager().popBackStackImmediate();
                break;

            case R.id.submitBtn:

                String bankName = bankNameET.getText().toString().trim();
                String bankAccName = bankAccNameET.getText().toString().trim();
                String bankAccNo = bankAccNumET.getText().toString().trim();
                String transId = transIdET.getText().toString().trim();
                String note = addReport.getText().toString().trim();

                if (!bankName.isEmpty() && !bankAccName.isEmpty() && !bankAccNo.isEmpty() && !transId.isEmpty()) {

                    ProgressDialogHelper.init(getActivity()).showProgress();

                    Transaction transaction = new Transaction();
                    transaction.setBankName(bankName);
                    transaction.setBankAccountName(bankAccName);
                    transaction.setBankAccountNumber(bankAccNo);
                    transaction.setTransactionId(transId);
                    transaction.setNote(note);
                    transaction.setProjectsDetailed(details); //adding project details

                    //for debug only
//                    Transaction transaction = new Transaction();
//                    transaction.setBankName("Bank");
//                    transaction.setBankAccountName("My name");
//                    transaction.setBankAccountNumber("1324657987");
//                    transaction.setTransactionId("313546313");
//                    transaction.setNote("note");
//                    transaction.setProjectsDetailed(details); //adding project details

                    presenter.submitTransactionData(transaction, activityContext);

                } else {

                    showMessage("Please fill up all mandatory fields!");
                }

                break;

        }
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRequestFailureEvent(RequestFailureEvent event) {

        ProgressDialogHelper.hideProgress();

        showMessage("Couldn't submit data, try again!");
    }

}
