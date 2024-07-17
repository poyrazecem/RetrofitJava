package com.example.retrofitjava.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.Date;

public class NewsModel {

    @SerializedName("page")
    public Integer page;

    @SerializedName("limit")
    public Integer limit;


    public Integer getPage() {
        return page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

}
