package com.code.interviewDemo.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalAgreement {
    final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yy");

    private String toolCode;
    private String toolType;
    private String toolBrand;
    private Long rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private Double dailyRentalCharge;
    private Long chargeDays;
    private BigDecimal preDiscountCharge;
    private int discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    public Long getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(Long rentalDays) {
        this.rentalDays = rentalDays;
    }

    public Double getDailyRentalCharge() {
        return Math.round(dailyRentalCharge * 100.0) / 100.0;
    }

    public void setDailyRentalCharge(Double dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    public Long getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(Long chargeDays) {
        this.chargeDays = chargeDays;
    }

    public BigDecimal getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public void setPreDiscountCharge(BigDecimal preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(BigDecimal finalCharge) {
        this.finalCharge = finalCharge;
    }

    public String getDueDate() {
        return dueDate.format(FORMATTER);
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getCheckoutDate() {
        return checkoutDate.format(FORMATTER);
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    @Override
    public String toString() {
        return
                "toolCode='" + toolCode + '\'' + '\n' +
                "toolType='" + toolType + '\'' + '\n' +
                "toolBrand='" + toolBrand + '\'' + '\n' +
                "rentalDays=" + rentalDays + '\n' +
                "checkoutDate=" + checkoutDate.format(FORMATTER) + '\n' +
                "dueDate=" + dueDate.format(FORMATTER) + '\n' +
                "dailyRentalCharge= $" + dailyRentalCharge + '\n' +
                "chargeDays=" + chargeDays + '\n' +
                "preDiscountCharge= $" + preDiscountCharge + '\n' + //TODO have money have $ and perentages %
                "discountPercent=" + discountPercent + "%" +'\n' +
                "discountAmount= $" + discountAmount + '\n' +
                "finalCharge= $" + finalCharge;
    }

    public void printToConsole() {
        System.out.println(this);
    }
}
