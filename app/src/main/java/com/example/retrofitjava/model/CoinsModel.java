package com.example.retrofitjava.model;

import com.google.gson.annotations.SerializedName;

public class CoinsModel {

    @SerializedName("page")
    public Integer page;

    @SerializedName("limit")
    public Integer limit;

    @SerializedName("currency")
    public String currency;

    @SerializedName("blockchain")
    public String blockchain;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(String blockchain) {
        this.blockchain = blockchain;
    }


}
