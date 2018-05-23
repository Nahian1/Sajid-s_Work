/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.thread_list.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadListFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class ThreadListFragmentPresenter extends BaseFragPresenter<ThreadListFragmentContract.View, ThreadListFragmentContract.Model>
        implements ThreadListFragmentContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(ThreadListFragmentPresenter.class);

    public ThreadListFragmentPresenter(ThreadListFragmentContract.Model model) {
        super(model);
    }
}
