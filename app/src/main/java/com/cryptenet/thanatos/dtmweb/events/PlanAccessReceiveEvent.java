/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.Plans;

import java.util.List;

public class PlanAccessReceiveEvent {
    public final List<Plans> plansList;

    public PlanAccessReceiveEvent(List<Plans> plansList) {
        this.plansList = plansList;
    }
}
