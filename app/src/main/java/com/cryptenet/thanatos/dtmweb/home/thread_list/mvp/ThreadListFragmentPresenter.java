/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.thread_list.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadListFragmentContract;

@PerFragment
public class ThreadListFragmentPresenter
        extends BaseFragPresenter<ThreadListFragmentContract.View, ThreadListFragmentContract.Model>
        implements ThreadListFragmentContract.Presenter {
//    private static final String TAG = TagProvider.getDebugTag(ThreadListFragmentPresenter.class);

    public ThreadListFragmentPresenter(ThreadListFragmentContract.Model model) {
        super(model);
    }

    @Override
    public void getThreadList(Context context, int offset) {
        model.getThreadList(context, offset);
    }
}
