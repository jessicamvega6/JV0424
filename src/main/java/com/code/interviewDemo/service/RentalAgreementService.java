package com.code.interviewDemo.service;

import com.code.interviewDemo.domain.RentalAgreement;

public class RentalAgreementService {
    RentalTimeService rental;
    CalculateCost cost;

    RentalAgreementService(RentalTimeService rentalTime, CalculateCost cost) {
        this.rental = rentalTime;
        this.cost = cost;
    }

    RentalAgreement getRentalAgreement() {
        RentalAgreement agreement = new RentalAgreement();
        agreement.setToolCode(cost.tool.getToolCode());
        agreement.setToolBrand(cost.tool.getBrand());
        agreement.setToolType(cost.tool.getToolType());
        agreement.setRentalDays(rental.numberOfDaysRenting);
        agreement.setCheckoutDate(rental.checkoutDate);
        agreement.setDueDate(rental.dueDate);
        agreement.setDailyRentalCharge(cost.tool.getDailyCharge());
        agreement.setChargeDays(cost.chargeableDays);
        agreement.setPreDiscountCharge(cost.preDiscountCharge);
        agreement.setDiscountAmount(cost.discountAmount);
        agreement.setDiscountPercent(cost.discountPercent);
        agreement.setFinalCharge(cost.finalCharge);
        return agreement;
    }
}
