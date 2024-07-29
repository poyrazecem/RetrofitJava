package com.example.retrofitjava.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitjava.R;
import com.example.retrofitjava.interfaces.OnItemClickListener;
import com.example.retrofitjava.model.NewsPageModel;
import com.example.retrofitjava.view.MainActivity;
import com.example.retrofitjava.view.NewsDetailsFragment;
import com.example.retrofitjava.view.NewsFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.RowHolder> {

    private final List<NewsPageModel> newsList;
    private final OnItemClickListener onItemClickListener;
    Context context;

    public NewsAdapter(List<NewsPageModel> newsList, OnItemClickListener onItemClickListener) {
        this.newsList = newsList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_news_layout, parent, false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bind(newsList.get(position), position);
        holder.textnews.setText(newsList.get(position).title);

        Picasso.get()
                .load(newsList.get(position).imgUrl)
                .into(holder.newsImageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class RowHolder extends RecyclerView.ViewHolder {
        TextView textnews;
        ImageView newsImageView;
        CardView cardView;

        public RowHolder(@NonNull View itemView) {
            super(itemView);
            textnews = itemView.findViewById(R.id.textnews);
            newsImageView = itemView.findViewById(R.id.newsImageView);
            cardView = itemView.findViewById(R.id.CardView);
        }

        public void bind(NewsPageModel newsPageModel, Integer position) {

        }
    }
}
