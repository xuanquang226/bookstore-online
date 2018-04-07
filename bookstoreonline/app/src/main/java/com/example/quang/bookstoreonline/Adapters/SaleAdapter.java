package com.example.quang.bookstoreonline.Adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quang.bookstoreonline.Models.SaleModel;
import com.example.quang.bookstoreonline.R;

import java.util.ArrayList;

/**
 * Created by Quang on 4/7/2018.
 */

public class SaleAdapter extends ArrayAdapter<SaleModel> {

    AppCompatActivity context;
    int layout;
    ArrayList<SaleModel> saleModels;

    public SaleAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<SaleModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.saleModels = objects;
    }

    public class ViewHolder {
        ImageView imgBook;
        TextView nameBook;
        TextView price;
        TextView author;
        TextView oldPrice;
        TextView sale;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgBook = (ImageView) convertView.findViewById(R.id.imgBookListA);
            viewHolder.nameBook = (TextView) convertView.findViewById(R.id.txtNameBookA);
            viewHolder.price = (TextView) convertView.findViewById(R.id.txtPriceB);
            viewHolder.author = (TextView) convertView.findViewById(R.id.txtAuthorB);
            viewHolder.oldPrice = (TextView) convertView.findViewById(R.id.txtOldPrice);
            viewHolder.sale = (TextView) convertView.findViewById(R.id.txtSale);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.nameBook.setText(saleModels.get(position).getNameBookA());
        viewHolder.price.setText(saleModels.get(position).getPriceA() + " VND");
        viewHolder.author.setText(saleModels.get(position).getAuthorA());
        viewHolder.imgBook.setImageResource(saleModels.get(position).getImgBookA());
        viewHolder.oldPrice.setText(saleModels.get(position).getOldPrice() + " VND");
        viewHolder.sale.setText(saleModels.get(position).getSale());

        return convertView;
    }
}
