package com.cryptenet.thanatos.dtmweb.home.investor_project;

import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;

import java.util.ArrayList;
import java.util.List;

public class INVPlanGenerator {
    private static List<ProjectsRsp> rsps;

    public static List<ProjectsRsp> getList() {
        rsps = new ArrayList<>();

        ProjectsRsp rsp = new ProjectsRsp();
        rsp.setTitle("Hello Adele");
        rsp.setAccessPrice("500");
        rsp.setIsApproved(false);
        rsp.setCreatedAt("2018-05-27T20:07:27.793588Z");
        rsps.add(rsp);

        ProjectsRsp rsp2 = new ProjectsRsp();
        rsp2.setTitle("Chain Smoker");
        rsp2.setAccessPrice("800");
        rsp2.setIsApproved(true);
        rsp2.setCreatedAt("2018-05-27T20:07:27.793588Z");
        rsps.add(rsp2);

        ProjectsRsp rsp3 = new ProjectsRsp();
        rsp3.setTitle("Thug Life");
        rsp3.setAccessPrice("1500");
        rsp3.setIsApproved(false);
        rsp3.setCreatedAt("2018-05-27T20:07:27.793588Z");
        rsps.add(rsp3);

        ProjectsRsp rsp4 = new ProjectsRsp();
        rsp4.setTitle("San Andreas");
        rsp4.setAccessPrice("2500");
        rsp4.setIsApproved(true);
        rsp4.setCreatedAt("2018-05-27T20:07:27.793588Z");
        rsps.add(rsp4);

        return rsps;
    }
}
