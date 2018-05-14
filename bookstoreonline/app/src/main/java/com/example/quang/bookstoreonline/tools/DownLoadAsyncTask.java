package com.example.quang.bookstoreonline.tools;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.example.quang.bookstoreonline.ConfirmationActivity;
import com.example.quang.bookstoreonline.LoginActivity;
import com.example.quang.bookstoreonline.MainActivity;
import com.example.quang.bookstoreonline.Models.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Dai on 5/7/18.
 */

public class DownLoadAsyncTask extends AsyncTask<String, Integer, String> {
    private String data = "";
    private LoginActivity me;

    public DownLoadAsyncTask(LoginActivity me) {
        super();
        this.me = me;
    }

    @Override
    protected void onPreExecute() {
        // Pre processing
    }

    @Override
    protected String doInBackground(String... f_url) {
        try {
            URL url = new URL(f_url[0]);
            URLConnection conection = url.openConnection();
            conection.connect();
            // getting file length
            // lenghtOfFile = conection.getContentLength();
            // Read the file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
            br.close();
            input.close();
        } catch (Exception e) {
            Log.d("Exception: ", e.toString());
        }
        return data;
    }

    protected void onProgressUpdate(Integer... progress) {
        // Do nothing now
    }

    @Override
    protected void onPostExecute(String json) {
        parseJson(json);
    }

    public void parseJson(String json) {
        //Log.d("test", "Json "+json);
        if (json.isEmpty()) {
            return;
        }

        try {
            JSONObject jsonObject = new JSONObject(json);


            if(jsonObject.getInt("resultCode") == 0) {
//                Toast.makeText(me, jsonObject.getString("message") + "", Toast.LENGTH_SHORT);
                Log.d("json: " , jsonObject.getString("message") + "");
                int id = 0;
                String mail     = "";
                String phone    = "";
                String name     = "";
                String coin     = "";
                String address  = "";

                JSONArray data = jsonObject.getJSONArray("data");
                JSONObject objData = data.getJSONObject(0);
                id      = objData.getInt("id");
                mail    = objData.getString("c_mail");
                phone   = objData.getString("c_phone");
                name    = objData.getString("c_name");
                coin    = objData.getString("c_coin");
                address = objData.getString("c_address");

                Log.d("mail", mail);
                Log.d("phone", phone);
                Log.d("name", name);
                Log.d("coin", coin);
                Log.d("address", address);


                UserModel user = new UserModel(id, mail, phone, name, coin, address);

                me.setData(user);
                ConfirmationActivity.isLogin = true;



            }else {
                AlertDialog alertDialog = new AlertDialog.Builder(me).create();
                alertDialog.setTitle("Failed");
                alertDialog.setMessage("Dang nhap that bai vui long thu lai");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

