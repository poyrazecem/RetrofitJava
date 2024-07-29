package com.example.retrofitjava.model;

import com.google.gson.annotations.SerializedName;

public class NewsDetailsModel {

    @SerializedName("page")
    public Integer page;

    @SerializedName("limit")
    public Integer limit;

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



}
