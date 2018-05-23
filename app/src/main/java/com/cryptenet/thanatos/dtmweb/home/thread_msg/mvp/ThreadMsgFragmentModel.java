/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.thread_msg.mvp;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadMsgFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class ThreadMsgFragmentModel extends BaseFragModel<ThreadMsgFragmentContract.Repository>
        implements ThreadMsgFragmentContract.Model {
    private static final String TAG = TagProvider.getDebugTag(ThreadMsgFragmentModel.class);

    public ThreadMsgFragmentModel(ThreadMsgFragmentContract.Repository repository) {
        super(repository);
    }


}
