package com.example.quang.bookstoreonline.Adapters;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quang.bookstoreonline.Models.CardViewModel;
import com.example.quang.bookstoreonline.R;

import java.util.Vector;

/**
 * Created by Quang on 4/7/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Vector<CardViewModel> data;

    public RecyclerViewAdapter(Vector<CardViewModel> data) {
        this.data = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.imageText);
        }
    }

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CardView cardView = (CardView) inflater.inflate(R.layout.card_view_layout,parent,false);
        return new MyViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, int position) {
        Drawable drawable = holder.imageView.getResources().getDrawable(data.get(position).getImgSource());
        holder.imageView.setImageDrawable(drawable);
        holder.textView.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
