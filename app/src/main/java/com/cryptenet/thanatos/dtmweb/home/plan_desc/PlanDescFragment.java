/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.plan_desc;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.events.PlanDetailsRequestEvent;
import com.cryptenet.thanatos.dtmweb.events.ShowPlanDetailsEvent;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanDescFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsDetailed;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;


public class PlanDescFragment extends BaseFragment<PlanDescFragmentContract.Presenter>
        implements PlanDescFragmentContract.View {
    public static final String TAG = TagProvider.getDebugTag(PlanDescFragment.class);
    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.textDatePrice)
    TextView textDatePrice;
    @BindView(R.id.demoImg)
    ImageView demoImg;
    @BindView(R.id.profilepic)
    CircleImageView profilepic;
    @BindView(R.id.textViewName)
    TextView textViewName;
    @BindView(R.id.textViewType)
    TextView textViewType;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.textShortDesc)
    TextView textShortDesc;
    @BindView(R.id.textLongDesc)
    TextView textLongDesc;
    Unbinder unbinder;
    @BindView(R.id.ic_init_conversation)
    ImageView icInitConversation;
    @BindView(R.id.textViewFile)
    TextView textViewFile;
    @BindView(R.id.layoutBankSection)
    LinearLayout layoutBankSection;

    //    @BindView(R.id.profilepic)
//    ImageView profileIV;
    @BindView(R.id.textBankAccName)
    TextView textBankAccName;
    @BindView(R.id.textBankAccNo)
    TextView textBankAccNo;
    @BindView(R.id.textBankName)
    TextView textBankName;
    @BindView(R.id.amountToBePaid)
    TextView amountToBePaid;

    private int projectId, type;
    private ProjectsDetailed projectsDetailed;

    public PlanDescFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_plan_desc, container, false);

        projectId = getArguments().getInt("project_id");
        type = getArguments().getInt("type");

        unbinder = ButterKnife.bind(this, convertView);

        return convertView;
    }

    @OnClick(R.id.buttonRequestDetails)
    public void buttonRequestDetails() {

        if (type == 3) {

            showMessage("Log in as Investor!");

        } else {

            EventBus.getDefault().post(new PlanDetailsRequestEvent(projectsDetailed));
        }
    }

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void toShowPlanDetailsEvent(ShowPlanDetailsEvent event) {

        projectsDetailed = event.detailed;

        textTitle.setText(event.detailed.getTitle());

        textViewName.setText(event.detailed.getInitiatorsName());
        address.setText(event.detailed.getInitiatorAddress());
        textShortDesc.setText(event.detailed.getShortDescription());

        textBankAccName.setText(event.detailed.getBankAccountName());
        textBankAccNo.setText(event.detailed.getBankAccountNumber());
        textBankName.setText(event.detailed.getBankName());
        amountToBePaid.setText(event.detailed.getAccessPrice());

        //commented out for later use
//        Glide.with(activityContext)
//                .load(event.detailed.getCover())
//                .apply(RequestOptions.placeholderOf(R.drawable.imgdemo))
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .into(demoImg);
//
//        Glide.with(activityContext)
//                .load(event.detailed.getInitiatorImage())
//                .apply(RequestOptions.placeholderOf(R.drawable.img_initiator_profile_picture))
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .into(profileIV);

//        if (type == 1) {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
//            try {
//                textDatePrice.setText(dateFormat.format(dateFormat.parse(event.detailed.getCreatedAt())));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            layoutBankSection.setVisibility(View.GONE);
//            textLongDesc.setVisibility(View.VISIBLE);
//            textLongDesc.setText(event.detailed.getLongDescription());
//
//            textViewFile.setVisibility(View.VISIBLE);
//        } else {
        textDatePrice.setText("Price: " + event.detailed.getMinimumInvestmentCost() + " - " + event.detailed.getMaximumInvestmentCost());
//        }

        if (type != 3) {
            icInitConversation.setVisibility(View.VISIBLE);
        }


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

    @OnClick({R.id.ic_init_conversation, R.id.textViewFile})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_init_conversation:
                break;
            case R.id.textViewFile:
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            EventBus.getDefault().register(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            EventBus.getDefault().register(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.attachView(this);

//        if (type == 1) { //long
//            presenter.getLongDetails(activityContext, projectId);
//        } else { //short
        presenter.getShortDetails(activityContext, projectId);
//        }
    }

    @Override
    public void onStop() {
        presenter.detachView();

        super.onStop();
    }
}
