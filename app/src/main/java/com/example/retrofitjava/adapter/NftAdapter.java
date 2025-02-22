package com.example.retrofitjava.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitjava.R;
import com.example.retrofitjava.model.NftPageModel;
import com.squareup.picasso.Picasso;


import java.util.List;

public class NftAdapter extends RecyclerView.Adapter<NftAdapter.RowHolder>{

    private List<NftPageModel> nftList;

    public NftAdapter(List<NftPageModel> nftList) {
        this.nftList = nftList;
    }

    @NonNull
    @Override
    public NftAdapter.RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_nft_layout,parent,false);
        return new NftAdapter.RowHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(nftList.get(position), position);
        holder.textnft.setText(nftList.get(position).name);
        holder.source.setText("" + nftList.get(position).source);


        Picasso.get()
                .load(nftList.get(position).img)
                .into(holder.nftImageView);



    }

    @Override
    public int getItemCount() {
        return nftList.size();
    }


    public static class RowHolder extends RecyclerView.ViewHolder {
        TextView textnft;
        TextView source;
        ImageView nftImageView;

        public RowHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind (NftPageModel nftpageModel, Integer position) {
            textnft = itemView.findViewById(R.id.textnft);
            source = itemView.findViewById(R.id.source);
            nftImageView = itemView.findViewById(R.id.nftImageView);

        }
    }}


