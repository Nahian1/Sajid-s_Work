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
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadProjectFragmentContract;

@PerFragment
public class ThreadProjectFragmentModel extends BaseFragModel<ThreadProjectFragmentContract.Repository>
        implements ThreadProjectFragmentContract.Model {
//    private static final String TAG = TagProvider.getDebugTag(ThreadProjectFragmentModel.class);

    public ThreadProjectFragmentModel(ThreadProjectFragmentContract.Repository repository) {
        super(repository);
    }

    @Override
    public void getInvestorThreads(int threadId, Context context, int offset) {
        repository.getInvestorThreads(threadId, context, offset);
    }
}
