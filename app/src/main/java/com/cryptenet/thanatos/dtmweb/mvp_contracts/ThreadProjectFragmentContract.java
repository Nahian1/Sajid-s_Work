/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

public interface ThreadProjectFragmentContract {
    interface Presenter extends BaseFragContract.Presenter<ThreadProjectFragmentContract.View> {
    }

    interface View extends BaseFragContract.View {
    }

    interface Model extends BaseFragContract.Model {
    }

    interface Repository extends BaseFragContract.Repository {
    }
}
