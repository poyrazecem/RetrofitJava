package com.example.retrofitjava.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CryptoModelResult {

    @SerializedName("result")
    private List<CryptoModel> result;

    public List<CryptoModel> getResult() {
        return result;
    }

    public void setResult(List<CryptoModel> result) {
        this.result = result;
    }
}
