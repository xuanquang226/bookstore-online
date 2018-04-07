package com.example.quang.bookstoreonline;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Quang on 4/7/2018.
 */

public class AddSubControl extends LinearLayout {
    private ViewGroup mLayout;
    ImageButton imgAdd;
    TextView txtAmount;
    ImageButton imgSub;

    private AddSubFuntion addSubFuntion = null;


    public AddSubControl(Context context) {
        super(context);
        init();
    }

    public AddSubControl(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AddSubControl(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        ViewGroup viewGroup = (ViewGroup) inflate(getContext(),R.layout.custom_view_add_sub,this);
        mLayout = (ViewGroup) viewGroup.getChildAt(0);

        imgAdd = (ImageButton) findViewById(R.id.imgAdd);
        imgAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                addSubFuntion.onAddClicked();
            }
        });


        txtAmount = (TextView) findViewById(R.id.txtAmount);

        imgSub = (ImageButton) findViewById(R.id.imgSub);
        imgSub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                addSubFuntion.onSubClicked();
            }
        });

    }

    public void setAddSubFuntion(AddSubFuntion addSubFuntion) {
        this.addSubFuntion = addSubFuntion;
    }
}
