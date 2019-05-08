package com.icebear.cleanmaster.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.florent37.viewanimator.ViewAnimator;
import com.icebear.cleanmaster.R;

public class LoadingAppActivity extends AppCompatActivity {

    private ImageView imgLoading;
    private Thread loadingThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_app_list);
        initView();
        animation();
        turnToActivity();
    }

    private void animation() {
        loadingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ViewAnimator.animate(imgLoading).rotation(360).duration(300).start();
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        loadingThread.start();
    }

    private void turnToActivity() {
        new Handler().postDelayed(() -> {
            loadingThread.interrupt();
            startActivity(new Intent(this, AppManagerActivity.class));
            finish();
        }, 1000);
    }

    private void initView() {
        imgLoading = findViewById(R.id.img_loading);

        Glide.with(this).load(R.drawable.img_loading).into(imgLoading);
    }
}
