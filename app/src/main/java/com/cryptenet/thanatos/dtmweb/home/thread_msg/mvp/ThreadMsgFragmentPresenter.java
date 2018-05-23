/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.thread_msg.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadMsgFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class ThreadMsgFragmentPresenter extends BaseFragPresenter<ThreadMsgFragmentContract.View, ThreadMsgFragmentContract.Model>
        implements ThreadMsgFragmentContract.Presenter {
    private static final String TAG = TagProvider.getDebugTag(ThreadMsgFragmentPresenter.class);

    public ThreadMsgFragmentPresenter(ThreadMsgFragmentContract.Model model) {
        super(model);
    }
}
