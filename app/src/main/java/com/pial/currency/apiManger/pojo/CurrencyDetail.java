
package com.pial.currency.apiManger.pojo;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrencyDetail {

    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("version")
    @Expose
    private Object version;
    @SerializedName("rates")
    @Expose
    private ArrayList<Rate> rates = new ArrayList<>();

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Object getVersion() {
        return version;
    }

    public void setVersion(Object version) {
        this.version = version;
    }

    public ArrayList<Rate> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Rate> rates) {
        this.rates = rates;
    }

}
