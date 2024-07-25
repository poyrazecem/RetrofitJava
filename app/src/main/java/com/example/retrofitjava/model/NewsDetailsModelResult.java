package com.example.retrofitjava.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsDetailsModelResult {

    @SerializedName("result")
    private NewsPageModel result;
    public NewsPageModel getResult() {return result;}
    public void setResult(NewsPageModel result) {this.result = result;}
}
