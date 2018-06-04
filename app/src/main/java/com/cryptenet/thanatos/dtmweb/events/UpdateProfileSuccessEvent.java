/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.RegistrationResponse;
import com.cryptenet.thanatos.dtmweb.pojo.UpdateProfileResponse;

public class UpdateProfileSuccessEvent {
    public final UpdateProfileResponse updateProfileResponse;

    public UpdateProfileSuccessEvent(UpdateProfileResponse updateProfileResponse) {
        this.updateProfileResponse = updateProfileResponse;
    }
}
