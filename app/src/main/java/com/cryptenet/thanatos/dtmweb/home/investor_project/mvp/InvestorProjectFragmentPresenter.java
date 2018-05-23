/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.investor_project.mvp;

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
}
