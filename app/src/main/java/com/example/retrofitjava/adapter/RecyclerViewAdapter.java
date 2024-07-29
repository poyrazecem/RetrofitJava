package com.example.retrofitjava.adapter;

import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitjava.model.CryptoModel;
import com.example.retrofitjava.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {

    private List<CryptoModel> cryptoList;

    private String[] colors = {"#37B8F8","#3EB7F4","#3DA0D1","#3699CC","#3693C1","#4CAAD9"};

public RecyclerViewAdapter (List<CryptoModel> cryptoList){
    this.cryptoList = cryptoList;
}
    @NonNull
    @Override
    public RecyclerViewAdapter.RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_coins_layout,parent,false);
        return new RowHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RowHolder holder, int position) {
      holder.bind(cryptoList.get(position),colors,position);
      holder.textName.setText(cryptoList.get(position).name);
      holder.textPrice.setText("" + cryptoList.get(position).price);
      holder.twitterUrl.setText(cryptoList.get(position).twitterUrl);
      //holder.coinicon.setImageIcon(Icon.createWithContentUri(cryptoList.get(position).icon));

        Picasso.get()
                .load(cryptoList.get(position).icon)
                .into(holder.coinicon);
    }

    @Override
    public int getItemCount() {
        return cryptoList.size();
    }

    public static class RowHolder extends RecyclerView.ViewHolder {
    TextView textName;
    TextView textPrice;
    TextView twitterUrl;
    ImageView coinicon;

        public RowHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind (CryptoModel cryptoModel, String[]colors, Integer position) {
            itemView.setBackgroundColor(Color.parseColor(colors[position %6]));
            textName = itemView.findViewById(R.id.coinsName);
            textPrice = itemView.findViewById(R.id.coinsPrice);
            twitterUrl = itemView.findViewById(R.id.twitterUrl);
            coinicon = itemView.findViewById(R.id.coinicon);

        }
    }
}
