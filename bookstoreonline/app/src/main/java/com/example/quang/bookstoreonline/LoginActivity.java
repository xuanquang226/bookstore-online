package com.example.quang.bookstoreonline;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quang.bookstoreonline.Models.UserModel;
import com.example.quang.bookstoreonline.tools.DownLoadAsyncTask;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Intent intent;
    Bundle bundle;

    EditText edtUserName;
    EditText edtPassword;

    String username;
    String password;

    int idBookSend = 0;
    int quanBookSend = 0;

    UserModel userModel;

    public final String SEND_BOOK_KEY = "bookKey";
    Bundle bundleBookSend;
    public static final String IDBOOKSEND = "idBookSend";
    public static final String QUANBOOKSEND = "quanBookSend";

    public final String SEND_KEY_USER = "send_data";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String MAIL = "mail";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";
    public static final String COIN = "coin";

    public final String GIVE_BOOK_KEY = "giveKey";
    public final String IDBOOKGIVE = "idBookSend";
    public final String QUANBOOKGIVE = "quanBookSend";

    Map<String, String> params;
    String url = "http://kaffeines.xyz/public/api/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login ");

        TextView txtSignUp = (TextView) findViewById(R.id.txtSignUp);
        TextView txtForgot = (TextView) findViewById(R.id.txtForgotPass);
        edtUserName = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnExit = (Button) findViewById(R.id.btnExit);

        Intent intentSendBook = getIntent();
        bundleBookSend = intentSendBook.getBundleExtra(SEND_BOOK_KEY);

        idBookSend =  bundleBookSend.getInt(IDBOOKSEND);
        quanBookSend = bundleBookSend.getInt(QUANBOOKSEND);


        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        txtForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = edtUserName.getText().toString();
                password = edtPassword.getText().toString();
                //new DownloadAsyncTask(LoginActivity.this).execute("http://anhtan3396.pe.hu/Web/public/api/login?c_email="+username+"&c_password="+password);
                new DownLoadAsyncTask(LoginActivity.this).execute("http://kaffeines.xyz/public/api/login?c_email="+username+"&c_password="+password);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    public void setData(UserModel user){
        //userModel = new UserModel(user.getC_mail(), user.getC_phone(), user.getC_address(), user.getC_coin(),user.getC_phone());
        userModel = user;

        bundle = new Bundle();
        intent = new Intent(LoginActivity.this, ConfirmationActivity.class);
        int id = 0;

        AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
        alertDialog.setTitle("Thong bao");
        alertDialog.setMessage("Dang nhap thanh cong");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        intent.putExtra(ID, userModel.getC_id().toString());
                        intent.putExtra(NAME, userModel.getC_name());
                        intent.putExtra(PHONE, userModel.getC_phone());
                        intent.putExtra(MAIL, userModel.getC_mail());
                        intent.putExtra(ADDRESS, userModel.getC_address());
                        intent.putExtra(COIN, userModel.getC_coin());

                        bundle.getInt(IDBOOKGIVE, idBookSend);
                        bundle.getInt(QUANBOOKGIVE, quanBookSend);

                        Log.d("id: ", intent.putExtra(ID, userModel.getC_id().toString()) + "");

                        Log.d("id book la: ", idBookSend + "");
                        Log.d("so luong book la: ", quanBookSend + "");



                        intent.putExtra(GIVE_BOOK_KEY, bundle);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();


    }

    public void showToast(String response) {
        Toast.makeText(LoginActivity.this, response + "", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(LoginActivity.this, "Ban phai dang nhap roi moi mua hang", Toast.LENGTH_SHORT);
    }
}























//        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        requestQueue.getCache().clear();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//
//
//                    if(jsonObject.getInt("resultCode") == 0) {
//                        Toast.makeText(LoginActivity.this, jsonObject.getString("message") + "", Toast.LENGTH_SHORT);
//                    }else {
//                        Toast.makeText(LoginActivity.this, jsonObject.getString("message") + "", Toast.LENGTH_SHORT);
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        })
//         {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                params = new HashMap<String, String>();
//
//                params.put("c_email", login_email);
//                params.put("c_password", login_pass);
//
//                Toast.makeText(LoginActivity.this, params.put("c_email", username) + "", Toast.LENGTH_SHORT).show();
//
//                return params;
//            }
//        };
//
//        int socketTimeout = 3000;
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES/DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        stringRequest.setRetryPolicy(policy);
//        requestQueue.add(stringRequest);



//    public void LoginURL(final String url, final String username, final String password) {
//        Toast.makeText(LoginActivity.this, url, Toast.LENGTH_SHORT).show();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
////                    Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
//                    // Toast.makeText(LoginActivity.this, jsonObject + "", Toast.LENGTH_SHORT).show();
//
//
//                    Toast.makeText(LoginActivity.this, jsonObject.getInt("resultCode") + "", Toast.LENGTH_SHORT).show();
//
//                    if (jsonObject.getInt("resultCode") == -1) {
//                        Toast.makeText(LoginActivity.this, jsonObject.getString("message") + "", Toast.LENGTH_SHORT);
//                    } else {
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                params = new HashMap<String, String>();
//                params.put("c_email", username);
//                params.put("c_password", password);
//                return super.getParams();
//            }
//        };
//
//        int socketTimeout = 3000;
//        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES / DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        stringRequest.setRetryPolicy(policy);
//
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        requestQueue.getCache().clear();
//        requestQueue.add(stringRequest);
//
//    }