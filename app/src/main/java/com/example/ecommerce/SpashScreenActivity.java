package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SpashScreenActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT=3000;
    SharedPreferences sharedPreferences;
    LottieAnimationView lottieAnimationView;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        lottieAnimationView=findViewById(R.id.lottie);
        title=findViewById(R.id.welcometitle);

//        title.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
//        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);

        sharedPreferences = getSharedPreferences("autoLogin", Context.MODE_PRIVATE);
        int j=sharedPreferences.getInt("key",0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(j == 0)
                {
                    Intent intent=new Intent(SpashScreenActivity.this,Login.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent=new Intent(SpashScreenActivity.this,MainActivity.class);
                    startActivity(intent);
                }

                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}