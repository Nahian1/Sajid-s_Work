/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.thread_project.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadProjectFragmentContract;

@PerFragment
public class ThreadProjectFragmentPresenter extends BaseFragPresenter<ThreadProjectFragmentContract.View, ThreadProjectFragmentContract.Model>
        implements ThreadProjectFragmentContract.Presenter {
//    private static final String TAG = TagProvider.getDebugTag(ThreadProjectFragmentPresenter.class);

    public ThreadProjectFragmentPresenter(ThreadProjectFragmentContract.Model model) {
        super(model);
    }

    @Override
    public void getInvestorThreads(int threadId, Context context, int offset) {
        model.getInvestorThreads(threadId, context, offset);
    }
}
