/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.thread_project.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class ThreadProjectFragmentPresenter extends BaseFragPresenter<ThreadProjectFragmentContract.View, ThreadProjectFragmentContract.Model>
        implements ThreadProjectFragmentContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(ThreadProjectFragmentPresenter.class);

    public ThreadProjectFragmentPresenter(ThreadProjectFragmentContract.Model model) {
        super(model);
    }
}
