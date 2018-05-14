package com.example.quang.bookstoreonline.Adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quang.bookstoreonline.LoginActivity;
import com.example.quang.bookstoreonline.Models.SaleModel;
import com.example.quang.bookstoreonline.R;
import com.example.quang.bookstoreonline.SaleFragment;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by Quang on 4/7/2018.
 */

public class SaleAdapter extends ArrayAdapter<SaleModel> {

    AppCompatActivity context;
    int layout;
    ArrayList<SaleModel> saleModels;
    ViewHolder viewHolder;
    public static String image;

    private Bitmap bitmap;


    private AsyncTask<String, Void, Bitmap> LoadImage;


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
        TextView oldPrice;
        TextView sale;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgBook = (ImageView) convertView.findViewById(R.id.imgBookListA);
            viewHolder.nameBook = (TextView) convertView.findViewById(R.id.txtNameBookA);
            viewHolder.price = (TextView) convertView.findViewById(R.id.txtPriceB);
            viewHolder.oldPrice = (TextView) convertView.findViewById(R.id.txtOldPrice);
            viewHolder.sale = (TextView) convertView.findViewById(R.id.txtSale);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.nameBook.setText(saleModels.get(position).getNameBookA());
        viewHolder.price.setText(saleModels.get(position).getPriceA() + " VND");

        Picasso.with(getContext()).load(saleModels.get(position).getImgBookA()).into(viewHolder.imgBook);

        viewHolder.oldPrice.setText(saleModels.get(position).getOldPrice() + " VND");
        viewHolder.sale.setText(saleModels.get(position).getSale());

        return convertView;
    }

}
