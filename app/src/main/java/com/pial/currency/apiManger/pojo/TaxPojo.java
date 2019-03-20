package com.pial.currency.apiManger.pojo;

public class TaxPojo {
    private String taxName;
    private double taxValue;

    public TaxPojo(String taxName, double taxValue) {
        this.taxName = taxName;
        this.taxValue = taxValue;
    }

    public String getTaxName() {
        return taxName;
    }

    public double getTaxValue() {
        return taxValue;
    }
}
