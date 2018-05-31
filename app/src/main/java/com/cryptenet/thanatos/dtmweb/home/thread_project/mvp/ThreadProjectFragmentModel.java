/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.thread_project.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class ThreadProjectFragmentModel extends BaseFragModel<ThreadProjectFragmentContract.Repository>
        implements ThreadProjectFragmentContract.Model {
    private static final String TAG = TagProvider.getDebugTag(ThreadProjectFragmentModel.class);

    public ThreadProjectFragmentModel(ThreadProjectFragmentContract.Repository repository) {
        super(repository);
    }

    @Override
    public void getInvestorThreads(int threadId, Context context) {
        repository.getInvestorThreads(threadId, context);
    }
}
