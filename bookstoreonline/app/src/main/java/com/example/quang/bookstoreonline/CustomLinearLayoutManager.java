package com.example.quang.bookstoreonline;

import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

/**
 * Created by Quang on 4/7/2018.
 */

public class CustomLinearLayoutManager extends LinearLayoutManager {
    private float MILLISECOND_PER_INCH;


    public CustomLinearLayoutManager(Context context) {
        super(context);
    }

    public CustomLinearLayoutManager(Context context, float delay) {
        super(context);
        MILLISECOND_PER_INCH = delay;
    }

    public CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CustomLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        final LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return CustomLinearLayoutManager.this.computeScrollVectorForPosition(targetPosition);
            }

            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics){
                return MILLISECOND_PER_INCH / displayMetrics.densityDpi;
            }
        };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }
}
