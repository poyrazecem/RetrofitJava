package com.example.retrofitjava.view;

import static com.example.retrofitjava.network.ServiceGenerator.NFT_URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.retrofitjava.Calls.GenericCalls;
import com.example.retrofitjava.R;
import com.example.retrofitjava.adapter.NftAdapter;
import com.example.retrofitjava.adapter.RecyclerViewAdapter;
import com.example.retrofitjava.databinding.FragmentHomeBinding;
import com.example.retrofitjava.databinding.FragmentNftBinding;
import com.example.retrofitjava.model.CoinsModel;
import com.example.retrofitjava.model.CryptoModelResult;
import com.example.retrofitjava.model.NftModel;
import com.example.retrofitjava.model.NftModelResult;
import com.example.retrofitjava.model.NftPageModel;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public class NftFragment extends Fragment {

    private FragmentNftBinding binding;
    private List<NftPageModel> nftList;
    private NftAdapter NftAdapter;
    ImageView nftImageView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // FragmentHomeBinding ile layout dosyasını bağlama
        binding = FragmentNftBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        loadData();
        return view;
    }

    public void loadData() {
        NftModel nftModel = new NftModel();
        nftModel.setPage(1);
        nftModel.setLimit(20);

        GenericCalls.getNftModels(nftModel, new Callback<NftModelResult>() {
            @Override
            public void onResponse(Call<NftModelResult> call, Response<NftModelResult> response) {
                if (response.isSuccessful() && response.body() != null) {

                    nftList = response.body().getResult();
                    //binding.rvnft.setLayoutManager(new LinearLayoutManager(getContext()));

                    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    binding.rvnft.setLayoutManager(staggeredGridLayoutManager);

                    NftAdapter = new NftAdapter(nftList);
                    binding.rvnft.setAdapter(NftAdapter);
                } else {
                    System.err.println("Yanıt başarısız: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<NftModelResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}


/* Retrofit ile veriyi çek ve Glide ile resmi yükle
        apiService.getNft(nftPageModel.id).enqueue(new Callback<NftModel>() {
            @Override
            public void onResponse(Call<NftModel> call, Response<NftModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NftModel nftModel = response.body();
                    Glide.with(holder.itemView.getContext())
                        .load(nftModel.imageUrl)
                        .into(holder.imageView);
                }
            } */
