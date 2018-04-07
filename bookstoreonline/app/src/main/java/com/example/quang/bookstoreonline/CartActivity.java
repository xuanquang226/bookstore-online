package com.example.quang.bookstoreonline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class CartActivity extends AppCompatActivity {

    private AddSubFuntion addSubFuntion = new AddSubFuntion() {
        @Override
        public void onAddClicked() {
            Toast.makeText(CartActivity.this,"Thêm 1",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSubClicked() {
            Toast.makeText(CartActivity.this,"Giảm 1",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_layout);
        AddSubControl addSubControl = (AddSubControl) findViewById(R.id.addSub);
        addSubControl.setAddSubFuntion(addSubFuntion);
    }
}
