
package com.pial.currency.apiManger.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rates {

    @SerializedName("super_reduced")
    @Expose
    private double superReduced;
    @SerializedName("reduced")
    @Expose
    private double reduced;
    @SerializedName("standard")
    @Expose
    private double standard;
    @SerializedName("reduced1")
    @Expose
    private double reduced1;
    @SerializedName("reduced2")
    @Expose
    private double reduced2;
    @SerializedName("parking")
    @Expose
    private double parking;

    public double getSuperReduced() {
        return superReduced;
    }

    public void setSuperReduced(double superReduced) {
        this.superReduced = superReduced;
    }

    public double getReduced() {
        return reduced;
    }

    public void setReduced(double reduced) {
        this.reduced = reduced;
    }

    public double getStandard() {
        return standard;
    }

    public void setStandard(double standard) {
        this.standard = standard;
    }

    public double getReduced1() {
        return reduced1;
    }

    public void setReduced1(double reduced1) {
        this.reduced1 = reduced1;
    }

    public double getReduced2() {
        return reduced2;
    }

    public void setReduced2(double reduced2) {
        this.reduced2 = reduced2;
    }

    public double getParking() {
        return parking;
    }

    public void setParking(double parking) {
        this.parking = parking;
    }
}
