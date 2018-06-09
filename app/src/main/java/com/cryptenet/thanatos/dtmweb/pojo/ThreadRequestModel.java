/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.pojo;

public class ThreadRequestModel {
    private String initiator_name;

    private String investor_picture;

    private String investor;

    private String last_text;

    private String plan_title;

    private String last_active;

    private String investor_name;

    private String id;

    private String plan_cover;

    private String plan;

    private String initiator;

    private String initiator_picture;

    private String activated;

    private String created_at;

    public String getInitiator_name ()
    {
        return initiator_name;
    }

    public void setInitiator_name (String initiator_name)
    {
        this.initiator_name = initiator_name;
    }

    public String getInvestor_picture ()
    {
        return investor_picture;
    }

    public void setInvestor_picture (String investor_picture) {
        this.investor_picture = investor_picture;
    }

    public String getInvestor ()
    {
        return investor;
    }

    public void setInvestor (String investor)
    {
        this.investor = investor;
    }

    public String getLast_text ()
    {
        return last_text;
    }

    public void setLast_text (String last_text)
    {
        this.last_text = last_text;
    }

    public String getPlan_title ()
    {
        return plan_title;
    }

    public void setPlan_title (String plan_title)
    {
        this.plan_title = plan_title;
    }

    public String getLast_active ()
    {
        return last_active;
    }

    public void setLast_active (String last_active)
    {
        this.last_active = last_active;
    }

    public String getInvestor_name ()
    {
        return investor_name;
    }

    public void setInvestor_name (String investor_name)
    {
        this.investor_name = investor_name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPlan_cover ()
    {
        return plan_cover;
    }

    public void setPlan_cover (String plan_cover)
    {
        this.plan_cover = plan_cover;
    }

    public String getPlan ()
    {
        return plan;
    }

    public void setPlan (String plan)
    {
        this.plan = plan;
    }

    public String getInitiator ()
    {
        return initiator;
    }

    public void setInitiator (String initiator)
    {
        this.initiator = initiator;
    }

    public String getInitiator_picture ()
    {
        return initiator_picture;
    }

    public void setInitiator_picture (String initiator_picture) {
        this.initiator_picture = initiator_picture;
    }

    public String getActivated ()
    {
        return activated;
    }

    public void setActivated (String activated)
    {
        this.activated = activated;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "ClassPojo [initiator_name = "+initiator_name+", investor_picture = "+investor_picture+", investor = "+investor+", last_text = "+last_text+", plan_title = "+plan_title+", last_active = "+last_active+", investor_name = "+investor_name+", id = "+id+", plan_cover = "+plan_cover+", plan = "+plan+", initiator = "+initiator+", initiator_picture = "+initiator_picture+", activated = "+activated+", created_at = "+created_at+"]";
    }
}
