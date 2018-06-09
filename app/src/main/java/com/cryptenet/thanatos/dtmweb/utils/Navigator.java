/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.cryptenet.thanatos.dtmweb.code.CodeActivity;
import com.cryptenet.thanatos.dtmweb.forgot_password.ForgotPasswordActivity;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.launcher.LoginActivity;
import com.cryptenet.thanatos.dtmweb.registration.RegistrationActivity;
import com.cryptenet.thanatos.dtmweb.set_password.SetPasswordActivity;
import com.cryptenet.thanatos.dtmweb.terms.TermsActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Navigator {
    @Inject
    public Navigator() {
    }

    public void toLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void toRegistrationActivity(Context context, boolean isEdit) {
        Intent intent = new Intent(context, RegistrationActivity.class);
        intent.putExtra("isEdit",isEdit);
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

    public void toHomeActivity(Context context, @Nullable String string) {
        Intent intent = new Intent(context, HomeActivity.class);
        if (string != null) {
            intent.putExtra("user", string);
        }
        context.startActivity(intent);
    }

    public void toTermsActivity(Context context) {
        Intent intent = new Intent(context, TermsActivity.class);
        context.startActivity(intent);
    }
}
