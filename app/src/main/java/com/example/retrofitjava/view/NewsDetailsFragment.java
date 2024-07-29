package com.example.retrofitjava.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.retrofitjava.Calls.GenericCalls;
import com.example.retrofitjava.R;
import com.example.retrofitjava.databinding.FragmentNewsDetailsBinding;
import com.example.retrofitjava.model.NewsDetailsModel;
import com.example.retrofitjava.model.NewsDetailsModelResult;
import com.example.retrofitjava.model.NewsPageModel;
import com.squareup.picasso.Picasso;

import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailsFragment extends Fragment {

    private FragmentNewsDetailsBinding binding;
    private NewsPageModel newsdetails;
    String id;
    String link;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewsDetailsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        // Button'a tıklama işlemi
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // URL'ye gitmek için Intent oluşturma
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                // Fragment içinden startActivity çağrısı
                startActivity(browserIntent);
            }
        });
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
        getDataFromNewsFragment();
    }

    private void getDataFromNewsFragment() {
        if (getArguments() != null) {
            id = getArguments().getString("ecem");
            loadData(id);
        }
    }


    private void loadData(String id) {
        NewsDetailsModel newsdetailsModel = new NewsDetailsModel();

        GenericCalls.getNewsDetailsModels(id, new Callback<NewsPageModel>() {
            @Override
            public void onResponse(Call<NewsPageModel> call, Response<NewsPageModel> response) {
                assert response.body() != null;
                newsdetails = response.body();
                binding.title.setText(newsdetails.title);
                binding.detailsTextView.setText(newsdetails.description);
                binding.sourceTextView.setText(newsdetails.source);
                Picasso.get()
                        .load(newsdetails.imgUrl)
                        .into(binding.imageView2);
                link = newsdetails.link;
            }

            @Override
            public void onFailure(Call<NewsPageModel> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }
}