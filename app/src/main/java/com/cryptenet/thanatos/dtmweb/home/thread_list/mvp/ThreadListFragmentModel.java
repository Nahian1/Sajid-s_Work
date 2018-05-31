/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.thread_list.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadListFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class ThreadListFragmentModel extends BaseFragModel<ThreadListFragmentContract.Repository>
        implements ThreadListFragmentContract.Model {
private static final String TAG = TagProvider.getDebugTag(ThreadListFragmentModel.class);

public ThreadListFragmentModel(ThreadListFragmentContract.Repository repository) {
        super(repository);
        }

        @Override
        public void getThreadList(Context context) {
                repository.getThreadList(context);
        }
}
