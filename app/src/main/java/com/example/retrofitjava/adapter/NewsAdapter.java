package com.example.retrofitjava.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitjava.R;
import com.example.retrofitjava.model.NewsPageModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.RowHolder> {
    private List<NewsPageModel> newsList;

    public NewsAdapter(List<NewsPageModel> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_news_layout, parent, false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(newsList.get(position), position);
        holder.textnews.setText(newsList.get(position).title);

        Picasso.get()
                .load(newsList.get(position).imgUrl)
                .into(holder.newsImageView);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class RowHolder extends RecyclerView.ViewHolder {
        TextView textnews;
        ImageView newsImageView;

        public RowHolder(@NonNull View itemView) {
            super(itemView);
            textnews = itemView.findViewById(R.id.textnews);
            newsImageView = itemView.findViewById(R.id.newsImageView);
        }

        public void bind(NewsPageModel newsPageModel, Integer position) {
            // Diğer bağlama işlemleri burada yapılabilir
        }
    }
}
