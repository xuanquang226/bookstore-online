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

import com.example.quang.bookstoreonline.Models.ViewOrdersModel;
import com.example.quang.bookstoreonline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quang on 4/7/2018.
 */

public class ViewOrdersAdapter extends ArrayAdapter<ViewOrdersModel> {
    AppCompatActivity context;
    int layout;
    ArrayList<ViewOrdersModel> viewOrders;

    public ViewOrdersAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<ViewOrdersModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.viewOrders = objects;
    }

    public class ViewHolder {
        ImageView imgBook;
        TextView nameBook;
        TextView price;
        TextView author;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgBook = (ImageView) convertView.findViewById(R.id.imgBookList);
            viewHolder.nameBook = (TextView) convertView.findViewById(R.id.txtNameBook);
            viewHolder.price = (TextView) convertView.findViewById(R.id.txtPriceA);
            viewHolder.author = (TextView) convertView.findViewById(R.id.txtAuthorA);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.nameBook.setText(viewOrders.get(position).getNameBook());
        viewHolder.price.setText(viewOrders.get(position).getPrice() + " VND");
//        viewHolder.imgBook.setImageResource(viewOrders.get(position).getImgBook());

        Picasso.with(getContext()).load(viewOrders.get(position).getImgBook()).into(viewHolder.imgBook);

        return convertView;
    }
}
