package com.example.retrofitjava.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retrofitjava.Calls.GenericCalls;
import com.example.retrofitjava.R;
import com.example.retrofitjava.adapter.NewsAdapter;
import com.example.retrofitjava.databinding.FragmentNewsBinding;
import com.example.retrofitjava.interfaces.OnItemClickListener;
import com.example.retrofitjava.model.NewsModel;
import com.example.retrofitjava.model.NewsModelResult;
import com.example.retrofitjava.model.NewsPageModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private List<NewsPageModel> newsList;
    String id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        loadData();
        return view;
    }

    public void loadData() {

        NewsModel newsModel = new NewsModel();
        newsModel.setPage(1);
        newsModel.setLimit(20);


        GenericCalls.getNewsModels(newsModel, new Callback<NewsModelResult>() {
            @Override
            public void onResponse(Call<NewsModelResult> call, Response<NewsModelResult> response) {
                if (response.isSuccessful() && response.body() != null) {
                    newsList = response.body().getResult();


                    binding.rvnews.setLayoutManager(new LinearLayoutManager(getContext()));
                    //StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                   // binding.rvnews.setLayoutManager(staggeredGridLayoutManager);

                    NewsAdapter newsAdapter = new NewsAdapter(newsList, new OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            id = newsList.get(position).id;
                            Bundle bundle = new Bundle();
                            bundle.putString("ecem",id);

                            NewsDetailsFragment newsDetailsFragment = new NewsDetailsFragment();
                            newsDetailsFragment.setArguments(bundle);

                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.frameLayout, newsDetailsFragment);
                            transaction.addToBackStack(null);
                            transaction.commitAllowingStateLoss();

                        }
                    });

                    binding.rvnews.setAdapter(newsAdapter);

                } else {
                    System.err.println("Yanıt başarısız: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<NewsModelResult> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}

