package com.example.quang.bookstoreonline;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quang.bookstoreonline.tools.DownLoadAsyncTask;
import com.example.quang.bookstoreonline.tools.DownLoadAsyncTaskRegist;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    EditText edtHoTen;
    EditText edtPass;
    EditText edtConfirm;
    EditText edtEmail;
    EditText edtAdress;
    EditText edtPhone;
    Button btnSignUp;
    Button btnExit;

    String mail;
    String pass;
    String phone;
    String rePass;
    String address;
    String name;



    Intent intent;

    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sign up");

        if (checkSendPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Log.d("storage", "Granted");
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        edtHoTen    = (EditText) findViewById(R.id.edtUsernameA);
        edtPass     = (EditText) findViewById(R.id.edtPasswordA);
        edtEmail    = (EditText) findViewById(R.id.edtEmail);
        edtConfirm     = (EditText) findViewById(R.id.edtRePassword);
        edtAdress   = (EditText) findViewById(R.id.edtAddress);
        edtPhone    = (EditText) findViewById(R.id.edtPhone);

        btnSignUp   = (Button) findViewById(R.id.btnSignUp);
        btnExit     = (Button) findViewById(R.id.btnExitA);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mail     = edtEmail.getText().toString().trim();
                pass     = edtPass.getText().toString().trim();
                phone    = edtPhone.getText().toString().trim();
                rePass   = edtConfirm.getText().toString().trim();
                address  = edtAdress.getText().toString().trim();
                name    = edtHoTen.getText().toString().trim();

                url = "http://kaffeines.xyz/public/api/regist?c_mail="+mail+"&c_password="+pass+"&c_phone="+phone+"&c_password_confirm="+rePass+"&c_address="+address+"&c_name="+name;
//                url = "http://kaffeines.xyz/public/api/bill";

                new DownLoadAsyncTaskRegist(SignUpActivity.this).execute(url);
                if(DownLoadAsyncTaskRegist.isSignUp) {
                    AlertDialog alertDialog = new AlertDialog.Builder(SignUpActivity.this).create();
                    alertDialog.setTitle("Success");
                    alertDialog.setMessage( "Dang ky thanh cong");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }




            }
        });
    }

    public void SignUp(String url) {

    }

    private boolean checkSendPermission(String permission) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
        return (permissionCheck == PackageManager.PERMISSION_GRANTED);
    }

//    public void SignUp(String url) {
//        RequestQueue requestQueue = Volley.newRequestQueue(SignUpActivity.this);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    Log.d("json: ", jsonObject + "");
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("Error: ", error.toString());
//            }
//        }) {
//            @Override
//            public Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("c_email", mail.trim());
//                params.put("c_password", pass.trim());
//                params.put("c_phone", phone.trim());
//                params.put("c_password_confirm", rePass.trim());
//                params.put("c_address", address.trim());
//                params.put("c_phone", phone.trim());
//                params.put("c_name", hoTen.trim());
//                return params;
//            }
//        };
//    }
}














//    StringRequest stringReques = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//        @Override
//        public void onResponse(String response) {
//            Log.d("Response: ", response);
//            try {
//                JSONObject object = new JSONObject(response);
//
//                if(object.getInt("resultCode") == 0) {
//                    Log.d("Object: ", object.getString("message"));
//                }else {
//                    Log.d("Loi tai ve: ", object.getString("message"));
//
//                }
//
//            } catch (JSONException e) {
//                Log.d("Loi ket noi: ", e.toString());
//                e.printStackTrace();
//            }
//        }
//    }, new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            Log.d("Error: ", error + "");
//
//        }
//    }){
//        @Override
//        protected Map<String, String> getParams() throws AuthFailureError {
//            Map<String, String> params = new HashMap<String, String>();
//            params.put("c_mail", mail);
//            params.put("c_password", pass);
//            params.put("c_phone", phone);
//            params.put("c_confirm_password", rePass);
//            params.put("c_address", address);
//            params.put("c_name", hoTen);
//            return super.getParams();
//        }
//    };
//
//                requestQueue.add(stringReques);