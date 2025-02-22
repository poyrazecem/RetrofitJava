package com.example.retrofitjava.Calls;

import com.example.retrofitjava.model.CoinsModel;
import com.example.retrofitjava.model.CryptoModelResult;
import com.example.retrofitjava.model.NewsDetailsModel;
import com.example.retrofitjava.model.NewsDetailsModelResult;
import com.example.retrofitjava.model.NewsModel;
import com.example.retrofitjava.model.NewsModelResult;
import com.example.retrofitjava.model.NewsPageModel;
import com.example.retrofitjava.model.NftModel;
import com.example.retrofitjava.model.NftModelResult;
import com.example.retrofitjava.network.ServiceGenerator;
import com.example.retrofitjava.service.GenericCallsService;

import retrofit2.Call;
import retrofit2.Callback;


public class GenericCalls {

    public static Call<CryptoModelResult> getCryptoModels(CoinsModel coinsModel, Callback<CryptoModelResult> callback) {
        GenericCallsService service = ServiceGenerator.with().createService(GenericCallsService.class);
        Call<CryptoModelResult> call = service.getCryptoModel(coinsModel.getPage(),coinsModel.getLimit(),coinsModel.getCurrency(),coinsModel.getBlockchain());
        call.enqueue(callback);
        return call;
    }

    public static Call<NftModelResult> getNftModels(NftModel nftModel, Callback<NftModelResult> callback){

        GenericCallsService service = ServiceGenerator.with().createService(GenericCallsService.class);
        Call<NftModelResult> call = service.getNftPageModel(nftModel.getPage(), nftModel.getLimit());
        call.enqueue(callback);
        return call;

    }

    public static Call<NewsModelResult> getNewsModels(NewsModel newsModel, Callback<NewsModelResult> callback){
        GenericCallsService service = ServiceGenerator.with().createService(GenericCallsService.class);
        Call<NewsModelResult> call = service.getNewsPageModel(newsModel.getPage(), newsModel.getLimit());
        call.enqueue(callback);
        return call;
    }

    public static Call<NewsPageModel> getNewsDetailsModels(String id, Callback<NewsPageModel> callback) {
        GenericCallsService service = ServiceGenerator.with().createService(GenericCallsService.class);
        Call<NewsPageModel> call = service.getNewsDetailPageModel(id);
        call.enqueue(callback);
        return call;
    }


}
