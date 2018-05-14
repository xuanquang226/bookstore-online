package com.example.quang.bookstoreonline.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quang.bookstoreonline.Models.CartModel;
import com.example.quang.bookstoreonline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quang on 4/7/2018.
 */

public class CartAdapter extends ArrayAdapter<CartModel> {
    AppCompatActivity context;
    int layout;
    ArrayList<CartModel> cartModels;
    int dem;


    public CartAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<CartModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.cartModels = objects;
    }

    public class ViewHolder {
        TextView nameBook;
        TextView priceBook;
        TextView author;
        TextView amount;
        ImageView imgBook;
        Button btnIncrease;
        Button btnDecrease;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.nameBook = (TextView) convertView.findViewById(R.id.txtNameBook);
            viewHolder.priceBook = (TextView) convertView.findViewById(R.id.txtPriceA);
            viewHolder.author = (TextView) convertView.findViewById(R.id.txtAuthorA);
            viewHolder.amount = (TextView) convertView.findViewById(R.id.txtAmount);
            viewHolder.imgBook = (ImageView) convertView.findViewById(R.id.imgBookList);
            viewHolder.btnDecrease = (Button) convertView.findViewById(R.id.btnDecrease);
            viewHolder.btnIncrease = (Button) convertView.findViewById(R.id.btnIncrease);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.nameBook.setText(cartModels.get(position).getNameBookB());
        viewHolder.priceBook.setText(Float.toString(cartModels.get(position).getPriceB()));
        viewHolder.amount.setText(Integer.toString(cartModels.get(position).getAmount()));
//        viewHolder.imgBook.setImageResource(cartModels.get(position).getImgBookB());

        Picasso.with(getContext()).load(cartModels.get(position).getImgBookB()).into(viewHolder.imgBook);

        viewHolder.btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dem = cartModels.get(position).getAmount();
                dem++;
                viewHolder.amount.setText(Integer.toString(dem));
                float defaultPrice = cartModels.get(position).getPriceB();
                float sum = dem * defaultPrice;
                viewHolder.priceBook.setText(sum + "");
                cartModels.get(position).setAmount(dem);

            }
        });

        viewHolder.btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dem = cartModels.get(position).getAmount();
                dem--;
                viewHolder.amount.setText(Integer.toString(dem));
                float defaultPrice = cartModels.get(position).getPriceB();
                float sum = dem * defaultPrice;
                viewHolder.priceBook.setText(sum + "");
                cartModels.get(position).setAmount(dem);
            }
        });


        return convertView;
    }
}
