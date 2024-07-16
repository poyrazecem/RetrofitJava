package com.example.retrofitjava.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CryptoModel {

    @SerializedName("symbol")
    public String symbol;

    @SerializedName("marketCap")
    public Double marketCap;

    @SerializedName("priceChange1w")
    public Double priceChange1w;

    @SerializedName("availableSupply")
    public Double availableSupply;

    @SerializedName("totalSupply")
    public Double totalSupply;

    @SerializedName("twitterUrl")
    public String twitterUrl;

    @SerializedName("icon")
    public String icon;

    @SerializedName("priceChange1h")
    public Double priceChange1h;

    @SerializedName("priceBtc")
    public Double priceBtc;

    @SerializedName("volume")
    public Double volume;

    @SerializedName("redditUrl")
    public String redditUrl;

    @SerializedName("priceChange1d")
    public Double priceChange1d;

    @SerializedName("websiteUrl")
    public String websiteUrl;

    @SerializedName("price")
    public Double price;

    @SerializedName("explorers")
    public List<String> explorers;

    @SerializedName("name")
    public String name;

    @SerializedName("rank")
    public Integer rank;

    @SerializedName("id")
    public String id;
}
