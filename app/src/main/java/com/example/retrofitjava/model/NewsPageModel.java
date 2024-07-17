package com.example.retrofitjava.model;

import com.google.gson.annotations.SerializedName;

public class NewsPageModel {

    @SerializedName("title")
    public String title;

    @SerializedName("imgUrl")
    public String imgUrl;

}