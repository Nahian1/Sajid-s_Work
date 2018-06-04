package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.Plans;

import java.util.List;

public class PlanAccessReceiveEvent {
    public final List<Plans> plansList;

    public PlanAccessReceiveEvent(List<Plans> plansList) {
        this.plansList = plansList;
    }
}
