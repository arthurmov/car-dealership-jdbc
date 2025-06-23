package com.pluralsight.models;

import com.pluralsight.Contract;

public class SalesContract extends Contract {
    private double salesTax;
    private int recordingFee;
    private int processingFee;
    private boolean isFinance;


    public SalesContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold,
                         double salesTax, int recordingFee, int processingFee, boolean isFinance) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.salesTax = salesTax;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.isFinance = isFinance;
    }

    public double getSalesTax() {
        if(this.salesTax == 0) {
            this.salesTax = this.getVehicleSold().getPrice() * 0.05;
        }
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public int getRecordingFee() {
        if(this.recordingFee == 0) {
            recordingFee = 100;
        }
        return recordingFee;
    }

    public void setRecordingFee(int recordingFee) {
        this.recordingFee = recordingFee;
    }

    public int getProcessingFee() {

        return processingFee;
    }

    public void setProcessingFee(int processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinance() {
        return isFinance;
    }

    public void setFinance(boolean finance) {
        isFinance = finance;
    }

    @Override
    public double getTotalPrice() {
        if(getVehicleSold().getPrice() < 10000) {
            processingFee = 295;
        } else processingFee = 495;

        return getVehicleSold().getPrice() + this.salesTax + this.recordingFee +this.processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (isFinance) {
            double price = getVehicleSold().getPrice();
            double interestRate;
            double annualInterestRate;
            double numMonthlyPayments;
            double monthlyInterestRate;

            if (price >= 10000) {
                interestRate = 0.0425;
                numMonthlyPayments = 48;
            } else {
                interestRate = 0.0525;
                numMonthlyPayments = 24;
            }

            monthlyInterestRate = interestRate / 12;

            return price * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numMonthlyPayments)) / (Math.pow(1 + monthlyInterestRate, numMonthlyPayments) - 1);
        }
        return 0;
    }

}
