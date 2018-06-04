/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.investor_project.mvp;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragPresenter;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.InvestorProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class InvestorProjectFragmentPresenter extends BaseFragPresenter<InvestorProjectFragmentContract.View, InvestorProjectFragmentContract.Model>
        implements InvestorProjectFragmentContract.Presenter {
        private static final String TAG = TagProvider.getDebugTag(InvestorProjectFragmentPresenter.class);

        public InvestorProjectFragmentPresenter(InvestorProjectFragmentContract.Model model) {
                super(model);
        }

        @Override
        public void getMyProjectList(int reqType, Context context) {
                model.getMyProjectList(reqType,context);
        }
}
