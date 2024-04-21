package com.code.interviewDemo.domain;

public enum Tools {
    CHAINSAW("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true),
    LADDER("LADW","Ladder","Werner", 1.99, true, true, false),
    JACKHAMMER_D("JAKD","Jackhammer","DeWalt", 2.99, true, false, false),
    JACKHAMMER_R("JAKR","Jackhammer","Ridgid", 2.99, true, false, false);

    final String toolCode;
    final String toolType;
    final String brand;
    final double dailyCharge;
    final boolean weekdayCharge;
    final boolean weekendCharge;
    final boolean holidayCharge;

    Tools(String toolCode, String toolType, String brand, double dailyCharge, boolean weekdayCharge,
          boolean weekendCharge, boolean holidayCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
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

    public double getDailyCharge() {
        return dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }
}
