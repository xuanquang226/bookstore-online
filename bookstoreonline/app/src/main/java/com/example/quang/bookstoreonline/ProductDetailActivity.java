package com.example.quang.bookstoreonline;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quang.bookstoreonline.Adapters.NewBookAdapter;
import com.example.quang.bookstoreonline.Models.NewBookModel;
import com.example.quang.bookstoreonline.Models.ViewOrdersModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {

    public final String SEND_KEY = "send";
    public final String ID = "id";
    public final String TITLE = "title";
    public final String CONTENT = "content";
    public final String PRICE = "price";
    public final String CLICKED = "clicked";

    public final String IMAGE = "image";

    int     id = 0;
    String title;
    String content;
    String price;
    String image;

    JSONObject jsonObject;

    Intent intent;
    Bundle bundle;
    ImageView imageBook;

    String urlGetListBook = "http://kaffeines.xyz/public/api/getlistbook";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Product detail");

        intent = getIntent();
        bundle = intent.getBundleExtra(SEND_KEY);

        id  = bundle.getInt(ID);
        title = bundle.getString(TITLE);
        content = bundle.getString(CONTENT);
        price = bundle.getString(PRICE);
        image = bundle.getString(IMAGE);

        imageBook =  (ImageView) findViewById(R.id.imgBookA);
        TextView txtTitle = (TextView) findViewById(R.id.txtBookTitle);
        TextView txtContent = (TextView) findViewById(R.id.txtBookDesc);
        TextView txtPrice = (TextView) findViewById(R.id.txtPrice);

        Button btnBuy = (Button) findViewById(R.id.btnBuy);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle = new Bundle();
                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);

                int idBook = 0;
                idBook = id;

                String txtTitle = "";
                txtTitle = title;

                String txtDesc = "";
                txtDesc = content;

                String txtPrice = "";
                txtPrice = price;

                String img = "";
                img = image;

                bundle.putInt(ID, id);
                bundle.putString(TITLE, txtTitle);
                bundle.putString(PRICE, txtPrice);
                bundle.putString(IMAGE, img);


                intent.putExtra(SEND_KEY, bundle);
                startActivity(intent);
            }
        });



        new LoadImage().execute(image);

        txtTitle.setText(title);
        txtContent.setText(content);
        txtPrice.setText(price);

    }


    public class LoadImage extends AsyncTask<String, Void, Bitmap> {
        Bitmap bitmapImg = null;

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);

                InputStream inputStream = url.openConnection().getInputStream();

                bitmapImg = BitmapFactory.decodeStream(inputStream);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmapImg;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageBook.setImageBitmap(bitmap);
        }
    }
}
