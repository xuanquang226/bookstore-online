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

import com.example.quang.bookstoreonline.Models.NewBookModel;
import com.example.quang.bookstoreonline.Models.ViewOrdersModel;
import com.example.quang.bookstoreonline.R;

import java.util.ArrayList;

/**
 * Created by Quang on 4/7/2018.
 */

public class NewBookAdapter extends ArrayAdapter<NewBookModel> {
    AppCompatActivity context;
    int layout;
    ArrayList<NewBookModel> newBookModels;

    public NewBookAdapter(@NonNull AppCompatActivity context, int resource, @NonNull ArrayList<NewBookModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.newBookModels = objects;
    }

    public class ViewHolder {
        ImageView imgBookC;
        TextView nameBookC;
        TextView priceC;
        TextView authorC;
        ImageView cart;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgBookC = (ImageView) convertView.findViewById(R.id.imgBookListC);
            viewHolder.nameBookC = (TextView) convertView.findViewById(R.id.txtNameBookC);
            viewHolder.priceC = (TextView) convertView.findViewById(R.id.txtPriceC);
            viewHolder.authorC = (TextView) convertView.findViewById(R.id.txtAuthorC);
            viewHolder.cart = (ImageView) convertView.findViewById(R.id.imgCart);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.nameBookC.setText(newBookModels.get(position).getNameBookC());
        viewHolder.priceC.setText(newBookModels.get(position).getPriceC() + " VND");
        viewHolder.authorC.setText(newBookModels.get(position).getAuthorC());
        viewHolder.imgBookC.setImageResource(newBookModels.get(position).getImgBookC());
        viewHolder.cart.setImageResource(newBookModels.get(position).getImgCart());

        return convertView;
    }
}
