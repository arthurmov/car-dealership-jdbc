package com.pluralsight.models;

import com.pluralsight.Contract;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;
    private double monthlyPayment;

    public LeaseContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold,
                         double expectedEndingValue, double leaseFee, double monthlyPayment) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = expectedEndingValue;
        this.leaseFee = leaseFee;
        this.monthlyPayment = monthlyPayment;
    }

    public double getExpectedEndingValue() {
        return getVehicleSold().getPrice() / 2;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        if(this.leaseFee == 0) {
            this.leaseFee = getVehicleSold().getPrice() * .07;
        }
        return this.leaseFee;
    }

    public void setLeaseFee(int leaseFee) {
        this.leaseFee = leaseFee;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    @Override
    public double getTotalPrice() {
        return 0;
    }

    @Override
    public double getMonthlyPayment() {
        double price = getVehicleSold().getPrice();
        double interestRate = 0.04;
        double numMonthlyPayments = 36;
        double monthlyInterestRate = interestRate / 12;

        return price * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numMonthlyPayments)) / (Math.pow(1 + monthlyInterestRate, numMonthlyPayments) - 1);
    }
}
