package com.example.quang.bookstoreonline.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quang.bookstoreonline.Models.FeatureBookModel;
import com.example.quang.bookstoreonline.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quang on 4/7/2018.
 */

public class FeatureBookAdapter extends ArrayAdapter<FeatureBookModel> {
    AppCompatActivity context;
    int layout;
    ArrayList<FeatureBookModel> featureBookModels;

    public FeatureBookAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<FeatureBookModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.featureBookModels = objects;
    }

    public class ViewHolder {
        ImageView imgBookD;
        TextView nameBookD;
        TextView priceD;
        TextView authorD;
        ImageView cartD;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgBookD = (ImageView) convertView.findViewById(R.id.imgBookListD);
            viewHolder.nameBookD = (TextView) convertView.findViewById(R.id.txtNameBookD);
            viewHolder.priceD = (TextView) convertView.findViewById(R.id.txtPriceD);
            viewHolder.authorD = (TextView) convertView.findViewById(R.id.txtAuthorD);
            viewHolder.cartD = (ImageView) convertView.findViewById(R.id.imgCart);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.nameBookD.setText(featureBookModels.get(position).getNameBookD());
        viewHolder.priceD.setText(featureBookModels.get(position).getPriceD() + " VND");
        viewHolder.authorD.setText(featureBookModels.get(position).getAuthorD());
        viewHolder.imgBookD.setImageResource(featureBookModels.get(position).getImgBookD());
        viewHolder.cartD.setImageResource(featureBookModels.get(position).getImgCartD());

        return convertView;
    }
}
