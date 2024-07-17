package com.example.retrofitjava.network;

import android.content.Context;

import com.example.retrofitjava.view.NftFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String BASE_URL = "https://openapiv1.coinstats.app";

    public static final String CRYPTO_URL = BASE_URL + "/coins";
    public static final String NFT_URL = BASE_URL + "/nft/trending";
    public static final String NEWS_URL = BASE_URL + "/news";
    public static final String API_KEY = "LAjo7LL7qChk4iYXdazJu+TjFQXnSzJeE+iSx3BMZm8=";

    private static ServiceGenerator serviceGenerator;
    private Retrofit retrofit;
    private Context context;

    public static ServiceGenerator with() {
        serviceGenerator = new ServiceGenerator();
        return serviceGenerator;
    }

    private ServiceGenerator() {
        context = AppContext.getContext();
        retrofit = getRetrofitBuilder(BASE_URL);
    }

    public <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    private Retrofit getRetrofitBuilder(String url) {
        Gson gson = new GsonBuilder().setLenient().create();

        return new Retrofit.Builder()
                .baseUrl(url)
                .client(getDefaultClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();



    }

    private OkHttpClient getDefaultClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(chain -> chain.proceed(
                        chain.request()
                                .newBuilder()
                                .header("accept", "application/json")
                                .header("Content-Type", "application/json")
                                .header("x-api-key", ServiceGenerator.API_KEY)
                                .build()))
                .build();
    }

}
