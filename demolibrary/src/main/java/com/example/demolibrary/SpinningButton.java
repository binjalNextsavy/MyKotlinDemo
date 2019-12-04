package com.example.demolibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;

public class SpinningButton extends Button implements View.OnClickListener {
    public SpinningButton(Context context) {
        super(context);
        init();
    }

    public SpinningButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public SpinningButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SpinningButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        RotateAnimation animation = new RotateAnimation(0,360,v.getHeight()/2,v.getWidth()/2);
        animation.setDuration(500);
        v.startAnimation(animation);

    }
}
