package com.code.interviewDemo.domain;

public enum Tools {
    CHAINSAW("CHNS", "Chainsaw", "Stihl"),
    LADDER("LADW","Ladder","Werner"),
    JACKHAMMER_D("JAKD","Jackhammer","DeWalt"),
    JACKHAMMER_R("JAKR","Jackhammer","Ridgid");

    String toolCode;
    String toolType;
    String brand;

    Tools(String toolCode, String toolType, String brand) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
    }

    public String getToolType() {
        return toolType;
    }

    public String getBrand() {
        return brand;
    }

    public String getToolCode() {
        return toolCode;
    }
}
