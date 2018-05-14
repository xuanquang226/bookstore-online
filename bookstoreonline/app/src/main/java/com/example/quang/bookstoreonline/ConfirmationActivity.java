package com.example.quang.bookstoreonline;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmationActivity extends AppCompatActivity {

    Intent intent;
    Intent intentCart;

    Bundle bundle;

    public final String SEND_KEY = "send";
    public final String NAME = "name";
    public final String MAIL = "mail";
    public final String PHONE = "phone";
    public final String ADDRESS = "address";
    public final String COIN = "coin";

    public static boolean isLogin = false;

    public final String IDBOOK = "idbook";
    public final String QUANTITY = "quantity";

    public final String SEND_BOOK_KEY = "bookKey";

    Bundle bundleBookSend;

    public final String IDBOOKSEND = "idBookSend";
    public final String QUANBOOKSEND = "quanBookSend";

    Intent intentBookGive;
    Bundle bundleBookGive;
    public final String GIVE_BOOK_KEY = "giveKey";
    public final String IDBOOKGIVE = "idBookSend";
    public final String QUANBOOKGIVE = "quanBookSend";

    int idBookGive = 0;
    int quanBookGive = 0;
    String id;
    String name = "";
    String mail = "";
    String address = "";
    int coin = 0;
    String phone = "";
    TextView txtName;
    TextView txtAddress;
    TextView txtPhone;
    TextView txtCoin;
    TextView txtMail;


    int idBook = 0;
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Confirmation");





        txtName = (TextView) findViewById(R.id.txtUsernameC);
        txtAddress = (TextView) findViewById(R.id.txtAddressC);
        txtPhone = (TextView) findViewById(R.id.txtPhoneC);
        txtCoin = (TextView) findViewById(R.id.txtCoinC);
        txtMail = (TextView) findViewById(R.id.txtEmailC);



    }

    @Override
    protected void onResume() {
        super.onResume();
        intent = getIntent();
        intentCart = getIntent();

        Log.d("Login: ", isLogin + "");
        id = intent.getStringExtra(LoginActivity.ID);
        txtName.setText(intent.getStringExtra(LoginActivity.NAME));
        txtCoin.setText(intent.getStringExtra(LoginActivity.COIN));
        txtAddress.setText(intent.getStringExtra(LoginActivity.ADDRESS));
        txtMail.setText(intent.getStringExtra(LoginActivity.MAIL));
        txtPhone.setText(intent.getStringExtra(LoginActivity.PHONE));

        if(isLogin == false) {

            intentCart = getIntent();
            bundle = intentCart.getBundleExtra(SEND_KEY);

            idBook = bundle.getInt(IDBOOK);
            quantity = bundle.getInt(QUANTITY);

            Log.d("ID book la: ", idBook + "");
            Log.d("So luong la: ", quantity + "");




        }else if (isLogin == true) {


        }


        Button btnOrder = (Button) findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Dialog dialog = new Dialog(ConfirmationActivity.this);
//                dialog.setContentView(R.layout.dialog_yesno_layout);
//                dialog.show();

                if(isLogin == false) {

                    AlertDialog alertDialog = new AlertDialog.Builder(ConfirmationActivity.this).create();
                    alertDialog.setTitle("Thong bao");
                    alertDialog.setMessage("Ban phai dang nhap");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(ConfirmationActivity.this, LoginActivity.class);
                                    bundleBookSend = new Bundle();
                                    bundleBookSend.putInt(IDBOOKSEND, idBook);
                                    bundleBookSend.putInt(QUANBOOKSEND, quantity);

                                    intent.putExtra(SEND_BOOK_KEY, bundleBookSend);
                                    startActivity(intent);

                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }

                if(isLogin == true){

                    intentBookGive = getIntent();

                    bundleBookGive = intentBookGive.getBundleExtra(GIVE_BOOK_KEY);

                    idBookGive = bundleBookGive.getInt(IDBOOKGIVE) + 6;
                    quanBookGive = bundleBookGive.getInt(QUANBOOKGIVE) + 1;

                    int idUser = Integer.parseInt(id);

                    Log.d("ID sach la: ", idBookGive + "");
                    Log.d("So luong sach la: ", quanBookGive + "");


                    Log.d("Id la", id);

                    AlertDialog alertDialog = new AlertDialog.Builder(ConfirmationActivity.this).create();
                    alertDialog.setTitle("Success");
                    alertDialog.setMessage( "Ban da dat hang thanh cong");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(ConfirmationActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }
            }
        });
    }
}
