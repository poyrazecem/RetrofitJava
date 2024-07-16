package com.example.retrofitjava.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NftModelResult {

    @SerializedName("data")
    private List<NftPageModel> data;

    public List<NftPageModel> getResult(){
        return data;
    }
    public void setResult(List<NftPageModel> result) {
        this.data = data;
    }
}



