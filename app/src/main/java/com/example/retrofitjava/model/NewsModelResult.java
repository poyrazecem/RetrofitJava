package com.example.retrofitjava.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsModelResult {

    @SerializedName("result")
    private List<NewsPageModel> result;
    public List<NewsPageModel> getResult() {return result;}
    public void setResult(List<NewsPageModel> result) {this.result = result;}
}
