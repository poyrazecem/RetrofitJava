package com.example.retrofitjava.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.retrofitjava.Calls.GenericCalls;
import com.example.retrofitjava.adapter.RecyclerViewAdapter;
import com.example.retrofitjava.databinding.FragmentHomeBinding;
import com.example.retrofitjava.model.CoinsModel;
import com.example.retrofitjava.model.CryptoModelResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private CryptoModelResult cryptoList;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // FragmentHomeBinding ile layout dosyasını bağlama
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        loadData();

        return view;
    }

    private void loadData() {
        CoinsModel coinsModel = new CoinsModel();
        coinsModel.setPage(1);
        coinsModel.setLimit(20);
        coinsModel.setCurrency("USD");
        coinsModel.setBlockchain("");

        GenericCalls.getCryptoModels(coinsModel, new Callback<CryptoModelResult>() {
            @Override
            public void onResponse(Call<CryptoModelResult> call, Response<CryptoModelResult> response) {
                System.out.println(response.body());
                assert response.body() != null;
                cryptoList = response.body();
                binding.rvcoin.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerViewAdapter = new RecyclerViewAdapter(cryptoList.getResult());
                binding.rvcoin.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<CryptoModelResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
