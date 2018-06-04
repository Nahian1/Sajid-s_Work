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
