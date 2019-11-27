package com.example.myapplication.activity;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.R;
import pl.droidsonroids.gif.GifImageView;

public class SplashAnimated extends AppCompatActivity {
    GifImageView gifiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_animated);

        gifiv = findViewById(R.id.ivGifSplash);

        startSplash();
    }

    private void startSplash() {
        Animation fadeout = new AlphaAnimation(1.f, 1.f);
        fadeout.setDuration(2500);
        final View viewToAnimate = gifiv;
        fadeout.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                gifiv.setBackgroundResource(R.drawable.gif_splash);//splash_screen is your .gif file
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }}
            );
        gifiv.startAnimation(fadeout);

    }
}
