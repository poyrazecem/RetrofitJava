package com.example.retrofitjava.service;

import com.example.retrofitjava.model.CryptoModelResult;
import com.example.retrofitjava.model.NewsModelResult;
import com.example.retrofitjava.model.NftModelResult;
import com.example.retrofitjava.network.ServiceGenerator;


import java.util.Date;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GenericCallsService {

    @GET(ServiceGenerator.CRYPTO_URL)
    Call<CryptoModelResult> getCryptoModel(
            @Query("page") int page,
            @Query("limit") int limit,
            @Query("currency") String currency,
            @Query("blockchain") String blockchain);

    @GET(ServiceGenerator.NFT_URL)
    Call<NftModelResult> getNftPageModel(
            @Query("page") int page,
            @Query("limit") int limit);

    @GET(ServiceGenerator.NEWS_URL)
    Call<NewsModelResult> getNewsPageModel(
            @Query("page") int page,
            @Query("limit") int limit);

}
