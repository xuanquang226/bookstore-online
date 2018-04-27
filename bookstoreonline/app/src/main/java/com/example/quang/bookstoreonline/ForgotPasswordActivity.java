package com.example.quang.bookstoreonline;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Forgot Password");

        Button btnOK = (Button) findViewById(R.id.btnConfirm);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPasswordActivity.this, NewPasswordActivity.class));
            }
        });
    }
}
