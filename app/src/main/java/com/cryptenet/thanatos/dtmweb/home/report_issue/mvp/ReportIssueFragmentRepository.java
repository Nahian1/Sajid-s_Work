/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.report_issue.mvp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.events.IssueListReceiveEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ReportIssueFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.IssueParent;
import com.cryptenet.thanatos.dtmweb.pojo.IssueResponse;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@PerFragment
public class ReportIssueFragmentRepository extends BaseFragRepository
        implements ReportIssueFragmentContract.Repository {
    private static String TAG = TagProvider.getDebugTag(ReportIssueFragmentRepository.class);
    private List<IssueParent> issueParents;

    @Override
    public void getAllIssues(Context context) {
        Call<IssueResponse> req = apiClient.getAllIssues("Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN,null));
        req.enqueue(new Callback<IssueResponse>() {
            @Override
            public void onResponse(Call<IssueResponse> call, Response<IssueResponse> response) {
                IssueResponse issueResponse = response.body();
                assert issueResponse != null;
                setAllIssues(issueResponse.getResults());
            }

            @Override
            public void onFailure(Call<IssueResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: AllReqPlansResponse");
            }
        });
    }

    private void setAllIssues(List<IssueParent> issueParents) {
        this.issueParents = issueParents;
        EventBus.getDefault().post(new IssueListReceiveEvent(this.issueParents));
    }
}
