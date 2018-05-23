/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.utils;

import android.content.Context;
import android.content.Intent;

import com.cryptenet.thanatos.dtmweb.code.CodeActivity;
import com.cryptenet.thanatos.dtmweb.forgot_password.ForgotPasswordActivity;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.launcher.LoginActivity;
import com.cryptenet.thanatos.dtmweb.registration.RegistrationActivity;
import com.cryptenet.thanatos.dtmweb.set_password.SetPasswordActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Navigator {
    @Inject
    public Navigator() {
    }

    public void toLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public void toRegistrationActivity(Context context) {
        Intent intent = new Intent(context, RegistrationActivity.class);
        context.startActivity(intent);
    }

    public void toForgotPasswordActivity(Context context) {
        Intent intent = new Intent(context, ForgotPasswordActivity.class);
        context.startActivity(intent);
    }

    public void toCodeActivity(Context context) {
        Intent intent = new Intent(context, CodeActivity.class);
        context.startActivity(intent);
    }

    public void toSetPasswordActivity(Context context) {
        Intent intent = new Intent(context, SetPasswordActivity.class);
        context.startActivity(intent);
    }

    public void toHomeActivity(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }
}
