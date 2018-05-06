package com.example.quang.bookstoreonline;

import android.app.Dialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_password_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("New Password");

        Button btnOK = (Button) findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(NewPasswordActivity.this);
                dialog.setContentView(R.layout.dialog_new_password_layout);
                EditText newpass = (EditText) findViewById(R.id.edtNewPass);
                EditText repass = (EditText) findViewById(R.id.edtRePass);
                Button btnOKNP = (Button) findViewById(R.id.btnOKNP);
                dialog.show();
            }
        });
    }
}
