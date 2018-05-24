package com.cryptenet.thanatos.dtmweb.pojo;

public class NavHeader {
    private String ppUrl;
    private String name;
    private String type;
    private String location;
    private String desc;

    public NavHeader(String ppUrl, String name, String type, String location, String desc) {
        this.ppUrl = ppUrl;
        this.name = name;
        this.type = type;
        this.location = location;
        this.desc = desc;
    }

    public void setPpUrl(String ppUrl) {
        this.ppUrl = ppUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPpUrl() {
        return ppUrl;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public String getDesc() {
        return desc;
    }
}
