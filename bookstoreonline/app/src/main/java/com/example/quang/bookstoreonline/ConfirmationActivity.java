package com.example.quang.bookstoreonline;

import android.app.Dialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Confirmation");
        Button btnOrder = (Button) findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(ConfirmationActivity.this);
                dialog.setContentView(R.layout.dialog_yesno_layout);
                dialog.show();
            }
        });
    }
}
